package cl.nessfit.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.nessfit.web.model.InstalacionDeportiva;

public interface InstalacionDeportivaService {
	
	public void guardar(InstalacionDeportiva Instalacion);
	public InstalacionDeportiva buscarPorNombre(String nombre);
	public Page<InstalacionDeportiva> listar(Pageable pageable);
}
