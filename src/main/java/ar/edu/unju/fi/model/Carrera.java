package ar.edu.unju.fi.model;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
@Entity
public class Carrera {
	
	@Id
	@NotBlank(message="Ingresar código de la Carrera")
	@Size(min=1, max=10,message="longitud de código no valida")
	private String codigo;
	@NotBlank(message="Ingresar Nombre de la Carrera")
	@Size(min=3, max=40,message="El nombre debe contener minimo 3 Caracteres y 20 como maximo")
	@Pattern(regexp = "^[a-zA-Záéíóú ]*$", message = "Solo se permiten letras y espacios")
	private String nombre;
	@NotNull
	@Min(value=2, message=" Se requiere un número minimo de 2")
	@Max(value=8, message=" Se requiere un numero maximo de 8")
	private int cantidadAnios;
	
	@OneToMany(mappedBy = "carrera")
	private List<Alumno> alumnos;
	
	@OneToMany(mappedBy = "carrera")
	private List<Materia> materia;
	
	private Boolean estado;
}