package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;	
	
	@Autowired
	CarreraMapDTO carreraMapDto;
	
	@Override
	public void guardarCarrera(Carrera carrera) {
		log.info("SERVICE: CarreraServiceImp -> guardarCarrera");
		log.info("METHOD: guardarCarrera()");
		log.info("INFO: Guardando Carrera con codigo {}", carrera.getCodigo());
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	}

	@Override
	public List<CarreraDTO> mostrarCarreras() {
		log.info("SERVICE: CarreraServiceImp -> mostrarCarrera");
		log.info("METHOD: mostrarCarrera()");
		return carreraMapDto.convertirListaCarreraAListaCarreraDTO(carreraRepository.findCarrerasByEstado(true));
	}

	@Override
	public void borrarCarrera(String codigo) {
		log.info("SERVICE: CarreraServiceImp -> borrarCarrera");
		log.info("METHOD: borrarCarrera()");
		log.info("INFO: Borrando carrera con codigo {}", codigo);
		List<Carrera> carreras = carreraRepository.findAll();
		carreras.forEach(carrera -> {
			if(carrera.getCodigo().equals(codigo)) {
				carrera.setEstado(false);
				carreraRepository.save(carrera);
			}
		});
	}

	@Override
	public Carrera buscarCarrera(String codigo) { 
		List<Carrera> todasLasCarreras = carreraRepository.findAll();
		for (Carrera carreras : todasLasCarreras) {
			if(carreras.getCodigo().equals(codigo)) {
				log.info("METHOD: buscando Carrera()");
				log.info("Carrera", carreras.getCodigo(),carreras.getNombre());
				return carreras;

			}
		}

		return null;
	}

	@Override
	public void modificarCarrera(Carrera carrera) {
		log.info("SERVICE: CarreraServiceImp -> modificarCarrera");
		log.info("METHOD: modificarCarrera()");
		log.info("INFO: Modificando carrera con codigo {}", carrera.getCodigo());
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	}

}