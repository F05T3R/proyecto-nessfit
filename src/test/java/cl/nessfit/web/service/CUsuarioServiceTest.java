package cl.nessfit.web.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.Solicitud;
import cl.nessfit.web.model.Usuario;

@SpringBootTest
class CUsuarioServiceTest {

	
	@Autowired
	CUsuarioService data;
	
	@Test
	void Listar() {
		
		List<Usuario> lista = data.mostrarClientes();
		Assertions.assertEquals(2, lista.size());
		
		List<Usuario> lista2 = data.mostrarTodos();
		Assertions.assertEquals(5, lista2.size());
		
	}

}
