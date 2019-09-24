package com.doodlepie.downseries.model;

public class seriesItem {

    public String name;
    public String bannerLink;

    public seriesItem() {
    }

    public seriesItem(String name, String bannerLink) {
        this.name = name;
        this.bannerLink = bannerLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }
}
