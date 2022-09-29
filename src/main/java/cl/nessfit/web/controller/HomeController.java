package cl.nessfit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/InicioSesion", method = RequestMethod.GET)
	public String InicioSesion() {
		return "InicioSesion";
	}
	
	@GetMapping("/Registrar")
	public String Registrar() {
		return "Registrar";
	}
	
	@GetMapping("/MenuPrincipal")
	public String MenuPrincipal() {
		return "MenuPrincipal";
	}
}
