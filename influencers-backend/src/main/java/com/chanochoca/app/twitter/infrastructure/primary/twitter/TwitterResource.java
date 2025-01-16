package com.chanochoca.app.twitter.infrastructure.primary.twitter;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterInfluencerDTO;
import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterAPIResponse;
import com.chanochoca.app.twitter.application.TwitterApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TwitterResource {

    private final TwitterApplicationService twitterApplicationService;

    public TwitterResource(TwitterApplicationService twitterApplicationService) {
        this.twitterApplicationService = twitterApplicationService;
    }

    @PostMapping("/influencer")
    public ResponseEntity<TwitterAPIResponse> getInfluencer(@RequestBody TwitterInfluencerDTO twitterInfluencerDTO) {
        TwitterAPIResponse twitterResponse = twitterApplicationService.getUser(twitterInfluencerDTO);
        if (twitterResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(twitterResponse);
    }

//    @GetMapping("/api/tweets")
//    public String getRecentTweets(
//            @RequestParam String username,
//            @RequestParam(defaultValue = "5") int maxResults
//    ) {
//        return twitterService.getRecentTweets(username, maxResults);
//    }
}
