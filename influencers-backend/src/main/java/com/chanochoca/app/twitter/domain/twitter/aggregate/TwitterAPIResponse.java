package com.chanochoca.app.twitter.domain.twitter.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TwitterAPIResponse {

    @JsonProperty("data")
    private Users data;
    @JsonProperty("errors")
    private List<Errors> errors;
    @JsonProperty("includes")
    private Includes includes;
    @JsonProperty("meta")
    private Meta meta;

    public Users getData() {
        return data;
    }

    public void setData(Users data) {
        this.data = data;
    }

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    public Includes getIncludes() {
        return includes;
    }

    public void setIncludes(Includes includes) {
        this.includes = includes;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
