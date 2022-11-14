package cl.nessfit.web.service;

import cl.nessfit.web.model.FechasSolicitud;

public interface FechaSolicitudService {
	public void guardar(FechasSolicitud fechasSolicitud);
	public FechasSolicitud buscar(int id, String fecha);
	public void eliminar(FechasSolicitud delete);
}
