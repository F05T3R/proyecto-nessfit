package cl.nessfit.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.repository.SolicitudRepository;

@Service
public class CSolicitudService implements SolicitudService {
	@Autowired
	private SolicitudRepository solicitudRepository;
	
	@Override
	public void guardar(Solicitud solicitud) {
		solicitudRepository.save(solicitud);
		
	}
}
