package cl.nessfit.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.service.CInstalacionDeportivaService;
import cl.nessfit.web.utils.ValidacionInstalacion;
import cl.nessfit.web.utils.validacionUsuario;
import cl.nessfit.web.service.CUsuarioService;


@Controller
@RequestMapping(value="/administrativo")
public class RegistrarInstalacionDeportivaController {
	
	@Autowired
	CInstalacionDeportivaService InstalacionDeportivaService;
	
	@Autowired
	private ValidacionInstalacion validacion;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
    	binder.addValidators(validacion);
    }
	
	@RequestMapping(value="/RegistarInstalacionDeportiva", method=RequestMethod.GET)
    public String RegistrarInstalacionDeportiva(Model model) {
		model.addAttribute(new InstalacionDeportiva());
		model.addAttribute("tiposInstalaciones", TipoInstalacion.values());
		
    	return "administrativo/RegistarInstalacionDeportiva";
    }
	
	@RequestMapping(value="/RegistarInstalacionDeportiva", method=RequestMethod.POST)
	public String formCrearInstalacion(@Valid InstalacionDeportiva instalacion, BindingResult result, RedirectAttributes attr, Model model) {
		
		
		
		
		if (result.hasErrors()) {
			model.addAttribute("tiposInstalaciones", TipoInstalacion.values());
		    return "/administrativo/RegistarInstalacionDeportiva";
		    
		}
		
		
		
		/*if(instalacion.getTipo() == TipoInstalacion.CANCHA.getTipo()) {
			instalacion.setTipo(TipoInstalacion.CANCHA.getTipo());
		}
		*/
		InstalacionDeportivaService.guardar(instalacion);
		
		
		return "redirect:RegistarInstalacionDeportiva";
	}    
    @ModelAttribute("rolUser")
    public String rol() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get()
        .getAuthority();
    }
}
