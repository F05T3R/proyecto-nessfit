package cl.nessfit.web.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.Solicitud;

@SpringBootTest
class SolicitudServiceTest {
	@Autowired
	CSolicitudService data;
	Solicitud prueba = new Solicitud();
	
	@BeforeEach
	void setup() {
		//prueba.setNombreCentro("AguasMojadas");
		//prueba.setRutUsuario("128016023");
		prueba.setFechaCompra("2021/03/03");
		prueba.setEstado(1);
		prueba.setTotalPagar(2000);
	}
	
	@Test
	void agregar() {
		data.guardar(prueba);
		Solicitud solicitud = data.buscarPorId(prueba.getId());
		System.out.println(solicitud.getId());
        Assertions.assertNotNull(solicitud);
        data.eliminar(solicitud.getId());
	}
	
	@Test
	void eliminar() {
		data.guardar(prueba);
		Solicitud solicitud = data.buscarPorId(prueba.getId());
		data.eliminar(solicitud.getId());
		Solicitud solicitud2 = data.buscarPorId(solicitud.getId());
		Assertions.assertNull(solicitud2);
	}
}
