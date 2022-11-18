package cl.nessfit.web.service;

import java.util.List;

import cl.nessfit.web.model.Solicitud;

public interface SolicitudService {

	public void guardar(Solicitud solicitud);
	public Solicitud buscarPorId(int id);
	public void eliminar(int id);
	public List<Solicitud> buscarRut(String rutCompra);
}
