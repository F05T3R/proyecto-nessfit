package cl.nessfit.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.nessfit.web.model.FechasSolicitud;

public interface FechasSolicitudRepository extends JpaRepository<FechasSolicitud, Integer>{
	
	@Query(value="select f.fecha from fechassolicitud f where idSolicitud in(select id from solicitudes where nombreCentro=:nombre and (estado=0 or estado=1))",nativeQuery=true)
	public List<String> listarFechasInstalacion(String nombre);
	public List<FechasSolicitud> findByIdSolicitud(Integer id);
}
