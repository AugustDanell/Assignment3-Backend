package a3.springweb.springweb.model.dtos.franchise;
import java.util.Set;

import lombok.Data;

@Data
public class FranchiseDTO {
    
    private int id;

    private String name;

    private String description;

    private Set<Integer> movies;
}
