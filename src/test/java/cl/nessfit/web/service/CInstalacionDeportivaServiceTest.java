package cl.nessfit.web.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;

@SpringBootTest
class CInstalacionDeportivaServiceTest {

    @Autowired
    CInstalacionDeportivaService data;
    InstalacionDeportiva prueba = new InstalacionDeportiva();
    @BeforeEach
    void setup() {
        prueba.setCostoArriendo(0);
        prueba.setEstado(0);
        prueba.setNombre("1");
        prueba.setDireccion("2");
        prueba.setTipo(TipoInstalacion.CANCHA);
    }

    @Test
    void agregar() {
        data.guardar(prueba);

        InstalacionDeportiva ins2 = data.buscarPorNombre(prueba.getNombre());

        Assertions.assertNotNull(ins2);
        //borrar instalacion
        data.eliminar(ins2.getNombre());
    }
    
    @Test
    void eliminar() {
    	InstalacionDeportiva ins = new InstalacionDeportiva();
        ins.setCostoArriendo(0);
        ins.setEstado(0);
        ins.setNombre("2");
        ins.setDireccion("2");
        ins.setTipo(TipoInstalacion.CANCHA);

        data.guardar(ins);

        InstalacionDeportiva ins2 = data.buscarPorNombre(ins.getNombre());

        Assertions.assertNotNull(ins2);
        //borrar instalacion
        data.eliminar(ins2.getNombre());
        InstalacionDeportiva ins3 = data.buscarPorNombre(ins2.getNombre());
        Assertions.assertNull(ins3);
    }
    
}
