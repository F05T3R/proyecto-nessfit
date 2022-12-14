package cl.nessfit.web.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.Solicitud;
@SpringBootTest
class CSolicitudServiceTest {

	@Autowired
	CSolicitudService data;

	/*@Test
	void Listar() {
		List<Solicitud> lista = data.buscarRut("192644259");
		Assertions.assertEquals(0, lista.size());
	}
	*/
	@Test
	void ListarPorFecha() {
		List<Solicitud> lista = data.listarPorFechas("2020-01-30", "2021-01-30");
		Assertions.assertEquals(0, lista.size());
		
		List<Solicitud> lista2= data.listarPorFechas("2021-10-30", "2022-01-30");
		Assertions.assertEquals(1, lista2.size());
		
		List<Solicitud> lista3= data.listarPorFechas("2022-10-30", "2022-12-30");
		Assertions.assertEquals(3, lista3.size());
	}
}
