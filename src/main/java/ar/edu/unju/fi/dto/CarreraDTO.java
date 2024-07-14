package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CarreraDTO {
	private String codigo;
	private String nombre;
	private int cantidadAnios;
	private Boolean estado;
}