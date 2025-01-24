package com.chanochoca.app.twitter.infrastructure.primary.twitter;

import com.chanochoca.app.twitter.domain.twitter.aggregate.TwitterUser;
import com.chanochoca.app.twitter.domain.twitter.vo.TwitterInfluencerDTO;
import com.chanochoca.app.twitter.domain.twitter.vo.TwitterAPIResponse;
import com.chanochoca.app.twitter.application.TwitterApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/twitter")
public class TwitterResource {

    private final TwitterApplicationService twitterApplicationService;

    public TwitterResource(TwitterApplicationService twitterApplicationService) {
        this.twitterApplicationService = twitterApplicationService;
    }

    @PostMapping("/influencer")
    public ResponseEntity<TwitterUser> getInfluencer(@RequestBody TwitterInfluencerDTO twitterInfluencerDTO) {
        TwitterUser twitterUser = twitterApplicationService.getUserTweets(twitterInfluencerDTO);
        if (twitterUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(twitterUser);
    }

//    @GetMapping("/api/tweets")
//    public String getRecentTweets(
//            @RequestParam String username,
//            @RequestParam(defaultValue = "5") int maxResults
//    ) {
//        return twitterService.getRecentTweets(username, maxResults);
//    }
}
