package cl.nessfit.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class CambiarEstadoUsuario {
	
	@Autowired
	CUsuarioService usuarioService; 
	
	@RequestMapping(value="listarUsuarios")
	public String formCambio(Model model) {
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Usuario> listar;
		
		if(usuario.getRol().getId() == 2) {
			listar = usuarioService.mostrarClientes();
		}
		else {
			listar = usuarioService.mostrarTodos();
		}
		model.addAttribute("listaUsuarios", listar);
		return "listarUsuarios";
	}
	
	
	
	@RequestMapping("/buscarUsuario")
	public String busquedaRut(@RequestParam() String rutBuscar, Model model) {
		if(rutBuscar.isBlank() || rutBuscar.isEmpty()) {
			return "redirect:/listarUsuarios";
		}
		List<Usuario> lista;
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if(usuario.getRol().getId() == 2) {
			lista = usuarioService.listarAdministrativo(rutBuscar);
		}
		else if(usuario.getRol().getId() == 1) {
			lista = usuarioService.listarAdministrador(rutBuscar);;
		}
		else {
			return "MenuPrincipal";
		}
		 
		model.addAttribute("listaUsuarios", lista);
		return "/listarUsuarios";
	}
	
	
	@RequestMapping(value="/cambioEstado/{rut}")
	public String cambiarEstado(@PathVariable(value="rut") String rut ,Model model) {
		System.out.println(rut);
		Usuario usuario = usuarioService.buscarPorRut(rut);
		if(usuario == null) {
			return "MenuPrincipal";
			//result.rejectValue("rut ", rut);
		}
		
		int estado = usuario.getEstado();
		if(estado == 1) {
			usuario.setEstado(0);
		}
		else if(estado == 0) {
			usuario.setEstado(1);
		}
		else {
			return "MenuPrincipal";
		}
		
		usuarioService.guardar(usuario);
		
		return "redirect:/listarUsuarios";
	}
	
	 @ModelAttribute("rutUser")
	    public String auth() {
	    	Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
	    	return usuario.getNombre();
	    }
	    
	  @ModelAttribute("rolUser")
	    public String rol() {
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get()
	        .getAuthority();
	    }
	
	
	
}
