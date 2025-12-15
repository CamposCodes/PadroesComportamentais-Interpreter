package padroescomportamentais.interpreter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCalcularTotalPedido() {
        Pedido pedido = new Pedido();
        pedido.setQtdCafe(2);        // 2 * ESPRESSO (3.0) = 6.0
        pedido.setQtdSanduiche(1);   // 1 * SANDUICHE (2.0) = 2.0

        assertEquals(8.0, pedido.calcularTotal());
    }

}
