package cl.nessfit.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudes")
public class Solicitud implements Serializable {

	private static final long serialVersionUID = 4507764205332784955L;
	@Id
	private int id;
	
	private String rutUsuario;
	
	private String nombreCentro;
	
	private int totalPagar;
	
	public int getId() {
		return id;
	}
	public String getRutUsuario() {
		return rutUsuario;
	}
	
	public String getNombreCentro() {
		return nombreCentro;
	}
	
	public int getTotalPagar() {
		return totalPagar;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario=rutUsuario;
	}
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro=nombreCentro;
	}
	public void setTotalPagar(int totalPagar) {
		this.totalPagar=totalPagar;
	}
}
