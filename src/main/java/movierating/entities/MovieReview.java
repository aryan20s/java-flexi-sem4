package movierating.entities;

public class MovieReview {
    private final String personName, reviewText;
    private final Movie movie;
    private final double rating;

    public MovieReview(String personName, String reviewText, Movie movie, double rating) {
        this.personName = personName;
        this.reviewText = reviewText;
        this.movie = movie;
        this.rating = rating;
    }

    public String getPersonName() {
        return personName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getRating() {
        return rating;
    }
}
