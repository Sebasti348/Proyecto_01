package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
@Entity

public class Materia {
	@Id  
	@Column(name = "materia_codigo")
	@Size(min = 3, max = 20, message = "El código debe tener entre 3 y 20 caracteres.")
	private String codigo;
	
	@Column(name = "nombre", nullable = false)
	@Pattern(regexp = "^[a-zA-Záéíóú ]*$", message = "El nombre solo debe contener letras.")
	@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
	private String nombre;

	@Column(name = "curso", nullable = false)
	@Size(min = 3, max = 100, message = "El curso debe tener entre 3 y 100 caracteres.")
	private String curso;

	@Column(name = "cantidad_horas", nullable = false)
	@Min(3)
	@Max(10)
	private Integer cantidadHoras;

	@Enumerated(EnumType.STRING)
	@Column(name = "modalidad", nullable = false)
	private Modalidad modalidad;

    @Autowired
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_id")
    private Carrera carrera; 
	
	
	@Column(name = "estado", nullable = false)
	private Boolean estado;

	@OneToOne
	@JoinColumn(name = "docente", referencedColumnName = "legajo")
	private Docente docente;
		
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Alumno> alumnos;
}