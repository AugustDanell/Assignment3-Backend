package a3.springweb.springweb.mappers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import a3.springweb.springweb.model.dtos.franchise.FranchiseDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchisePostDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchiseUpdateDTO;
import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;


@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {

    // Mappings from DTO to franchise
    public abstract Franchise franchisePostDtoToFranchise(FranchisePostDTO franchisePostDTO);
    public abstract Franchise franchiseUpdateDtoToFranchise(FranchiseUpdateDTO franchiseUpdateDTO);
    
    // Mappings from franchise to DTO
    public abstract Collection<FranchiseDTO> franchiseToFranchiseDto(Collection<Franchise> franchises);
    
    @Mapping(target="movies", source="movies", qualifiedByName = "moviesToMovieIds")
    public abstract FranchiseDTO franchiseToFranchiseDto(Franchise franchise);

    @Named("moviesToMovieIds")
    Set<Integer> mapMoviesToIds(Set<Movie> source) {
        if (source == null) return null;
        return source.stream().map(
                m -> m.getId()
        ).collect(Collectors.toSet());
    }
}
