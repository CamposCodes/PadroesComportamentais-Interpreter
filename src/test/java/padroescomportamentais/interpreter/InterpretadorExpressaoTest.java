package padroescomportamentais.interpreter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpretadorExpressaoTest {

    @Test
    void deveCalcularExpressaoSomaNumeros() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("6 + 2");
        assertEquals(8.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoSubtracaoNumeros() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("6 - 2");
        assertEquals(4.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoMultiplicacaoNumeros() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("6 * 2");
        assertEquals(12.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoDivisaoNumeros() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("6 / 2");
        assertEquals(3.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoCombinadaNumeros() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("10 / 2 * 3 + 1 - 4");
        assertEquals(12.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoComItensDoCardapio() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("ESPRESSO + LATTE");
        assertEquals(7.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoComQuantidadesEItens() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("2 * ESPRESSO + 1 * SANDUICHE");
        assertEquals(8.0, interpretador.interpretar());
    }

    @Test
    void deveRetornarExcecaoElementoInvalido() {
        try {
            InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("2 ^ 2");
            assertEquals(4.0, interpretador.interpretar());
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Expressão com elemento inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoInvalida() {
        try {
            InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("2 +");
            assertEquals(4.0, interpretador.interpretar());
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Expressão inválida", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoItemInvalido() {
        try {
            InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar("WHISKEY + 2");
            interpretador.interpretar();
            fail();
        } catch(IllegalArgumentException e) {
            assertEquals("Expressão com elemento inválido", e.getMessage());
        }
    }
}
