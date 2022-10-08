package cl.nessfit.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.Usuario;
import cl.nessfit.web.repository.UsuarioRepository;

@Service
public class CUsuarioService implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);

    }
    @Override
    public List<Usuario> mostrarAdministrativos() {
    return usuarioRepository.findByRolId(2);
    }

    @Override
    public void eliminar(String rutUsuario) {
        usuarioRepository.deleteById(rutUsuario);

    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorRut(String rut) {
    Usuario usuario = usuarioRepository.findByRut(rut);
    return usuario;
    }
    
    @Override
    public Usuario buscarPorEmail(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email);
    return usuario;
    }
}
