package cl.nessfit.web.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
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
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.utils.ValidacionInstalacion;

@Controller
@RequestMapping(value="/administrativo")
public class EditarDatosInstalacionDeportiva {
	
	
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@Autowired
	private ValidacionInstalacion validacion;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
    	binder.addValidators(validacion);
    }
	
	@GetMapping("/editarPrueba")
	public String editarInstalacion(Model model) {
		List<InstalacionDeportiva> lista = InstalacionDeportivaService.listar();
		model.addAttribute("AllInstalaciones", lista);
		return "/administrativo/editarPrueba";
	}
	
	
	@RequestMapping(value = {"/editar/{nombre}"}, method = RequestMethod.GET)
	public String mostrarFormularioEditar(@PathVariable(value = "nombre") String nombre, Model model) {
		System.out.println("1");
		
		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		model.addAttribute("instalacionDeportiva", ins);
		
		return "/administrativo/editar_Instalacion";
	}
	
	
	@RequestMapping(value = {"/editar/{nombre}"}, method = RequestMethod.POST)
	public String formEditar(@Valid InstalacionDeportiva instalacion, BindingResult result, RedirectAttributes attr, Model model) {
		
		if (result.hasErrors()) {
		    return "/administrativo/editar_Instalacion";
		}
		
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
