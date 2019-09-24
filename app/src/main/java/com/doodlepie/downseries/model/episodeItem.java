package com.doodlepie.downseries.model;

public class episodeItem {

    public String episode_name;
    public String seasonId;
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public episodeItem() {
    }

    public episodeItem(String episode_name, String seasonId) {
        this.episode_name = episode_name;
        this.seasonId = seasonId;
    }

    public String getEpisode_name() {
        return episode_name;
    }

    public void setEpisode_name(String episode_name) {
        this.episode_name = episode_name;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }
}
