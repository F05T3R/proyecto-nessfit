package cl.nessfit.web.controller;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CSolicitudService;
import cl.nessfit.web.service.CUsuarioService;


@Controller
@RequestMapping(value="/administrativo/estadisticas")
public class DesplegarEstadisticas {
	
	@Autowired
	CUsuarioService data;
	
	@Autowired
	CSolicitudService solicitudService;
	
	
	@GetMapping(value="")
	public String estadistica(Model model,
		    @RequestParam(name = "inicio", required = false, defaultValue = "1000-01-01") String inicio,
		    @RequestParam(name = "fin", required = false, defaultValue = "2999-01-01") String fin)
		    throws ParseException {
		
	int contadorCancha = 0;
	int contadorGimnasio = 0;
	int contadorPiscina = 0;
	int contadorQuincho = 0;
	int contadorEstadio = 0;
	List<Solicitud> solicitudes = solicitudService.listarPorFechas(inicio, fin);

	for (Solicitud solicitud : solicitudes) {
		  switch (solicitud.getInstalacion().getTipo()) {
		  case CANCHA:
			  contadorCancha++;
			  break;
		  case GIMNASIO:
			  contadorGimnasio++;
			  break;
		  case PISCINA:
			  contadorPiscina++;
			  break;
		  case QUINCHO:
			  contadorQuincho++;
			  break;
		  case ESTADIO:
			  contadorEstadio++;
			  break;
		  default:
		  break;
		    }
	}
	System.out.println(contadorCancha + ", " + contadorGimnasio + "," + contadorPiscina + ", " + contadorQuincho + ", " + contadorEstadio);
	model.addAttribute("solicitudes", solicitudes);
	model.addAttribute("cuentaCancha", contadorCancha);
	model.addAttribute("cuentaGimnasio", contadorGimnasio);
	model.addAttribute("cuentaPiscina", contadorPiscina);
	model.addAttribute("cuentaQuincho", contadorQuincho);
	model.addAttribute("cuentaEstadio", contadorEstadio);

	model.addAttribute("inicio", inicio);
	model.addAttribute("fin", fin);
		
		
		
		
	return "administrativo/verEstadisticas";	
		
	}
	
	/*
	
	@RequestMapping(value="/add")
	public String escogerFechas(@RequestParam String fechaInicio,@RequestParam String fechaTermino, Model model) {
		System.out.println(fechaInicio);
		System.out.println(fechaTermino);
		Usuario usuario = data.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Solicitud> listaSol = solicitudService.listarPorFechas(usuario.getRut(), fechaInicio, fechaTermino);
		ArrayList<String> listaNombres = new ArrayList<String>(); 
		ArrayList<Integer> listaCantidad = new ArrayList<Integer>(); 
		listaNombres.add("CANCHA");
		listaNombres.add("GIMNASIO");
		listaNombres.add("PISCINA");
		listaNombres.add("QUINCHO");
		listaNombres.add("ESTADIO");
		Integer contador = 0;
		for(int i = 0; i<listaNombres.size();i++) {
			
			for(int j = 0; j<listaSol.size();j++) {
				
				if(listaNombres.get(i) == listaSol.get(j).getInstalacion().getTipo().name()) {
					contador++;
				}
			}
			listaCantidad.add(contador);
			contador=0;
			
		}
		System.out.println(listaNombres);
		System.out.println(listaCantidad);
		
		model.addAttribute("Cancha", listaCantidad.get(0));
		model.addAttribute("Gimnasio", listaCantidad.get(1));
		model.addAttribute("Piscina", listaCantidad.get(2));
		model.addAttribute("Quincho", listaCantidad.get(3));
		model.addAttribute("Estadio", listaCantidad.get(4));
		
		
		model.addAttribute("lista", listaSol);
		return "cliente/desplegarEstadisticas";
	}
	
	*/
	
	
	
	
	
	
	
}
