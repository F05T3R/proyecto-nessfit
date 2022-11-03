package cl.nessfit.web.model;

public enum TipoInstalacion {
	CANCHA ("cancha"),
	GIMNASIO ("gimnasio"),
	PISCINA ("piscina"),
	QUINCHO ("quincho"),
	ESTADIO ("estadio"); 
	
	private final String tipo;
	
	TipoInstalacion(String tipo){
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
	
	
}
