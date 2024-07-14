package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

@Service
public interface DocenteService {
	
	public void guardarDocente(Docente  docenteDTO);
	public List<DocenteDTO> mostrarDocentes();
	public void borrarDocente(String lu);
	public void modificarDocente(Docente docenteDTOModificado);
	public Docente buscarDocente(String lu);
}