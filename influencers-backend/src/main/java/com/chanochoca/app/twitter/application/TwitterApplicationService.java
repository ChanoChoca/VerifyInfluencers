package com.chanochoca.app.twitter.application;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterInfluencerDTO;
import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterAPIResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwitterApplicationService {

    @Value("${twitter.api.url}")
    private String twitterApiUrl;

    @Value("${twitter.api.bearer.token}")
    private String bearerToken;

    public TwitterAPIResponse getUserTweets(TwitterInfluencerDTO twitterInfluencerDTO) {
        try {
            twitterInfluencerDTO.setInfluencerName(sanitizeUsername(twitterInfluencerDTO.getInfluencerName()));

            // Obtener el ID del usuario
            String userFields = "id";
            String userUrl = String.format("%s/users/by/username/%s?user.fields=%s",
                    twitterApiUrl,
                    twitterInfluencerDTO.getInfluencerName(),
                    userFields);

            // Realizar la solicitud HTTP GET para obtener datos del usuario
            HttpResponse<String> userResponse = Unirest.get(userUrl)
                    .header("Authorization", "Bearer " + bearerToken)
                    .asString();

            // Verificar si la respuesta es exitosa
            if (userResponse.getStatus() == 200) {
                // Obtener el ID del usuario del JSON de la respuesta
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                TwitterAPIResponse userApiResponse = mapper.readValue(userResponse.getBody(), TwitterAPIResponse.class);
                String userId = userApiResponse.getData().getId();

                // Buscar tweets recientes relacionados con salud
                String query = "health";  // Palabras clave relacionadas con salud, filtra por autor
                String tweetFields = "created_at,text,author_id,public_metrics";  // Campos adicionales

                // Construir la URL de búsqueda de tweets
                String tweetUrl = String.format("%s/tweets/search/recent?query=%s&tweet.fields=%s",
                        twitterApiUrl, query, tweetFields);

                // Realizar la solicitud HTTP GET para obtener tweets recientes
                HttpResponse<String> tweetResponse = Unirest.get(tweetUrl)
                        .header("Authorization", "Bearer " + bearerToken)
                        .asString();

                // Verificar estado de la respuesta
                if (tweetResponse.getStatus() == 200) {
                    System.out.println(tweetResponse.getBody());
                    // Convertir JSON de tweets en objeto TwitterAPIResponse
//                    return mapper.readValue(tweetResponse.getBody(), TwitterAPIResponse.class);
                    return userApiResponse;
                } else {
                    throw new RuntimeException("Error al consultar los tweets: " + tweetResponse.getBody());
                }
            } else {
                throw new RuntimeException("Error al consultar Twitter API para el usuario: " + userResponse.getBody());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con Twitter API", e);
        }
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