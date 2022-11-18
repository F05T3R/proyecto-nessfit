package cl.nessfit.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.nessfit.web.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud,String> {

	@Query(value="select * from solicitudes s where s.rutUsuario=:rutCompra", nativeQuery = true)
	public List<Solicitud> buscarPorRut(String rutCompra);
	public Solicitud findById(int id);
	
}
