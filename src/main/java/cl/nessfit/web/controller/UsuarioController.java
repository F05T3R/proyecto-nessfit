package cl.nessfit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.Rol;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.service.InstalacionDeportivaService;
import cl.nessfit.web.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CInstalacionDeportivaService InstalacionService;
	
	@GetMapping("/guardar")
	public String guardar() {
		
		/*Usuario usuario = new Usuario();
		usuario.setApellido("Felipe");
		usuario.setContrasena("1234");
		usuario.setEmail("Administrador@gmail.com");
		usuario.setEstado(1);
		usuario.setNombre("Felipe");
		usuario.setRut("192644259");
		
		Rol rol = new Rol();
		rol.setId(1);
		
		usuario.setRol(rol);
		
		usuarioService.guardar(usuario);
		*/
		/*
		InstalacionDeportiva ins = new InstalacionDeportiva();
		ins.setNombre("Inst1");
		ins.setDireccion("Dir1");
		ins.setEstado(1);
		long precio = 2000;
		ins.setCostoArriendo(precio);
		TipoInstalacion tp = TipoInstalacion.ESTADIO;
		ins.setTipo(tp);
		
		InstalacionService.guardar(ins);
		*/
		
		
		return "guardar";
	}
}
