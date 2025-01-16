package com.chanochoca.app.twitter.domain.twitter.aggregate;

import java.util.List;

public class TwitterInfluencerDTO {

    private String timeRange;
    private String influencerName;
    private int claimsPerInfluencer;
    private int productsPerInfluencer;
    private boolean includeRevenueAnalysis;
    private boolean verifyWithScientificJournals;
    private List<String> selectedJournals;
    private String notesForAssistant;

    public TwitterInfluencerDTO() {
    }

    public TwitterInfluencerDTO(String timeRange, String influencerName, int claimsPerInfluencer, int productsPerInfluencer, boolean includeRevenueAnalysis, boolean verifyWithScientificJournals, List<String> selectedJournals, String notesForAssistant) {
        this.timeRange = timeRange;
        this.influencerName = influencerName;
        this.claimsPerInfluencer = claimsPerInfluencer;
        this.productsPerInfluencer = productsPerInfluencer;
        this.includeRevenueAnalysis = includeRevenueAnalysis;
        this.verifyWithScientificJournals = verifyWithScientificJournals;
        this.selectedJournals = selectedJournals;
        this.notesForAssistant = notesForAssistant;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getInfluencerName() {
        return influencerName;
    }

    public void setInfluencerName(String influencerName) {
        this.influencerName = influencerName;
    }

    public int getClaimsPerInfluencer() {
        return claimsPerInfluencer;
    }

    public void setClaimsPerInfluencer(int claimsPerInfluencer) {
        this.claimsPerInfluencer = claimsPerInfluencer;
    }

    public int getProductsPerInfluencer() {
        return productsPerInfluencer;
    }

    public void setProductsPerInfluencer(int productsPerInfluencer) {
        this.productsPerInfluencer = productsPerInfluencer;
    }

    public boolean isIncludeRevenueAnalysis() {
        return includeRevenueAnalysis;
    }

    public void setIncludeRevenueAnalysis(boolean includeRevenueAnalysis) {
        this.includeRevenueAnalysis = includeRevenueAnalysis;
    }

    public boolean isVerifyWithScientificJournals() {
        return verifyWithScientificJournals;
    }

    public void setVerifyWithScientificJournals(boolean verifyWithScientificJournals) {
        this.verifyWithScientificJournals = verifyWithScientificJournals;
    }

    public List<String> getSelectedJournals() {
        return selectedJournals;
    }

    public void setSelectedJournals(List<String> selectedJournals) {
        this.selectedJournals = selectedJournals;
    }

    public String getNotesForAssistant() {
        return notesForAssistant;
    }

    public void setNotesForAssistant(String notesForAssistant) {
        this.notesForAssistant = notesForAssistant;
    }
}
