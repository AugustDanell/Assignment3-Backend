package a3.springweb.springweb.mappers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import a3.springweb.springweb.model.dtos.character.CharacterDTO;
import a3.springweb.springweb.model.dtos.character.CharacterPostDTO;
import a3.springweb.springweb.model.dtos.character.CharacterUpdateDTO;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

     // Mappings from DTO to character.
    @Mapping(target = "movies", ignore = true)
    public abstract MovieCharacter characterPostDtoToCharacter(CharacterPostDTO characterDto);

    @Mapping(target = "movies", ignore = true)
    public abstract MovieCharacter characterUpdateDtoToCharacter(CharacterUpdateDTO characterUpdateDTO);

    // Mappings from character to DTO
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDto(MovieCharacter movieCharacter);

    public abstract Collection<CharacterDTO> characterToCharacterDto(Collection<MovieCharacter> movieCharacters);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }
}