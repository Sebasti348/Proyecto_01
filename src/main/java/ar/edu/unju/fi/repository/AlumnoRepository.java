package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.model.Alumno;

public interface AlumnoRepository extends JpaRepository <Alumno,String>{
	List<Alumno> findAlumnoByEstado(boolean estado);
	List<Alumno> findByMateriasCodigo(String codigo);
}
