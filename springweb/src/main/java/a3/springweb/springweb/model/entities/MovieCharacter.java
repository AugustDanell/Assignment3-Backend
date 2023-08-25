package a3.springweb.springweb.model.entities;

// Data structures:
import java.util.Set;

// SpringBoot and Hibernate imports
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "character")
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String alias;

    @Column(length = 50, nullable = false)
    private String gender;

    @Column()
    private String url;

    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies; 

    // Getters:
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAlias(){
        return alias;
    }

    public String getGender(){
        return gender;
    }

    public String getUrl(){
        return url;
    }

    @JsonIgnoreProperties("characters")
    public Set<Movie> getMovies(){
        return movies;
    }

    // Setters:
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAlias(String alias){
        this.alias = alias;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
