package a3.springweb.springweb.mappers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import a3.springweb.springweb.model.dtos.movie.MovieDTO;
import a3.springweb.springweb.model.dtos.movie.MoviePostDTO;
import a3.springweb.springweb.model.dtos.movie.MovieUpdateDTO;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    // Mappings from DTO to movie.
    public abstract Movie moviePostDtoToMovie(MoviePostDTO moviePostDTO);
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
