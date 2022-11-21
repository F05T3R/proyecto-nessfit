package cl.nessfit.web.controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="cliente/EscogerCentro", method=RequestMethod.GET)
	public String EscogerCentro( Model model, Pageable pageable) {
		Page<InstalacionDeportiva> lista = InstalacionDeportivaService.listar(pageable);
		List<InstalacionDeportiva> lis = lista.toList();
		int contador = 0;
		model.addAttribute("AllInstalaciones", lista);
		for(int i = 0; i < lis.size(); i++) {
			if(lis.get(i).getEstado() == 0) {
				contador++;
			}
		}
		
		model.addAttribute("cont", contador);
		model.addAttribute("total", (int)lis.size());
		System.out.println(contador);
		
		return "/cliente/EscogerCentro";
	}
	
	@RequestMapping(value = {"cliente/ArrendarCentrosPrueba"})
	public String mostrarFormularioArrendar(@RequestParam String nombre, Model model) throws ParseException {
		System.out.println(nombre);
		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		if(ins == null) {
			return "redirect:/MenuPrincipal";
		}
		
		List<String> lista = fechasSolicitudService.listarIns(nombre);
		ArrayList<Date> listaFechas = new ArrayList<Date>();
		//System.out.println(lista);
		for(int i = 0; i<lista.size(); i++) {
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(lista.get(i));
			listaFechas.add(fecha);
			/*int anio = fecha.getYear()+1900;
			int dia = fecha.getDate();
			int mes = fecha.getMonth()+1;
			String fecha2 =  + dia + "/" + mes + "/" + anio ;
			System.out.println(fecha2);
			*/
			
			
		}
		System.out.println(listaFechas);
		model.addAttribute("listaFechasInstalacion", listaFechas);
		model.addAttribute("instalacionDeportiva", ins);
		System.out.println(ins.getNombre());
		return "/cliente/ArrendarCentros"; 
	}
	@RequestMapping(value="cliente/ArrendarCentros")
	public String formArrendarCentro(@RequestParam String nombre, RedirectAttributes attr, Model model ,HttpServletRequest request) {
		System.out.println("1");
		InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);
		System.out.println(ins.getNombre());
		int contador = 0; 
		System.out.println(contador);
		//InstalacionDeportiva ins = InstalacionDeportivaService.buscarPorNombre(nombre);

		Solicitud solicitud = new Solicitud();
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());

		if (request.getParameterValues("dia") != null) {
			for (String dia : request.getParameterValues("dia")) {
				System.out.println(dia);  
				contador++;
			}
		}
		
		System.out.println(contador);
		if(contador == 0) {
			System.out.println("5");
			return "redirect:/cliente/EscogerCentro";
		}
		
		System.out.println("6");
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
		return "redirect:/MenuPrincipal";
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

