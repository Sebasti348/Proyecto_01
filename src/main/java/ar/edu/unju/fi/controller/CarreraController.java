package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;


@Controller
public class CarreraController {
	@Autowired
	CarreraDTO nuevaCarreraDTO;
	
	@Autowired
	CarreraService carreraService;

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera", nuevaCarreraDTO);	
		modelView.addObject("flag", false);
		return modelView;
	}

	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraParaGuardar , BindingResult result) {
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		try {
		     if (result.hasErrors()) {
		    	 System.out.println("SE CAPTO UN ERROR DE VALIDACION");
		    	 modelView.setViewName("formCarrera");
		     }
		     else {
		    	 carreraService.guardarCarrera(carreraParaGuardar);
		    	 System.out.println("Carrera guardada");
		    	 modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
		     }
		 }
		 catch(Exception e) {
			 boolean error = true;
			 modelView.addObject("error",error);
			 modelView.addObject("cargarCarreraErrorMessage", "Error de carga en la BD" + e.getMessage());
			 System.out.println(e.getMessage());
		 }
		return modelView;
	}

	@GetMapping("/borrarCarrera/{codigo}")
	public ModelAndView deleteCarreraDelListado(@PathVariable(name = "codigo") String codigo) {
		// borrar
		// ListadoCarreras.eliminarCarrera(codigo);
		carreraService.borrarCarrera(codigo);
		// mostrar el listado
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		// modelView.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());	
		return modelView;
	}

	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView getFormModificarCarrera(@PathVariable(name = "codigo") String codigo) {
		// buscar
		// Carrera carreraParaModificar = ListadoCarreras.buscarCarreraPorCodigo(codigo);
		Carrera carrera = carreraService.buscarCarrera(codigo);
		// mostrar el nuevo formulario
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera", carrera);
		modelView.addObject("flag", true);
		return modelView;
	}
	

	@PostMapping("/modificarCarrera")
	public ModelAndView updateCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraModificada, BindingResult result) {
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		try {
			if (result.hasErrors()) {
				modelView.setViewName("formCarrera");
			} else {
				carreraService.modificarCarrera(carreraModificada);
				modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
			}
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaCarreraErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());

		}
		return modelView;
	}


	
    @GetMapping("/listadoCarreras")
    public ModelAndView showCarreras() {
        // mostrar el listado
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        return modelView;
    
}


}