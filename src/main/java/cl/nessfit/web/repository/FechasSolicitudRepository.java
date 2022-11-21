package cl.nessfit.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.nessfit.web.model.FechasSolicitud;

public interface FechasSolicitudRepository extends JpaRepository<FechasSolicitud, String>{
	
	@Query(value="select f.fecha from fechassolicitud f where idSolicitud in(select id from solicitudes where nombreCentro=:nombre)",nativeQuery=true)
	public List<String> listarFechasInstalacion(String nombre);
}
