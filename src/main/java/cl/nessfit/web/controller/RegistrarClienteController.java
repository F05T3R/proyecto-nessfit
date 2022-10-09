package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.Rol;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.utils.validacionUsuario;

@Controller
@RequestMapping(value="/administrativo")
public class RegistrarClienteController {

	@Autowired
    CUsuarioService usuarioService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private validacionUsuario validacionUsuario;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	//System.out.println("hola1");
    	binder.addValidators(validacionUsuario);
    }
    
    @RequestMapping(value="/RegistrarCliente", method=RequestMethod.GET)
    public String RegistrarCliente(Usuario usuario) {
    	return "administrativo/RegistrarCliente";
    }
    
    @RequestMapping(value="/RegistrarCliente", method=RequestMethod.POST)
    public String formCrearUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {

	// paso 1 validaciones
	//result.rejectValue("rut", null, "rut inválido");
    	
    Usuario existe = usuarioService.buscarPorRut(usuario.getRut());

    if (existe != null) {
    	result.rejectValue("rut", null, "El RUT y/o correo electrónico ya existen en el sistema. Intente iniciar sesión");
    }
    	
    
	if (result.hasErrors()) {
	    return "/administrativo/RegistrarCliente";
	}

	// paso 2 set atributos no ingresados por usuario
	usuario.setContrasena(passwordEncoder.encode(usuario.getRut()));
	usuario.setEstado(1);
	Rol rolCliente = new Rol();
	rolCliente.setId(3);
	usuario.setRol(rolCliente);
	System.out.println(usuario.toString());

	// paso 3 persistencia 
	usuarioService.guardar(usuario);

	// paso 4 redireccionamiento
	return "redirect:RegistrarCliente";
    }

    @ModelAttribute("rutUser")
    public String auth() {
	// Usuario usuario =
	// usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());

	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
