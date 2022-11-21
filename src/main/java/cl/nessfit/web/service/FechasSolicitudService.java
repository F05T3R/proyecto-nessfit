package cl.nessfit.web.service;

import java.util.List;

import cl.nessfit.web.model.FechasSolicitud;

public interface FechasSolicitudService {

	public void guardar(FechasSolicitud fechasSolicitud);
	public List<String> listarIns(String nombre);
}
