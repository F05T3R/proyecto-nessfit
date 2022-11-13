package cl.nessfit.web.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.nessfit.web.model.InstalacionDeportiva;


public interface InstalacionDeportivaRepository extends JpaRepository<InstalacionDeportiva, String>{
	
	public InstalacionDeportiva findByNombre(String nombre);
	public List<InstalacionDeportiva> findAll();
	//@Query ("SELECT * FROM nessfit.instalacionesdeportivas n where n.estado = 1")
	//public List<InstalacionDeportiva> findOperative();
}
