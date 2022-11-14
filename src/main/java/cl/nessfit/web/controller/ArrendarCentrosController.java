zpackage cl.nessfit.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import cl.nessfit.web.model.FechasSolicitud;
import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CSolicitudService;

import cl.nessfit.web.service.CUsuarioService;

@Controller
public class ArrendarCentrosController {

	@Autowired
    CSolicitudService solicitudService;
	
	@Autowired
    CUsuarioService usuarioService;

	
	 
	  @GetMapping("/ArrendarCentro")
	    public String FormObtener(Model model) {
		return "cliente/ArrendarCentros";
	    }
	  @PostMapping("/ArrendarCentro")
	    public String FormArrendarSolcitud(HttpServletRequest request, @Valid Solicitud solicitud ,BindingResult result, Model model) {
		 // DetalleSolicitud detalleSolicitud = new DetalleSolcitud();
	  if(request.getParameterValues("dia") != null) {
		  for(String dia : request.getParameterValues("dia")) {
			  System.out.println(dia);
		  }
		  
	  } 
		// 1.- usuario logeado
			Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		  
		  return "redirect:ArrendarCentro";
	  }
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	
	@Autowired
	CFechasSolicitudService fechasSolicitudService;
	
	 
	@RequestMapping(value="/ArrendarCentro", method=RequestMethod.GET)
    public String ArrendarCentro(Model model) {
		
	 	List<InstalacionDeportiva> lista = InstalacionDeportivaService.crearLista();
	 	 
		model.addAttribute("AllInstalaciones", lista);
    	return "cliente/ArrendarCentros";
    }

	@RequestMapping(value="/ArrendarCentro", method=RequestMethod.POST)
	public String formArrendarCentro(HttpServletRequest request, Model model, String nombre) {
		// DetalleSolicitud detalleSolicitud = new DetalleSolcitud();
		int contador = 0;

		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);

		Solicitud solicitud = new Solicitud();
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());

		if (request.getParameterValues("dia") != null) {
			for (String dia : request.getParameterValues("dia")) {
				System.out.println(dia);
				contador++;
			}
		}
		solicitud.setNombreCentro(ins.getNombre());
		solicitud.setEstado(0);
		solicitud.setTotalPagar((int) ins.getCostoArriendo() * contador);
		solicitud.setRutUsuario(usuario.getRut());
		
		solicitudService.guardar(solicitud);
		Solicitud solicitud2 = solicitud;
		
		if (request.getParameterValues("dia") != null) {
			for (String dia : request.getParameterValues("dia")) {
				FechasSolicitud fecha = new FechasSolicitud();
				fecha.setIdSolicitud(solicitud2.getId());
				fecha.setFecha(dia);
				fechasSolicitudService.guardar(fecha);
			}
		}
		return "redirect:ArrendarCentro";
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
