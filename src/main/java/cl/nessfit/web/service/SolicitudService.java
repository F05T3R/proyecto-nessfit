package cl.nessfit.web.service;

import cl.nessfit.web.model.Solicitud;

public interface SolicitudService {

	public void guardar(Solicitud solicitud);
	public Solicitud buscarPorId(int id);
	public void eliminar(int id);
}
