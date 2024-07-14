package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class MateriaDTO {
	private String codigo;
	private String nombre;
	private String curso;
	private Integer cantidadHoras;
	private Docente docente;
	private Carrera carrera;
	private Boolean estado;
}