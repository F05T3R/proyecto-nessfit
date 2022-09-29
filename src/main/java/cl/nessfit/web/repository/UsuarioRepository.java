package cl.nessfit.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.nessfit.web.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
}
