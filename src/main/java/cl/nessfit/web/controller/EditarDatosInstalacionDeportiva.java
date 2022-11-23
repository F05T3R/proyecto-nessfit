package cl.nessfit.web.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.utils.ValidacionEditarInstalacion;


@Controller
@RequestMapping(value="/administrativo")
public class EditarDatosInstalacionDeportiva {
	
	
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@Autowired
	private ValidacionEditarInstalacion validacion;
	
	@InitBinder("InstalacionDeportiva")
    public void initBinder(WebDataBinder binder) {
    	binder.addValidators(validacion);
    }
	
	@GetMapping("/editarPrueba")
	public String editarInstalacion(Model model, Pageable page) {
		Page<InstalacionDeportiva> lista = InstalacionDeportivaService.listar(page);
		model.addAttribute("AllInstalaciones", lista);
		return "/administrativo/editarPrueba";
	}
	
	
	@RequestMapping(value = {"/editar/{nombre}"}, method = RequestMethod.GET)
	public String mostrarFormularioEditar(@PathVariable(value = "nombre") String nombre, Model model) {
		System.out.println("1");
		
		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		model.addAttribute("instalacionDeportiva", ins);
		model.addAttribute("tiposInstalaciones", TipoInstalacion.values());
		
		return "/administrativo/editar_Instalacion";
	}
	
	
	@RequestMapping(value = {"/editar/{nombre}"}, method = RequestMethod.POST)
	public String formEditar(@Valid InstalacionDeportiva instalacion, BindingResult result, RedirectAttributes attr, Model model) {
		
		System.out.println("2");

		
		if(instalacion.getCostoArriendo() < 1000) {
			
			result.rejectValue("costoArriendo", null, "El costo mínimo de arriendo debe ser $1.000 ");
		}
		
		if(instalacion.getEstado() != 1  && instalacion.getEstado() !=  0 ) {
			result.rejectValue("estado", null, "Estado no válido");
		}
		
		if (result.hasErrors()) {
			model.addAttribute("tiposInstalaciones", TipoInstalacion.values());
		    return "/administrativo/editar_Instalacion";
		}
		
		InstalacionDeportivaService.guardar(instalacion);
		
		return "redirect:/administrativo/editarPrueba";
	}
	
	/*@GetMapping("/editar1")
	public String ejemplo(Model model) {
		System.out.println("1");
		
		return "/administrativo/editarPrueba";
	}
	
	@GetMapping("ejemplo")
	public String xd(Model model) {
		
		return "/administrativo/editarPrueba";
	}
	
	/*
	
	@GetMapping("/editar/")
	public String retornarForm(Model model) {
		System.out.println("1");
		//InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		//model.addAttribute(ins);
		return "editar_Instalacion";
	}
	*/
	
	
}

