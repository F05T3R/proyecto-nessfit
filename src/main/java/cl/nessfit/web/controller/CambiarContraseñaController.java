package cl.nessfit.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class CambiarContraseñaController {

	 @Autowired
	    private CUsuarioService usuarioService;

	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	    @GetMapping("/CambiarContraseña")
	    public String cambiarContrasenaForm(Model model) {
		model.addAttribute("nuevaContrasena", "");
		model.addAttribute("nuevaContrasenaRepetir", "");
		return "cambiarContraseña";
	    }
	    
	    @PostMapping("/CambiarContraseña")
	    public String enviarForm(@RequestParam String nuevaContrasena, @RequestParam String nuevaContrasenaRepetir,
		    HttpServletRequest request, RedirectAttributes attr, Model model) {

		// 1.- usuario logeado
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());

		// 2.- validar contraseñas iguales, usuario no null, contraseña mayor a 10 y
		// menor a 15 caracteres

		if (nuevaContrasena.length()< 10 || nuevaContrasena.length()>15) {
		    model.addAttribute("msg", "Contraseña incorrecta");
		    model.addAttribute("nuevaContrasena", nuevaContrasena);
		    model.addAttribute("nuevaContrasenaRepetir", nuevaContrasenaRepetir);
		    return "cambiarContraseña";
		}
		if(nuevaContrasena != nuevaContrasenaRepetir) {
			model.addAttribute("msg2", "Contraseña incorrecta");
		    model.addAttribute("nuevaContrasena", nuevaContrasena);
		    model.addAttribute("nuevaContrasenaRepetir", nuevaContrasenaRepetir);
		    return "cambiarContraseña";
		}

		// 3.- set usuario
		usuario.setContrasena(passwordEncoder.encode(nuevaContrasena));

		// 4.- persistencia
		usuarioService.guardar(usuario);

		// 5.- redirección
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	    }

	    @ModelAttribute("rutUser")
	    public String auth() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	    }
}
