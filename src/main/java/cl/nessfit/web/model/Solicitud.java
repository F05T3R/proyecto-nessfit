package cl.nessfit.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudes")
public class Solicitud implements Serializable {

	private static final long serialVersionUID = 4507764205332784955L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rutUsuario", referencedColumnName = "rut")
	private Usuario usuario;
	
	private String nombreCentro;
	
	private int totalPagar;
	
	private String fechaCompra;
	
	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	private int estado;
	
	public int getId() {
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}
	
	public int getTotalPagar() {
		return totalPagar;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro=nombreCentro;
	}
	public void setTotalPagar(int totalPagar) {
		this.totalPagar=totalPagar;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
