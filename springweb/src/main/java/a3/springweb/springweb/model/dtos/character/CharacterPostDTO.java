package a3.springweb.springweb.model.dtos.character;

import lombok.Data;

@Data
public class CharacterPostDTO {
    
    private int id;

    private String name;

    private String alias;

    private String gender;

    private String url;
}
