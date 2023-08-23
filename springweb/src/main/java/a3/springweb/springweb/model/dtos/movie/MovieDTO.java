package a3.springweb.springweb.model.dtos.movie;
import java.util.Set;

import lombok.Data;

@Data
public class MovieDTO {

    private int id;

    private String title;

    private String genre;

    private int year;

    private String director;

    private String picture;

    private String trailer;

    private Set<Integer> characters;
    
    private Integer franchise;
    
}
