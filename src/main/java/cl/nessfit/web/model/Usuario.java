package cl.nessfit.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")

public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 4507764205332784955L;
	@Id
	private String rut;
	
	//@Size(min = 3, message = "Los nombres o apellidos deben tener más de 2 caracteres")
	private String nombre;
	//@Size(min = 3, message = "Los nombres o apellidos deben tener más de 2 caracteres")
	private String apellido;
	@Digits(integer = 16,message = "El teléfono móvil ingresado no es válido", fraction = 0)
	private Long telefono;
	@Email(message = "Su correo electrónico no es válido")
	private String email;
	private int estado;
	private String contrasena;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", referencedColumnName = "id")
	private Rol rol;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
