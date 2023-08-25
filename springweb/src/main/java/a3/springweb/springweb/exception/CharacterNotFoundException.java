package a3.springweb.springweb.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException {

    /**
     * Exception that is thrown when a character with given id doesn't exist
     *
     * @param id character id
     */
    public CharacterNotFoundException(int id) {
        super("Character with id " + id + " doesn't exist");
    }
    
}
