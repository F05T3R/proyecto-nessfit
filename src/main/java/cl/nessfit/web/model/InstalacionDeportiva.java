package cl.nessfit.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

@Entity
@Table(name = "instalacionesDeportivas")

public class InstalacionDeportiva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private String nombre;
	
	private String direccion;
	private TipoInstalacion tipo;
	//@Size(min = 1000, message = "El costo mínimo de arriendo debe ser $1.000")
	private long costoArriendo;
	private Integer estado;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public TipoInstalacion getTipo() {
		return tipo;
	}
	public void setTipo(TipoInstalacion tipo) {
		this.tipo = tipo;
	}
	public long getCostoArriendo() {
		return costoArriendo;
	}
	public void setCostoArriendo(long costoArriendo) {
		this.costoArriendo = costoArriendo;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
