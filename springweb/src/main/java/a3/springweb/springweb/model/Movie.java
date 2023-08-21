package a3.springweb.springweb.model;

import jakarta.persistence.Entity;

import java.util.Set;

import org.hibernate.grammars.hql.HqlParser.FrameClauseContext;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import a3.springweb.springweb.model.Franchise;

@Entity
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

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private Set<MovieCharacter> characters;

    @ManyToOne()
    @JoinColumn(name="franchise_id")
    private Franchise franchise;


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

}