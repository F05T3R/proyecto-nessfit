package cl.nessfit.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.nessfit.web.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	// SELECT * FROM usuarios u INNER JOIN r.id ON u.id_rol = r.id;
    public List<Usuario> findByRolId(int rol);

    // SELECT * FROM usuarios u WHERE u.rut = rut;
    public Usuario findByRut(String rut);
    
    public Usuario findByEmail(String email);
    
    @Query(value = "Select * from usuarios u where u.id_rol != 1", nativeQuery = true)
    public List<Usuario> findList();
    
    @Query(value="Select * from usuarios u where s.rut like =:rutBuscar%", nativeQuery=true)
    public List<Usuario> findListByRut(String rutBuscar);
}
