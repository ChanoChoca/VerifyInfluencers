package com.chanochoca.app.twitter.domain.twitter.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TweetAPIResponse {

    @JsonProperty("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TweetAPIResponse{" +
                "data=" + data +
                '}';
    }
}
