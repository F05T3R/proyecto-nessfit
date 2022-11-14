package cl.nessfit.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.nessfit.web.model.FechasSolicitud;

public interface FechasSolicitudRepository extends JpaRepository <FechasSolicitud, String>{
	public FechasSolicitud findByIdSolicitudAndFecha(int id, String fecha);
}
