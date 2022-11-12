package cl.nessfit.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_solicitud")
public class DetalleSolicitud implements Serializable {
	private static final long serialVersionUID = 4507764205332784955L;
	@Id
	private int idSolicitud;
	@Id
	private String fecha;
	
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public String getIdFecha() {
		return fecha;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud=idSolicitud;
	}
	public void setId(String fecha) {
		this.fecha=fecha;
	}	
}
