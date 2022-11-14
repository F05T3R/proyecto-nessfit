package cl.nessfit.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<InstalacionDeportiva> listar() {
		return (List<InstalacionDeportiva>)InstalacionDeportivaRepository.findAll();
	}

	@Override
	public void eliminar(String nombre) {
		InstalacionDeportivaRepository.deleteById(nombre);
		
	}

}
