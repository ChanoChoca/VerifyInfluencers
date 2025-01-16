package com.chanochoca.app.twitter.domain.twitter.aggregate;

import java.util.List;

public class TwitterAPIResponse {

    private List<Users> data;
    private List<Errors> errors;
    private Includes includes;
    private Meta meta;
}
