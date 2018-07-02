package com.movies.book.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailResponse {


    @Expose
    @SerializedName("vote_count")
    private int vote_count;
    @Expose
    @SerializedName("vote_average")
    private double vote_average;
    @Expose
    @SerializedName("video")
    private boolean video;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("tagline")
    private String tagline;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("spoken_languages")
    private List<Spoken_languagesEntity> spoken_languages;
    @Expose
    @SerializedName("runtime")
    private int runtime;
    @Expose
    @SerializedName("revenue")
    private int revenue;
    @Expose
    @SerializedName("release_date")
    private String release_date;
    @Expose
    @SerializedName("production_countries")
    private List<Production_countriesEntity> production_countries;
    @Expose
    @SerializedName("production_companies")
    private List<Production_companiesEntity> production_companies;
    @Expose
    @SerializedName("poster_path")
    private String poster_path;
    @Expose
    @SerializedName("popularity")
    private double popularity;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("original_title")
    private String original_title;
    @Expose
    @SerializedName("original_language")
    private String original_language;
    @Expose
    @SerializedName("imdb_id")
    private String imdb_id;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("genres")
    private List<GenresEntity> genres;
    @Expose
    @SerializedName("budget")
    private int budget;
    @Expose
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @Expose
    @SerializedName("adult")
    private boolean adult;

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Spoken_languagesEntity> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<Spoken_languagesEntity> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Production_countriesEntity> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<Production_countriesEntity> production_countries) {
        this.production_countries = production_countries;
    }

    public List<Production_companiesEntity> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<Production_companiesEntity> production_companies) {
        this.production_companies = production_companies;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresEntity> genres) {
        this.genres = genres;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public static class Spoken_languagesEntity {
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("iso_639_1")
        private String iso_639_1;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }
    }

    public static class Production_countriesEntity {
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("iso_3166_1")
        private String iso_3166_1;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }
    }

    public static class Production_companiesEntity {
        @Expose
        @SerializedName("origin_country")
        private String origin_country;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("logo_path")
        private String logo_path;
        @Expose
        @SerializedName("id")
        private int id;

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class GenresEntity {
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
