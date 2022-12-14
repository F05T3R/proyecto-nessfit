package cl.nessfit.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;

@SpringBootTest
class CInstalacionDeportivaServiceTest {
	
	@Autowired
	CInstalacionDeportivaService data;
	
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
	void ListarOperativa() {
		List<InstalacionDeportiva> lista = data.listarOperativas();
		Assertions.assertEquals(5, lista.size());
	}
	@Test
	void ListarTipo() {
		List<InstalacionDeportiva> lista = data.listarTipo(0);
		Assertions.assertEquals(2, lista.size());
		
		List<InstalacionDeportiva> lista2 = data.listarTipo(1);
		Assertions.assertEquals(0, lista2.size());
		
		List<InstalacionDeportiva> lista3 = data.listarTipo(2);
		Assertions.assertEquals(1, lista3.size());
		
		List<InstalacionDeportiva> lista4 = data.listarTipo(3);
		Assertions.assertEquals(1, lista4.size());
		
		List<InstalacionDeportiva> lista5 = data.listarTipo(4);
		Assertions.assertEquals(1, lista5.size());
	}

}
