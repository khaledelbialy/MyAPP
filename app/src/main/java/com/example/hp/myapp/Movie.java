package com.example.hp.myapp;

public class Movie {

    private String image;
    private String title;
    private String overView;
    private String usersRating;
    private String releaseDate;

    public Movie(){

    }
    public Movie(String image, String title, String overView, String usersRating, String releaseDate){
        this.image = image;
        this.title = title;
        this.overView = overView;
        this.usersRating = usersRating;
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }
    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getUsersRating() {
        return usersRating;
    }
    public void setUsersRating(String usersRating) {
        this.usersRating = usersRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
