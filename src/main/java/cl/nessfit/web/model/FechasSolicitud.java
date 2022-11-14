package cl.nessfit.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "fechasSolicitud")
@IdClass(FechasSolicitudPK.class)
public class FechasSolicitud implements Serializable {
	private static final long serialVersionUID = 4507764205332784955L;
	@Id
	private int idSolicitud;
	@Id
	private String fecha;
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
		
}
