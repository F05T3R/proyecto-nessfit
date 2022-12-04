package cl.nessfit.web.service;

import java.util.List;

import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;

public interface UsuarioService {
	
	public void guardar(Usuario usuario);
	
	public void eliminar(String rutUsuario);
	
	public List<Usuario> listar();
	
	public List<Usuario> mostrarAdministrativos();
	
	public List<Usuario> mostrarClientes();
	
	public Usuario buscarPorRut(String rut);
	
	public Usuario buscarPorEmail(String email);
	
	public List<Usuario> mostrarTodos();
	public List<Usuario> listarParecidos(String rut);
}
