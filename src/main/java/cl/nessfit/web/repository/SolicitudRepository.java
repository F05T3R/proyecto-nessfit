package cl.nessfit.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.nessfit.web.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud,String> {

	@Query(value="select * from solicitudes s where s.rutUsuario=:rutCompra", nativeQuery = true)
	public List<Solicitud> buscarPorRut(String rutCompra);
	@Query(value = "select s.id, s.fechaCompra, s.estado, s.nombreCentro, s.rutUsuario, s.totalPagar, u.nombre from solicitudes s inner join usuarios u on s.rutUsuario=u.rut where s.estado = 0 order by s.fechaCompra asc", nativeQuery = true)
	public List<Solicitud> listarPendientes();
	public Solicitud findById(int id);
	@Query(value="select * from solicitudes s where (s.fechaCompra BETWEEN :fechaInicio and :fechaTermino) and s.estado != -1 ", nativeQuery = true)
	public List<Solicitud> listarDespliegue(String fechaInicio, String fechaTermino);
	
}
