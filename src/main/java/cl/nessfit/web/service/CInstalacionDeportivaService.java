package cl.nessfit.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.repository.InstalacionDeportivaRepository;

@Service
public class CInstalacionDeportivaService implements InstalacionDeportivaService {

	@Autowired
	private InstalacionDeportivaRepository InstalacionDeportivaRepository;
	
	@Override
	public void guardar(InstalacionDeportiva Instalacion) {
		
		InstalacionDeportivaRepository.save(Instalacion);
		
	}

	@Override
	public InstalacionDeportiva buscarPorNombre(String nombre) {
		InstalacionDeportiva instalacion = InstalacionDeportivaRepository.findByNombre(nombre);
		return instalacion;
		
		
	}

	@Override
	public Page<InstalacionDeportiva> listar(Pageable pageable) {
		return InstalacionDeportivaRepository.findAll(pageable);
	}
	
	@Override
	public List<InstalacionDeportiva> crearLista() {
		return (List<InstalacionDeportiva>)InstalacionDeportivaRepository.findAll();
	}

}
