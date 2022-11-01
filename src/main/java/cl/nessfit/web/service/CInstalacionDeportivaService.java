package cl.nessfit.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.repository.InstalacionDeportivaRepository;

@Service
public class CInstalacionDeportivaService implements InstalacionDeportivaService {

	@Autowired
	private InstalacionDeportivaRepository InstalacionDeportivaRepository;
	
	@Override
	public void guardar(InstalacionDeportiva Instalacion) {
		
		InstalacionDeportivaRepository.save(Instalacion);
		
	}

}
