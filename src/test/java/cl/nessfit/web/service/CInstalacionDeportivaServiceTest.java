package cl.nessfit.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;


class CInstalacionDeportivaServiceTest {
	
	@Autowired
	CInstalacionDeportivaService data;

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void agregar() {
		InstalacionDeportiva ins = new InstalacionDeportiva();
		ins.setCostoArriendo(0);
		ins.setEstado(0);
		ins.setNombre("1");
		ins.setDireccion("2");
		ins.setTipo(TipoInstalacion.CANCHA);
		
		data.guardar(ins);
		
		InstalacionDeportiva ins2 = data.buscarPorNombre(ins.getNombre());
		
		Assertions.assertNotNull(ins2);
		
	}

	
	@Test
	void listar(Pageable pageable) {
		InstalacionDeportiva ins = new InstalacionDeportiva();
		ins.setNombre("Test_2");
		ins.setDireccion("Direccion2");
		ins.setCostoArriendo(3400);
		ins.setEstado(0);
		ins.setTipo(TipoInstalacion.QUINCHO);
		
		data.guardar(ins);
		
		//data.save(insPrueba);
		
		Page<InstalacionDeportiva> lista = data.listar(pageable);
		
		assertThat(lista).isNotNull();
		assertThat(lista.getSize()).isEqualTo(1);
	}


}
