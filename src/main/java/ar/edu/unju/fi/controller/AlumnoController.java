package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
@Controller
public class AlumnoController {
	@Autowired
	Alumno nuevoAlumno;
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	Materia nuevaMateria;
	@Autowired
	MateriaService materiaService;
	@Autowired
	CarreraService carreraService;
	@Autowired
	private CarreraRepository carreraRepository;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", nuevoAlumno);	
		modelView.addObject("flag", false);
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
	
		return modelView;
	}
	
	@GetMapping("/listadoAlumnos")
	public ModelAndView getFormListaAlumno() {
		
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());	
		
		return modelView;	
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoParaGuardar, BindingResult resultado ) {
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());

			try {
				if(resultado.hasErrors()) {
					System.out.println("SE CAPTO UN ERROR DE VALIDACION");
					modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
					modelView.setViewName("formAlumno");
					
				}
				else {
					alumnoParaGuardar.setCarrera(carreraService.buscarCarrera(alumnoParaGuardar.getCarrera().getCodigo()));
					alumnoService.guardarAlumno(alumnoParaGuardar);
					modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
					
					
				}
			}
			catch( Exception e){
				boolean errors = true;
				modelView.addObject("errors", errors);
				modelView.addObject("cargaAlumnoErrorMessage", " Error al cargar en la BD " + e.getMessage());
				System.out.println(e.getMessage());
			}

		return modelView;
	}
	
	@GetMapping("/modificarAlumno/{lu}")
	public ModelAndView getFormModificarAlumno(@PathVariable(name = "lu") String lu) {
		// buscar
		Alumno alumno = alumnoService.buscarAlumno(lu);
		// mostrar el nuevo formulario
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
		modelView.addObject("nuevoAlumno", alumno);
		modelView.addObject("flag", true);
		return modelView;
	}
 
	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoModificado, BindingResult resultado) {
		
			ModelAndView modelView = new ModelAndView("listaDeAlumnos");
			
			try {
				if(resultado.hasErrors()) {
					modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
					modelView.setViewName("formAlumno");
				}
				else {
					//System.out.println("Alumno modificado correctamente  "+nuevoAlumno.getNombre());
					alumnoModificado.setCarrera(carreraService.buscarCarrera(alumnoModificado.getCarrera().getCodigo()));
					alumnoService.modificarAlumno(alumnoModificado);
					modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
				}
			}
			catch( Exception e){
				boolean errors = true;
				modelView.addObject("errors", errors);
				modelView.addObject("cargaAlumnoErrorMessage", " Error al cargar en la BD " + e.getMessage());
				System.out.println(e.getMessage());
			}
			
		return modelView;
			
	} 
				
	@GetMapping("/borrarAlumno/{lu}")
	public ModelAndView deleteAlumnoDelListado(@PathVariable (name="lu") String lu) {
		
		//borrar
		alumnoService.borrarAlumno(lu);
		
		//mostrar nueva lista de alumnos
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumnos());
		
		 return modelView;
	}
	@GetMapping("/alumnosPorCarrera")
    public String mostrarAlumnosPorCarrera(Model model) {
        model.addAttribute("carreras", carreraRepository.findAll());
        return "alumnosPorCarrera";
    }

    @GetMapping("/buscarAlumnosPorCarrera")
    public String buscarAlumnosPorCarrera(@RequestParam("carrera") String codigoCarrera, Model model) {
        Carrera carrera = carreraRepository.findById(codigoCarrera).orElse(null);
        if (carrera != null) {
            model.addAttribute("listadoAlumnos", carrera.getAlumnos());
        }
        model.addAttribute("carreras", carreraRepository.findAll());
        return "alumnosPorCarrera";
    }
}
