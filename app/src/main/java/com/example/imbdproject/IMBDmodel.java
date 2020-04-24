package com.example.imbdproject;

public class IMBDmodel {

    private String title;
    private String year;
    private String plot;
    private String released;
    private String awards;
    private String poster;
    private String imdbRating;

    public IMBDmodel() {
    }

    public IMBDmodel(String title, String year, String plot, String released, String awards, String poster, String imdbRating) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.released = released;
        this.awards = awards;
        this.poster = poster;
        this.imdbRating = imdbRating;
    }

    public IMBDmodel(String title, String plot, String released, String awards, String poster, String imdbRating) {
        this.title = title;
        this.plot = plot;
        this.released = released;
        this.awards = awards;
        this.poster = poster;
        this.imdbRating = imdbRating;
    }

    public IMBDmodel(String plot, String released, String awards, String poster, String imdbRating) {
        this.plot = plot;
        this.released = released;
        this.awards = awards;
        this.poster = poster;
        this.imdbRating = imdbRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }
}
