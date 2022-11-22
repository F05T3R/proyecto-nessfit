package cl.nessfit.web;



import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;
import cl.nessfit.web.repository.InstalacionDeportivaRepository;



public class InstalacionDeportivaRepositoryTests {
	
	@Autowired
	InstalacionDeportivaRepository data;
	
	InstalacionDeportiva insPrueba;
	
	
	
	
	@Test
	 void testGuardarInstalacion() {
		
		InstalacionDeportiva ins = new InstalacionDeportiva();
		ins.setNombre("Test");
		ins.setDireccion("Direccion1");
		ins.setCostoArriendo(1459);
		ins.setEstado(0);
		ins.setTipo(TipoInstalacion.GIMNASIO);
		
		InstalacionDeportiva insGuardada = data.save(ins);
		
		Assertions.assertNotNull(insGuardada);
		
		//assertThat(insGuardada).isNotNull();
		
	}
	
	
	@Test
	void testListarInstalaciones() {
		InstalacionDeportiva ins = new InstalacionDeportiva();
		ins.setNombre("Test_2");
		ins.setDireccion("Direccion2");
		ins.setCostoArriendo(3400);
		ins.setEstado(0);
		ins.setTipo(TipoInstalacion.QUINCHO);
		
		data.save(ins);
		
		//data.save(insPrueba);
		
		List<InstalacionDeportiva> lista = data.findAll();
		
		assertThat(lista).isNotNull();
		assertThat(lista.size()).isEqualTo(1);
		
		
	}
	
}