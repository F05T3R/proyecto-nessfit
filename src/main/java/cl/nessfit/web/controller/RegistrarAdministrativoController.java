package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.Rol;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.utils.validacionUsuario;

@Controller
@RequestMapping(value="/administrador")
public class RegistrarAdministrativoController {

	@Autowired
    CUsuarioService usuarioService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @RequestMapping(value="/RegistrarAdministrativo", method=RequestMethod.GET)
    public String RegistrarAdministrativo(Usuario usuario) {
    	return "administrador/RegistrarAdministrativo";
    }
    @RequestMapping(value="/RegistrarAdministrativo", method=RequestMethod.POST)
    public String formCrearUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {

	// paso 1 validaciones
	//result.rejectValue("rut", null, "rut inválido");
    	
    	Usuario existe = usuarioService.buscarPorRut(usuario.getRut());

    if (existe != null) {
    	result.rejectValue("rut", null, "Rut existente en la base de datos");
    }
    	
	if (result.hasErrors()) {
	    return "/administrador/RegistrarAdministrativo";
	}

	// paso 2 set atributos no ingresados por usuario
	usuario.setContrasena(passwordEncoder.encode(usuario.getRut()));
	usuario.setEstado(1);
	Rol rolAdministrativo = new Rol();
	rolAdministrativo.setId(2);
	usuario.setRol(rolAdministrativo);
	System.out.println(usuario.toString());

	// paso 3 persistencia 
	usuarioService.guardar(usuario);

	// paso 4 redireccionamiento
	return "redirect:RegistrarAdministrativo";
    }

    @ModelAttribute("rutUser")
    public String auth() {
    	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
