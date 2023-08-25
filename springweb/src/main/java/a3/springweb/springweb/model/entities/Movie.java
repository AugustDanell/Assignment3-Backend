package a3.springweb.springweb.model.entities;

import jakarta.persistence.Entity;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 50)
    private String genre;

    @Column(length = 50, nullable = false)
    private int year;

    @Column(length = 50, nullable = false)
    private String director;

    @Column()
    private String picture;
    
    @Column()
    private String trailer;

    @ManyToMany
    @JoinTable(
    name="character_movie", 
    joinColumns = {@JoinColumn(name="movie_id")}, 
    inverseJoinColumns = {@JoinColumn(name="character_id")
    })
    private Set<MovieCharacter> characters;

    @ManyToOne()
    @JoinColumn(name="franchise_id")
    private Franchise franchise;

    // Getters:
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getPicture(){
        return picture;
    }

    public String getTrailer() {
        return trailer;
    }

    @JsonIgnoreProperties("movies")
    public Set<MovieCharacter> getCharacters() {
        return characters;
    }

    @JsonIgnoreProperties("movies")
    public Franchise getFranchise() {
        return franchise;
    }

    // Setters:
    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setPicture(String picture){
        this.picture = picture;
    }

    public void setTrailer(String trailer){
        this.trailer = trailer;
    }

    public void setCharacters(Set<MovieCharacter> characters){
        System.out.println(characters.size());
        this.characters = characters;
    }

    public void setFranchise(Franchise franchise){
        this.franchise = franchise;
    }

    /**
     * shallowCopyNotNull()
     * Takes in a new movie and copies its values onto this old movie, provided that they are not null (Shallow).
     * @param newMovie, The movie entity corresponding to the movie that is to update this movie.
     */
    
    public void shallowCopyNotNull(Movie newMovie){
        
        // Not-Nullable:
        this.title = newMovie.getTitle();
        this.year = newMovie.getYear();
        this.director = newMovie.getDirector();

        if(newMovie.getGenre() != null){
            this.genre = newMovie.getGenre();
        }

        if(newMovie.getPicture() != null){
            this.picture = newMovie.getPicture();
        }

        if(newMovie.getTrailer() != null){
            this.trailer = newMovie.getTrailer();
        }
    }
}