package ar.edu.unju.fi.model;



import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Component
@Data
@Entity

public class Docente {
    @Id
    @Column(name = "legajo")
    @Size(min = 3, max = 20, message = "El código debe tener entre 3 y 20 caracteres.")
    private String  legajo;
    
    @Column(name = "nombre", nullable = false)
	@Pattern(regexp = "^[a-zA-Záéíóú ]*$", message = "Solo se permiten letras y espacios")
	@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String nombre;
    
    @Column(name = "apellido", nullable = false)
	@Pattern(regexp = "^[a-zA-Záéíóú ]*$", message = "Solo se permiten letras y espacios")
	@Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres.")
    private String apellido;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "telefono", nullable = false)
    @Size(min=10, max=10,message="Ingrese un telefono valido (10 caracteres)")
    private String telefono;
    
    @Column(name = "estado", nullable = false)
    private boolean estado;
}