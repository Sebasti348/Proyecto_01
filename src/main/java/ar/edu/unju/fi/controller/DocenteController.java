package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;
import jakarta.validation.Valid;

@Controller
 
public class DocenteController {
	@Autowired
	Docente nuevoDocenteDTO;
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", nuevoDocenteDTO);
		modelView.addObject("flag", false);
		return modelView;
	}

	
	@PostMapping("/guardarDocente")
	public ModelAndView saveDocente(@Valid@ModelAttribute("nuevoDocente") Docente docenteParaGuardar,BindingResult result) {
		ModelAndView modelView 	= new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		try {
			if (result.hasErrors()) {
				modelView.setViewName("formDocente");
				modelView.addObject("flag", false);
			}
			else {
				docenteService.guardarDocente(docenteParaGuardar);	
			}
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());	
		return modelView;		
	}
	@GetMapping("/listadoDocentes")
	public ModelAndView getFormListaDocentes(){
		
		ModelAndView modelView= new  ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		return modelView;
	}
	@GetMapping("/eliminarDocente/{legajo}")
	public ModelAndView deleteCarreraDelListado(@PathVariable (name="legajo") String legajo) {
		
		//borrar
		docenteService.borrarDocente(legajo);
		//mostrar nueva lista de docentes
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocentes",docenteService.mostrarDocentes());
		return modelView;
	}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView getFormModificarDocente(@PathVariable(name = "legajo") String legajo) {

		Docente docente = docenteService.buscarDocente(legajo);
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", docente);
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		modelView.addObject("flag", true);
		return modelView;
	}

	@PostMapping("/modificarDocente")	
	public ModelAndView updateDocente(@Valid @ModelAttribute("nuevoDocente") Docente docenteModificado,BindingResult result) {
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		try {
			if (result.hasErrors()) {
				modelView.addObject("nuevoDocente", docenteModificado);
				modelView.setViewName("formDocente");
				modelView.addObject("flag", false);
				
			} else {
				docenteService.modificarDocente(docenteModificado);
			}
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());

		}
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());

		return modelView;
	}
	
	
		
}
