package cl.nessfit.web.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.FechasSolicitud;

@SpringBootTest
class CFechasSolicitudServiceTest {
	@Autowired
	CFechasSolicitudService data;
	FechasSolicitud prueba = new FechasSolicitud();
	
	@BeforeEach
	void setup() {
		prueba.setIdSolicitud(7);
		prueba.setFecha("2022/11/11");
	}
	
	@Test
	void agregar() {
		data.guardar(prueba);
		//FechasSolicitud fechaGuardar = data.buscar(prueba.getIdSolicitud(), prueba.getFecha());
		//Assertions.assertNotNull(fechaGuardar);
		//data.eliminar(prueba);
	}
	
	@Test
	void eliminar() {
		data.guardar(prueba);
		//data.eliminar(prueba);
		//FechasSolicitud fechaGuardar = data.buscar(prueba.getIdSolicitud(), prueba.getFecha());
		//Assertions.assertNull(fechaGuardar);
	}
}
