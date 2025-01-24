package com.chanochoca.app.twitter.application;

import com.chanochoca.app.twitter.domain.twitter.aggregate.Tweet;
import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUser;
import com.chanochoca.app.twitter.domain.twitter.repository.TwitterUserRepository;
import com.chanochoca.app.twitter.domain.twitter.service.TwitterUserSynchronizer;
import com.chanochoca.app.twitter.domain.twitter.vo.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TwitterApplicationService {

    private final TwitterUserSynchronizer twitterUserSynchronizer;

    @Value("${twitter.api.url}")
    private String twitterApiUrl;

    @Value("${twitter.api.bearer.token}")
    private String bearerToken;

    public TwitterApplicationService(TwitterUserRepository twitterUserRepository) {
        this.twitterUserSynchronizer = new TwitterUserSynchronizer(twitterUserRepository);
    }

    @Transactional
    public TwitterUser getUserTweets(TwitterInfluencerDTO twitterInfluencerDTO) {
        try {
            twitterInfluencerDTO.setInfluencerName(sanitizeUsername(twitterInfluencerDTO.getInfluencerName()));

            // Campos adicionales que deseas solicitar, basados en los atributos disponibles
            String userFields = "id,username,description,profile_image_url,verified,public_metrics";

            // Construir la URL con parámetros adicionales
            String url = String.format("%s/users/by/username/%s?user.fields=%s",
                    twitterApiUrl,
                    twitterInfluencerDTO.getInfluencerName(),
                    userFields);

            // Realizar la solicitud HTTP GET
            HttpResponse<String> response = Unirest.get(url)
                    .header("Authorization", "Bearer " + bearerToken)
                    .asString();

            // Verificar estado de la respuesta
            if (response.getStatus() == 200) {
                // Convertir JSON en un objeto TwitterAPIResponse
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                TwitterAPIResponse userResponse = mapper.readValue(response.getBody(), TwitterAPIResponse.class);
                Set<Tweet> tweets = getUserTweetsById(userResponse.getData().getUsername());

//                //TODO: en twitterUser falta el ID
                TwitterUser twitterUser = new TwitterUser(
                        new TwitterUserUsername(userResponse.getData().getUsername()),
                        new TwitterUserDescription(userResponse.getData().getDescription()),
                        new TwitterUserImageUrl(userResponse.getData().getProfileImageUrl()),
                        tweets);
                System.out.println(twitterUser.getUsername() + " " + twitterUser.getDescription());
//                twitterUserSynchronizer.createTwitterUser(twitterUser);

                return twitterUser;
            } else {
                // Manejar errores en caso de respuestas no exitosas
                throw new RuntimeException("Error al consultar Twitter API: " + response.getBody());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con Twitter API", e);
        }
    }

    /**
     {"data":
       {
         "description":"Director - Marketing & Business Development, Bilcare Technologies",
         "profile_image_url":"https://pbs.twimg.com/profile_images/618789238/3e52698_normal.jpg",
         "verified":false,
         "id":"102216607",
         "username":"lizchurchill",
         "name":"Liz Churchill",
         "public_metrics":
           {"followers_count":29,
            "following_count":5,
            "tweet_count":5,
            "listed_count":3,
            "like_count":0,
            "media_count":0
           }
       }
     }
     */

    public Set<Tweet> getUserTweetsById(String username) {
        try {
            // Definir los campos para los tweets
            String tweetFields = "id,created_at,text";

            // Definir la consulta para filtrar los tweets por el nombre de usuario
//            String query = "health from:" + username;
            String query = "health";

            // Construir la URL para obtener los tweets
            String url = String.format("%s/tweets/search/recent?query=%s&tweet.fields=%s",
                    twitterApiUrl,
                    query,
                    tweetFields);

            // Realizar la solicitud HTTP GET
            HttpResponse<String> response = Unirest.get(url)
                    .header("Authorization", "Bearer " + bearerToken)
                    .asString();

            // Verificar estado de la respuesta
            if (response.getStatus() == 200) {
                // Convertir JSON en un objeto TwitterAPIResponse
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                // Mapear la respuesta a un objeto TwitterAPIResponse
                TweetAPIResponse tweetAPIResponse = mapper.readValue(response.getBody(), TweetAPIResponse.class);

                // Crear un conjunto de tweets
                //TODO: distinguir en el atributo text la url (puede o no estar), para poder pasarlo como último parámetro en el map
                Set<Tweet> tweets = tweetAPIResponse.getData().stream()
                        .map(data -> new Tweet(
                                new TweetText(data.getText()),
                                new TweetDate(data.getCreated_at()),
                                new TweetLink(extractUrl(data.getText()))
                        ))
                        .collect(Collectors.toSet());
                tweets.forEach(tweet -> System.out.println(tweet.getText() + " - " + tweet.getLink()));

                return tweets; // Retornar el conjunto de tweets
            } else {
                // Manejar errores en caso de respuestas no exitosas
                throw new RuntimeException("Error al consultar Twitter API: " + response.getBody());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los tweets del usuario", e);
        }
    }

    /**
     {
     "data":[
       {"text":"RT @MJTruthUltra: I don’t like this…\n\nTrump asked what kind of Health Benefits can we expect from AI — Larry Ellison Suggests mRNA Vaccines…",
        "edit_history_tweet_ids":["1881890354183672128"],
        "created_at":"2025-01-22T02:23:18.000Z",
        "id":"1881890354183672128"},
       {"text":"\"fighting for you with every breath in my body\"\n\nRemoves US from World HEALTH Org shuts down TikTok pardons violent Jan 6 convicts\n\n1st orders: Nominates Project 2025 collabs (swore he didn't know them). Raises flags for his ego.\n\nsource: https://t.co/Dhs4kU8TH5\n\n#TrumpLies https://t.co/zMhlXNZ56W",
        "edit_history_tweet_ids":["1881890353835639036"],
        "created_at":"2025-01-22T02:23:18.000Z",
        "id":"1881890353835639036"},
       {"text":"RT @DrBenTapper1: Get me off the planet. Second day in office and he’s pushing a cancer vaccine? Are you kidding me? Make no mistake. This…",
        "edit_history_tweet_ids":["1881890353781084393"],
        "created_at":"2025-01-22T02:23:18.000Z",
        "id":"1881890353781084393"},
       {"text":"RT @MJTruthUltra: I don’t like this…\n\nTrump asked what kind of Health Benefits can we expect from AI — Larry Ellison Suggests mRNA Vaccines…",
        "edit_history_tweet_ids":["1881890353592291695"],
        "created_at":"2025-01-22T02:23:18.000Z",
        "id":"1881890353592291695"},
       {"text":"@ShadowofEzra Internet of Bodies #IoB \uD83E\uDDF5\n\n\"Bio-nanoscale machines, but these are for injecting into the body, always monitoring the health problems. That is also really going really well, like with these covid vaccines, it's going in this direction [...] Those will be part of 7G and beyond.\"https://t.co/19UHA0AsCP https://t.co/jsZxxXibPp","edit_history_tweet_ids":["1881890353185501517"],"created_at":"2025-01-22T02:23:18.000Z","id":"1881890353185501517"},{"text":"RT @annvandersteel: BREAKING: The World Health Organization BEGS Trump to Reconsider Withdrawing.\n\nYesterday, Trump signed an Executive Ord…","edit_history_tweet_ids":["1881890352061460486"],"created_at":"2025-01-22T02:23:18.000Z","id":"1881890352061460486"},{"text":"@MrAndyNgo Very sad... I'm sure he got no mental health evaluation.  That family should sue the govt","edit_history_tweet_ids":["1881890351977480633"],"created_at":"2025-01-22T02:23:18.000Z","id":"1881890351977480633"},{"text":"RT @luckytran: The censorship begins: According to officials, the Trump administration is instructing health agencies including the CDC, FD…","edit_history_tweet_ids":["1881890350878617844"],"created_at":"2025-01-22T02:23:18.000Z","id":"1881890350878617844"},{"text":"No more membership in World Health Organization\nNo more membership in Paris Climate Accords\nPardons &amp; frees 1500 violent thugs who attacked police\nI'm guessing the internment camps are next?\n\nSomeone please save us from this evil!\n\n#Felon47 #NeverMyPresident","edit_history_tweet_ids":["1881890350622761399"],"created_at":"2025-01-22T02:23:18.000Z","id":"1881890350622761399"},{"text":"@cameron_ja57097 Wisdom is to the mind what health is to the body.❓\uD83D\uDD16\uD83D\uDC3E","edit_history_tweet_ids":["1881890350442467543"],"created_at":"2025-01-22T02:23:18.000Z","id":"1881890350442467543"}],"meta":{"newest_id":"1881890354183672128","oldest_id":"1881890350442467543","result_count":10,"next_token":"b26v89c19zqg8o3frr9quqr93bz1vtylrbaqt4dcdu8hp"}}
     */

    private String extractUrl(String text) {
//        System.out.println("El texto de entrada es: " + text);
        if (text == null || text.isEmpty()) {
            return null;
        }

        // Expresión regular para detectar URLs
        String urlRegex = "https?://\\S+";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(text);

        // Retornar la primera URL encontrada, si existe

//        System.out.println("El texto de salida es: " + matcher.find());
        if (matcher.find()) {
//            System.out.println("Con: " + matcher.group());
            return matcher.group();
        }
        return null;
    }

    private String sanitizeUsername(String username) {
        if (username == null) return "";

        // Sanitizar el nombre de usuario, eliminando caracteres no permitidos
        String sanitized = username.replaceAll("[^A-Za-z0-9_]", "");

        // Limitar la longitud del nombre de usuario a 15 caracteres
        return sanitized.length() > 15 ? sanitized.substring(0, 15) : sanitized;
    }
}
// Respuesta JSON obtenida en https://docs.x.com/x-api/users/user-search
    /**
{
  "data": [
    {
      "created_at": "2013-12-14T04:35:55Z",
      "id": "2244994945",
      "name": "X Dev",
      "protected": false,
      "username": "TwitterDev"
    }
  ],
  "errors": [
    {
      "detail": "<string>",
      "status": 123,
      "title": "<string>",
      "type": "<string>"
    }
  ],
  "includes": {
    "media": [
      {
        "height": 1,
        "media_key": "<string>",
        "type": "<string>",
        "width": 1
      }
    ],
    "places": [
      {
        "contained_within": [
          "f7eb2fa2fea288b1"
        ],
        "country": "United States",
        "country_code": "US",
        "full_name": "Lakewood, CO",
        "geo": {
          "bbox": [
            -105.193475,
            39.60973,
            -105.053164,
            39.761974
          ],
          "geometry": {
            "coordinates": [
              -105.18816086351444,
              40.247749999999996
            ],
            "type": "Point"
          },
          "properties": {},
          "type": "Feature"
        },
        "id": "f7eb2fa2fea288b1",
        "name": "Lakewood",
        "place_type": "city"
      }
    ],
    "polls": [
      {
        "duration_minutes": 5042,
        "end_datetime": "2023-11-07T05:31:56Z",
        "id": "1365059861688410112",
        "options": [
          {
            "label": "<string>",
            "position": 123,
            "votes": 123
          }
        ],
        "voting_status": "open"
      }
    ],
    "topics": [
      {
        "description": "All about technology",
        "id": "<string>",
        "name": "Technology"
      }
    ],
    "tweets": [
      {
        "author_id": "2244994945",
        "created_at": "Wed Jan 06 18:40:40 +0000 2021",
        "id": "1346889436626259968",
        "text": "Learn how to use the user Tweet timeline and user mention timeline endpoints in the X API v2 to explore Tweet https://t.co/56a0vZUx7i",
        "username": "XDevelopers"
      }
    ],
    "users": [
      {
        "created_at": "2013-12-14T04:35:55Z",
        "id": "2244994945",
        "name": "X Dev",
        "protected": false,
        "username": "TwitterDev"
      }
    ]
  },
  "meta": {
    "next_token": "<string>",
    "previous_token": "<string>"
  }
}
     */

    // Respuesta JSON obtenida en https://docs.x.com/x-api/posts/recent-search
    /**
     {
  "data": [
    {
      "author_id": "2244994945",
      "created_at": "Wed Jan 06 18:40:40 +0000 2021",
      "id": "1346889436626259968",
      "text": "Learn how to use the user Tweet timeline and user mention timeline endpoints in the X API v2 to explore Tweet https://t.co/56a0vZUx7i",
      "username": "XDevelopers"
    }
  ],
  "errors": [
    {
      "detail": "<string>",
      "status": 123,
      "title": "<string>",
      "type": "<string>"
    }
  ],
  "includes": {
    "media": [
      {
        "height": 1,
        "media_key": "<string>",
        "type": "<string>",
        "width": 1
      }
    ],
    "places": [
      {
        "contained_within": [
          "f7eb2fa2fea288b1"
        ],
        "country": "United States",
        "country_code": "US",
        "full_name": "Lakewood, CO",
        "geo": {
          "bbox": [
            -105.193475,
            39.60973,
            -105.053164,
            39.761974
          ],
          "geometry": {
            "coordinates": [
              -105.18816086351444,
              40.247749999999996
            ],
            "type": "Point"
          },
          "properties": {},
          "type": "Feature"
        },
        "id": "f7eb2fa2fea288b1",
        "name": "Lakewood",
        "place_type": "city"
      }
    ],
    "polls": [
      {
        "duration_minutes": 5042,
        "end_datetime": "2023-11-07T05:31:56Z",
        "id": "1365059861688410112",
        "options": [
          {
            "label": "<string>",
            "position": 123,
            "votes": 123
          }
        ],
        "voting_status": "open"
      }
    ],
    "topics": [
      {
        "description": "All about technology",
        "id": "<string>",
        "name": "Technology"
      }
    ],
    "tweets": [
      {
        "author_id": "2244994945",
        "created_at": "Wed Jan 06 18:40:40 +0000 2021",
        "id": "1346889436626259968",
        "text": "Learn how to use the user Tweet timeline and user mention timeline endpoints in the X API v2 to explore Tweet https://t.co/56a0vZUx7i",
        "username": "XDevelopers"
      }
    ],
    "users": [
      {
        "created_at": "2013-12-14T04:35:55Z",
        "id": "2244994945",
        "name": "X Dev",
        "protected": false,
        "username": "TwitterDev"
      }
    ]
  },
  "meta": {
    "newest_id": "<string>",
    "next_token": "<string>",
    "oldest_id": "<string>",
    "result_count": 123
  }
}
     */