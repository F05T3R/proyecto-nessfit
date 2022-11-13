package cl.nessfit.web.controller;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cl.nessfit.web.model.DetalleSolicitud;
import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.nessfit.web.model.FechasSolicitud;
import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.repository.FechasSolicitudRepository;
import cl.nessfit.web.repository.InstalacionDeportivaRepository;
import cl.nessfit.web.repository.SolicitudRepository;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.service.CSolicitudService;

import cl.nessfit.web.service.CUsuarioService;
import cl.nessfit.web.service.InstalacionDeportivaService;

@Controller
public class ArrendarCentrosController {
	private int num_id;
	
	@Autowired
    CUsuarioService usuarioService;

	
	 
	  @GetMapping("/ArrendarCentro")
	    public String FormObtener(Model model) {
		return "cliente/ArrendarCentros";
	    }
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@Autowired
	SolicitudRepository data;
	
	@Autowired
	FechasSolicitudRepository data2;
	
	@Autowired
	CSolicitudService solicitudService;
	
	 @RequestMapping(value="/ArrendarCentro", method=RequestMethod.GET)
	    public String ArrendarCentro(Model model) {
		 	//List<InstalacionDeportiva> lista = InstalacionDeportivaService.listar();
		 	 
			//model.addAttribute("AllInstalaciones", lista);
	    	return "cliente/ArrendarCentros";
	    }
	 
	 @RequestMapping(value="/ArrendarCentro", method=RequestMethod.POST)
	 	public String formArrendarCentro(String nombre, Model model) {
		 
		 
		 InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		 Solicitud solicitud= new Solicitud();
		 Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		 //solicitud.setId(1);
		 solicitud.setNombreCentro(ins.getNombre());
		 solicitud.setTotalPagar((int)ins.getCostoArriendo() *2);
		 solicitud.setRutUsuario(usuario.getRut());
		 Solicitud solicitud2 = data.save(solicitud);
		 //Solicitud solicitud2 = solicitudService.guardar(solicitud);
		 
		 System.out.println(solicitud2.getId());
		 
		 
		 String Date2 = "2022-10-13";
		 FechasSolicitud fecha = new FechasSolicitud();
		 fecha.setIdSolicitud(solicitud2.getId());
		 fecha.setFecha(Date2);
		 data2.save(fecha);
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 return "/MenuPrincipal";
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
