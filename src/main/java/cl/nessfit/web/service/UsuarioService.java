package cl.nessfit.web.service;

import java.util.List;

import cl.nessfit.web.model.Usuario;

public interface UsuarioService {
	
	public void guardar(Usuario usuario);
	
	public void eliminar(String rutUsuario);
	
	public List<Usuario> listar();
	
	public List<Usuario> mostrarAdministrativos();
	
	public Usuario buscarPorRut(String rut);
	
	public Usuario buscarPorEmail(String email);
}
