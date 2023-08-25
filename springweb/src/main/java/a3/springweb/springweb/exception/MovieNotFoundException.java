package a3.springweb.springweb.exception;

public class MovieNotFoundException extends RuntimeException {
    
    /**
     * Exception that is thrown when a movie with given id doesn't exist
     *
     * @param id movie id
     */
    public MovieNotFoundException(int id) {
        super("Movie with id " + id + " doesn't exist");
    }
}
