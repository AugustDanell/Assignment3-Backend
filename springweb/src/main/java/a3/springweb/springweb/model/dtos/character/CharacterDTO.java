package a3.springweb.springweb.model.dtos.character;
import java.util.Set;

import lombok.Data;

@Data
public class CharacterDTO {
    
    private int id;

    private String name;

    private String alias;

    private String gender;

    private String url;

    private Set<Integer> movies;
}
