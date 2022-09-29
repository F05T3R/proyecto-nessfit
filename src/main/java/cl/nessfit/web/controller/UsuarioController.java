package cl.nessfit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cl.nessfit.web.model.Rol;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/guardar")
	public String guardar() {
		
		Usuario usuario = new Usuario();
		usuario.setApellido("Felipe");
		usuario.setContrasena("1234");
		usuario.setEmail("Administrador@gmail.com");
		usuario.setEstado(1);
		usuario.setNombre("Felipe");
		usuario.setRut("192644259");
		usuario.setTelefono("1234332");
		
		Rol rol = new Rol();
		rol.setId(1);
		
		usuario.setRol(rol);
		
		usuarioService.guardar(usuario);
		
		return "guardar";
	}
}
