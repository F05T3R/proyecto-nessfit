package cl.nessfit.web.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cl.nessfit.web.model.FechasSolicitud;
import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CFechasSolicitudService;
import cl.nessfit.web.service.CSolicitudService;
import cl.nessfit.web.service.CUsuarioService;

@Controller

public class VerSolicitudes {
	@Autowired
	CSolicitudService solicitudService;
	
	@Autowired
	CUsuarioService usuarioService;
	
	@Autowired
	CFechasSolicitudService fechasService;
	
	@RequestMapping("/administrativo/verSolicitudes")
	public String verSolicitudes(Model model) {
		List<Solicitud> listaSol = solicitudService.listarPendientes();
		model.addAttribute("lista", listaSol);
		
		
		return "administrativo/verSolicitudes";
	}
	
	@RequestMapping("/cliente/verSolicitudes")
	public String verSolicitudesCliente(Model model) {
		Usuario usuario = usuarioService.buscarPorRut(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Solicitud> listaSol = solicitudService.buscarRut(usuario.getRut());
		model.addAttribute("lista", listaSol);
		
		
		return "cliente/verSolicitudes";
	}
	
	
	@RequestMapping(value = {"administrativo/verSolicitud/{id}"}, method = RequestMethod.GET)
	public String mostrarFormularioEditar(@PathVariable(value = "id") Integer id, Model model) {
		System.out.println("1");
		
		List<FechasSolicitud> listaFechas = fechasService.listarFechas(id);
		Solicitud soli = solicitudService.buscarPorId(id);
		model.addAttribute("lista", listaFechas);
		model.addAttribute("solicitud", soli);
		
		
		return "/administrativo/verSolicitud-id";
	}
	
	@RequestMapping(value = {"cliente/verSolicitud/{id}"}, method = RequestMethod.GET)
	public String mostrarFormularioVisualizarCliente(@PathVariable(value = "id") Integer id, Model model) {
		System.out.println("1");
		
		List<FechasSolicitud> listaFechas = fechasService.listarFechas(id);
		Solicitud soli = solicitudService.buscarPorId(id);
		model.addAttribute("lista", listaFechas);
		model.addAttribute("solicitud", soli);
		
		
		return "/cliente/verSolicitud-id";
	}
	
	@RequestMapping(value = "administrativo/confirmar/{id}")
	public String confirmarSolicitud(@PathVariable(value = "id") Integer id, Model model) {
		
		Solicitud solicitud = solicitudService.buscarPorId(id);
		
		solicitud.setEstado(1);
		
		solicitudService.guardar(solicitud);
		
		return "redirect:/administrativo/verSolicitudes";
	}
	
	@RequestMapping(value = "administrativo/cancelar/{id}")
	public String cancelarSolicitud(@PathVariable(value = "id") Integer id, Model model) {
		
		Solicitud solicitud = solicitudService.buscarPorId(id);
		
		solicitud.setEstado(-1);
		
		solicitudService.guardar(solicitud);
		
		return "redirect:/administrativo/verSolicitudes";
	}
	
}

