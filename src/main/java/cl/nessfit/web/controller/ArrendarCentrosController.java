package cl.nessfit.web.controller;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cl.nessfit.web.model.FechasSolicitud;
import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.repository.FechasSolicitudRepository;
import cl.nessfit.web.repository.SolicitudRepository;
import cl.nessfit.web.service.CFechasSolicitudService;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.service.CSolicitudService;
import cl.nessfit.web.service.CUsuarioService;

@Controller
public class ArrendarCentrosController {

	private int num_id;

	@Autowired
    CUsuarioService usuarioService;
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@Autowired
	CSolicitudService solicitudService;
	
	@Autowired
	CFechasSolicitudService fechasSolicitudService;
	
	@RequestMapping(value="/ArrendarCentro", method=RequestMethod.GET)
	public String ArrendarCentro(Model model, Pageable pageable) {
			
	Page<InstalacionDeportiva> lista = InstalacionDeportivaService.listar(pageable);
		 	
		 	/*for(int i = 0; i<lista.getTotalElements(); i++) {
		 		if(lista.getContent().get(i).getEstado() == 0) {
		 			lista.getContent().remove(i);
		 		}
		 	}
		 	*/
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
		
		if(contador == 0) {
			return "redirect:/MenuPrincipal";
		}
		
		Calendar fechaHoy = Calendar.getInstance();
		int añoHoy = fechaHoy.get(Calendar.YEAR);
        int mesHoy = fechaHoy.get(Calendar.MONTH);
        int diaHoy = fechaHoy.get(Calendar.DAY_OF_MONTH);
        String fechaCompra = añoHoy + "-" + (mesHoy+1) + "-" + diaHoy;
        //System.out.println(fechaHoy);
		//System.out.println(añoHoy + "-" + (mesHoy+1) + "-" + diaHoy);
		//System.out.println(fechaCompra);
		
		solicitud.setNombreCentro(ins.getNombre());
		solicitud.setEstado(0);
		System.out.println(ins.getCostoArriendo());
		solicitud.setTotalPagar((int)(ins.getCostoArriendo() * contador));
		solicitud.setRutUsuario(usuario.getRut());
		solicitud.setFechaCompra(fechaCompra);	  
		
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

