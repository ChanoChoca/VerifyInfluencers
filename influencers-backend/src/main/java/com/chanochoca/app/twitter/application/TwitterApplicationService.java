package com.chanochoca.app.twitter.application;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterInfluencerDTO;
import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterAPIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Service
public class TwitterApplicationService {

    @Value("${twitter.api.url}")
    private String twitterApiUrl;

    @Value("${twitter.api.bearer.token}")
    private String bearerToken;

    private final WebClient webClient;

    public TwitterApplicationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(twitterApiUrl).build();
    }

    public TwitterAPIResponse getUser(TwitterInfluencerDTO twitterInfluencerDTO) {
        try {
            TwitterAPIResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(twitterApiUrl + "/users/search")
                            .queryParam("query", twitterInfluencerDTO.getInfluencerName()) // Supongo que buscas por username
                            .build())
                    .header("Authorization", "Bearer " + bearerToken)
                    .retrieve()
                    .bodyToMono(TwitterAPIResponse.class)
                    .block();

            System.out.println(response);
            return response;
        } catch (WebClientResponseException e) {
            throw new RuntimeException("No se pudo obtener información de Twitter.", e);
        }
    }

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
}
