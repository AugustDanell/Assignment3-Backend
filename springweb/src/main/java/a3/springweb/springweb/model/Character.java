package a3.springweb.springweb.model;

import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Character {

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

    @ManyToMany
    @JoinTable(name="character_movie", joinColumns = {@JoinColumn(name="character_id")}, inverseJoinColumns = {
        @JoinColumn(name="movie_id")
    })
    private Set<Movie> movies; 
}