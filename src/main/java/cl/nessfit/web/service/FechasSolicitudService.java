package cl.nessfit.web.service;

import cl.nessfit.web.model.FechasSolicitud;

public interface FechasSolicitudService {

	public void guardar(FechasSolicitud fechasSolicitud);

	void eliminar(FechasSolicitud delete);

	FechasSolicitud buscar(int id, String fecha);
}
