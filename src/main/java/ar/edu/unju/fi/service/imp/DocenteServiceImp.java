package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService {

    private static final Logger logger = LoggerFactory.getLogger(DocenteServiceImp.class);

    @Autowired
    DocenteRepository docenteRepository; 

    @Autowired
    DocenteMapDTO docenteMapDTO;

    @Override
    public void guardarDocente(Docente docente) {
    	docente.setEstado(true);
        logger.info("Guardando docente: {}", docente.getLegajo());
        docenteRepository.save(docente);
    }

    @Override
    public List<DocenteDTO> mostrarDocentes() {
        logger.info("Mostrando todos los docentes activos.");
        return docenteMapDTO.convertirListaDocentesAListaDocentesDTO(docenteRepository.findDocenteByEstado(true)); 
    }

    @Override
    public void borrarDocente(String lu) {
        logger.info("Borrando docente con legajo: {}", lu);
        List<Docente> docentes = docenteRepository.findAll();
        docentes.forEach(docente -> {
            if(docente.getLegajo().equals(lu)) {
                docente.setEstado(false);
                docenteRepository.save(docente);
                logger.info("Docente borrado correctamente: {}", lu);
            }
        });
    }

    @Override
    public void modificarDocente(Docente docenteDTOModificado) {
    	docenteDTOModificado.setEstado(true);
        logger.info("Modificando docente: {}", docenteDTOModificado.getLegajo());
        docenteRepository.save(docenteDTOModificado);
    }

    @Override
    public Docente buscarDocente(String lu) {
        logger.info("Buscando docente con legajo: {}", lu);
        List<Docente> todosLosDocentes = docenteRepository.findAll();
        for (Docente docente : todosLosDocentes) {
            if(docente.getLegajo().equals(lu)) {
                logger.info("Docente encontrado: {}", lu);
                return docente;
            }
        }
        logger.info("Docente no encontrado: {}", lu);
        return null;
    }

}
