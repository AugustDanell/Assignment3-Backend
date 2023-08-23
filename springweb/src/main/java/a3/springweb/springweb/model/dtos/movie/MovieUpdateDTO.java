package a3.springweb.springweb.model.dtos.movie;

import lombok.Data;

@Data
public class MovieUpdateDTO {

    private int id;
    
    private String title;

    private String genre;

    private String year;

    private String director;

    private String picture;

    private String trailer;

}
