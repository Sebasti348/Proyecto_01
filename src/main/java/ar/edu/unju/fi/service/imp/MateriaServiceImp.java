package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService {
    
    private static final Logger logger = LoggerFactory.getLogger(MateriaServiceImp.class);
    
    @Autowired
    MateriaRepository materiaRepository;    
    
    @Autowired
    MateriaMapDTO materiaMapDto;
    
    @Override
    public void guardarMateria(Materia materia) {
        logger.info("Guardando materia: {}", materia.getCodigo());
        materia.setEstado(true);
        materiaRepository.save(materia);
    }

    @Override
    public List<MateriaDTO> mostrarMaterias() {
        logger.info("Mostrando todas las materias activas.");
        return materiaMapDto.convertirListaMateriaAListaMateriaDTO(materiaRepository.findMateriasByEstado(true));
    }

    @Override
    public void borrarMateria(String codigo) {
        logger.info("Borrando materia con código: {}", codigo);
        List<Materia> materias = materiaRepository.findAll();
        materias.forEach(materia -> {
            if(materia.getCodigo().equals(codigo)) {
                materia.setEstado(false);
                materiaRepository.save(materia);
                logger.info("Materia borrada correctamente: {}", codigo);
            }
        });
    }

    @Override
    public Materia buscarMateria(String codigo) {
        logger.info("Buscando materia con código: {}", codigo);
        List<Materia> todasLasMaterias = materiaRepository.findAll();
        for (Materia materia : todasLasMaterias) {
            if(materia.getCodigo().equals(codigo)) {
                logger.info("Materia encontrada: {}", codigo);
                return materia;
            }
        }
        logger.info("Materia no encontrada: {}", codigo);
        return null;
    }

    @Override
    public void modificarMateria(Materia materia) {
    	materia.setEstado(true);
        logger.info("Modificando materia: {}", materia.getCodigo());
        materiaRepository.save(materia);
    }

}
