package cl.nessfit.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.nessfit.web.model.InstalacionDeportiva;


public interface InstalacionDeportivaRepository extends JpaRepository<InstalacionDeportiva, String>{
	
	public InstalacionDeportiva findByNombre(String nombre);
	public List<InstalacionDeportiva> findAll();
}
