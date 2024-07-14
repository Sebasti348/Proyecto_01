package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocenteMapDTO {
	@Mapping(source="legajo", target="legajo")
	@Mapping(source="nombre", target="nombre")
	@Mapping(source="apellido", target="apellido")
	@Mapping(source="email", target="email")
	@Mapping(source="estado", target="estado")
	
	DocenteDTO convertirDocenteADocenteDTO(Docente d);
	@InheritConfiguration
	Docente convertirDocenteDTOADocente(DocenteDTO ddto);
	List<DocenteDTO> convertirListaDocentesAListaDocentesDTO (List<Docente> listaD);
	List<Docente> convertirListaDocentesDTOAListaDocentes (List<DocenteDTO> listaDDTO);
}
