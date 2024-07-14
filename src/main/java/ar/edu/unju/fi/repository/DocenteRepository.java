package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.model.Docente;

public interface DocenteRepository extends JpaRepository <Docente,String>{
	List<Docente> findDocenteByEstado(boolean estado);

	

}
