package com.chanochoca.app.twitter.domain.twitter.vo;

import java.time.Instant;
import java.util.List;

public class Polls {

    private Long durationMinutes;
    private Instant endDatetime;

    private String id;
    private List<Options> options;
    private String votingStatus;

    public Polls() {
    }

    public Polls(Long durationMinutes, Instant endDatetime, String id, List<Options> options, String votingStatus) {
        this.durationMinutes = durationMinutes;
        this.endDatetime = endDatetime;
        this.id = id;
        this.options = options;
        this.votingStatus = votingStatus;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Instant getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Instant endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public String getVotingStatus() {
        return votingStatus;
    }

    public void setVotingStatus(String votingStatus) {
        this.votingStatus = votingStatus;
    }
}
