package cl.nessfit.web.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.service.CUsuarioService;

@Controller
@RequestMapping(value="/administrativo")
public class EditarDatosInstalacionDeportiva {
	
	
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@GetMapping("/editarPrueba")
	public String editarInstalacion(Model model) {
		List<InstalacionDeportiva> lista = InstalacionDeportivaService.listar();
		model.addAttribute("AllInstalaciones", lista);
		return "/administrativo/editarPrueba";
	}
	
	@PostMapping("/editarPrueba")
	public String edidarDatosForm(@Valid InstalacionDeportiva instalacion, BindingResult result, RedirectAttributes attr) {
		System.out.println("2");
		return "";
	}
	
	@PostMapping("/editar/{nombre}")
	public ModelAndView mostrarFormularioEditar(@PathVariable String nombre) {
		System.out.println("1");
		ModelAndView modelo = new ModelAndView("editar_Instalacion");
		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		model.addAttribute("instalacionDeportiva", ins);
		model.addAttribute("tiposInstalaciones", TipoInstalacion.values());
		
		return modelo;
	}
	
	
	@RequestMapping(value = {"/editar/{nombre}"}, method = RequestMethod.POST)
	public String formEditar(@Valid InstalacionDeportiva instalacion, BindingResult result, RedirectAttributes attr, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("tiposInstalaciones", TipoInstalacion.values());
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
	@GetMapping("/editar/{nombre}")
	public String retornarForm(@PathVariable String nombre,Model model) {
		System.out.println("1");
		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		model.addAttribute(ins);
		return "administrativo/editar/form";
	}
		*/
}
