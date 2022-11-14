package cl.nessfit.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.nessfit.web.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud,String> {

	public Solicitud findById(int id);
	
}
