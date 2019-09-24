package com.doodlepie.downseries.model;

public class seasonItem  {

    public String season_image;
    public String categoryId;
    public String season_name;
    public String series;

    public seasonItem() {
    }

    public seasonItem(String season_image, String categoryId, String season_name, String series) {
        this.season_image = season_image;
        this.categoryId = categoryId;
        this.season_name = season_name;
        this.series = series;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public String getSeason_image() {
        return season_image;
    }

    public void setSeason_image(String season_image) {
        this.season_image = season_image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
