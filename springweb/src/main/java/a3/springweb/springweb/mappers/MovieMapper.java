package a3.springweb.springweb.mappers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import a3.springweb.springweb.model.dtos.movie.MovieDTO;
import a3.springweb.springweb.model.dtos.movie.MoviePostDTO;
import a3.springweb.springweb.model.dtos.movie.MovieUpdateDTO;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.CharacterService;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Autowired
    protected CharacterService characterService;

    // Mappings from DTO to movie.
    @Mapping(target = "franchise", ignore = true)
    @Mapping(target = "characters", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Movie moviePostDtoToMovie(MoviePostDTO moviePostDTO);

    @Mapping(target = "franchise", ignore = true)
    @Mapping(target = "characters", ignore = true)
    public abstract Movie movieUpdateDtoToMovie(MovieUpdateDTO movieUpdateDTO);

    // Mappings from movie to DTOs
    public abstract Collection<MovieDTO> movieToMovieDto(Collection<Movie> movies);

    @Mapping(target="characters", source="characters", qualifiedByName = "charactersToIds")
    @Mapping(target="franchise", source="franchise.id") 
    public abstract MovieDTO movieToMovieDto(Movie movie);

    @Named("charactersToIds")
    Set<Integer> map(Set<MovieCharacter> source) {
        if (source == null) return null;
        return source.stream().map(ch -> ch.getId()
        ).collect(Collectors.toSet());
    }
}
