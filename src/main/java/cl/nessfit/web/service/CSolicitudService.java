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

	@Override
	public Solicitud buscarPorId(int id) {
		return solicitudRepository.findById(id);
	}

	@Override
	public void eliminar(int id) {
		solicitudRepository.delete(buscarPorId(id));
	}
}
