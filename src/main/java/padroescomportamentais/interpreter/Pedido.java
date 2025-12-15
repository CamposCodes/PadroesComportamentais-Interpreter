package padroescomportamentais.interpreter;

/**
 * Pedido constrói expressão a partir de quantidades de itens e avalia o total.
 * Segue estilo do exemplo AlunoTest.
 */
public class Pedido {
    private int qtdCafe;        // quantidade de ESPRESSO
    private int qtdSanduiche;   // quantidade de SANDUICHE

    public void setQtdCafe(int qtdCafe) {
        this.qtdCafe = qtdCafe;
    }

    public void setQtdSanduiche(int qtdSanduiche) {
        this.qtdSanduiche = qtdSanduiche;
    }

    public double calcularTotal() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        if (qtdCafe > 0) {
            sb.append(qtdCafe).append(" * ESPRESSO");
            first = false;
        }
        if (qtdSanduiche > 0) {
            if (!first) sb.append(" + ");
            sb.append(qtdSanduiche).append(" * SANDUICHE");
        }
        String expressao = sb.toString();
        if (expressao.isEmpty()) {
            return 0.0;
        }
        InterpretadorExpressao interpretador = new InterpretadorExpressoesCafeBar(expressao);
        return interpretador.interpretar();
    }
}
