package a3.springweb.springweb.exception;

public class FranchiseNotFoundException extends RuntimeException  {
    
    /**
     * Exception that is thrown when a franchise with given id doesn't exist
     *
     * @param id franchise id
     */
    public FranchiseNotFoundException(int id) {
        super("Franchise with id " + id + " doesn't exist");
    }
}
