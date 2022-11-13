package cl.nessfit.web.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.nessfit.web.model.InstalacionDeportiva;
import cl.nessfit.web.model.TipoInstalacion;

@SpringBootTest
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
        //borrar instalacion
    }

}
