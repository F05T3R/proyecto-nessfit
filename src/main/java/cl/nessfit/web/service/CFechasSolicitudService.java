package cl.nessfit.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.FechasSolicitud;
import cl.nessfit.web.repository.FechasSolicitudRepository;

@Service

public class CFechasSolicitudService implements FechasSolicitudService {

	@Autowired
	private FechasSolicitudRepository fechasSolicitudRepository;
	
	@Override
	public void guardar(FechasSolicitud fechasSolicitud) {
		fechasSolicitudRepository.save(fechasSolicitud);
		
	}

	@Override
	public List<String> listarIns(String nombre) {
		return fechasSolicitudRepository.listarFechasInstalacion(nombre);
	}

	@Override
	public List<FechasSolicitud> listarFechas(Integer id) {
		return fechasSolicitudRepository.findByIdSolicitud(id);
	}

	
}
