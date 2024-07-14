package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.AlumnoService;
import lombok.extern.slf4j.Slf4j;
@Service
	@Slf4j
public class AlumnoServiceImp implements AlumnoService {
	@Autowired
	AlumnoRepository alumnoRepository;
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	@Autowired
	MateriaRepository materiaRepository;

    @Override
    public void guardarAlumno(Alumno alumno) {
        // Establecer el estado del alumno como activo
        alumno.setEstado(true);
        // Guardar los cambios en la base de datos
        alumnoRepository.save(alumno);
        log.info("Alumno guardado: {}", alumno.getNombre());
    }

    @Override
    public List<AlumnoDTO> mostrarAlumnos() {
        // Obtener la lista de alumnos activos
        List<Alumno> alumnosActivos = alumnoRepository.findAlumnoByEstado(true);
        // Convertir la lista de alumnos a una lista de DTOs
        List<AlumnoDTO> alumnosDTO = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnosActivos);
        log.info("Mostrando {} alumnos activos", alumnosDTO.size());
        return alumnosDTO;
    }

    @Override
    public void borrarAlumno(String lu) {
        log.info("MÉTODO: borrarAlumno()");
        // Obtener todos los alumnos
        List<Alumno> alumnos = alumnoRepository.findAll();
        // Buscar el alumno con el LU proporcionado
        alumnos.forEach(adto -> {
            if (adto.getLu().equals(lu)) {
                // Establecer el estado del alumno como inactivo
                adto.setEstado(false);
                // Guardar los cambios en el repositorio
                alumnoRepository.save(adto);
                log.info("Alumno con LU {} borrado", lu);
            }
        });
    }

    @Override
    public Alumno buscarAlumno(String lu) {
        // Obtener la lista de alumnos activos
        List<Alumno> alumnosActivos = alumnoRepository.findAlumnoByEstado(true);
        // Buscar el alumno con el LU proporcionado
        for (Alumno alumno : alumnosActivos) {
            if (alumno.getLu().equals(lu)) {
                log.info("Alumno encontrado con LU {}: {}", lu, alumno.getNombre());
                return alumno;
            }
        }
        log.info("No se encontró ningún alumno con LU {}", lu);
        return null;
    }

    @Override
    public void modificarAlumno(Alumno alumnoModificado) {
        // Guardar los cambios en el repositorio
    	alumnoModificado.setEstado(false);
        alumnoRepository.save(alumnoModificado);
        log.info("Alumno modificado: {}", alumnoModificado.getNombre());
    }

    @Override
    public void inscribirAlumno(Alumno alumno, Materia materia) {
        // Agregar la materia al alumno y viceversa
        alumno.getMaterias().add(materia);
        materia.getAlumnos().add(alumno);
        // Guardar los cambios en los repositorios
        alumnoRepository.save(alumno);
        materiaRepository.save(materia);
        log.info("Alumno {} inscrito en la materia {}", alumno.getNombre(), materia.getNombre());
    }
    
	@Override
	public List<Alumno> filtrarAlumnos(String codigo) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> filtrarAlumnos()");
		log.info("METHOD: filtrarAlumnos()");
		log.info("INFO: Filtrando alumnos por codigo{}", codigo);
		log.info("INFO: Alumno filtrado {}", codigo);
		return alumnoRepository.findByMateriasCodigo(codigo);
	}


}
