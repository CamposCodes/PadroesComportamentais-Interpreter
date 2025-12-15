package padroescomportamentais.interpreter;

public class NumeroExpressao implements Expressao {
    private final double valor;

    public NumeroExpressao(double valor) {
        this.valor = valor;
    }

    @Override
    public double interpretar() {
        return valor;
    }
}
