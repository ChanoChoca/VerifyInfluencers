package com.chanochoca.app.twitter.domain.twitter.vo;

public class Options {

    private String label;
    private Long position;
    private Long votes;

    public Options() {
    }

    public Options(String label, Long position, Long votes) {
        this.label = label;
        this.position = position;
        this.votes = votes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }
}
