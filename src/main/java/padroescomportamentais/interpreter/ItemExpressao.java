package padroescomportamentais.interpreter;

public class ItemExpressao implements Expressao {
    private final double preco;

    public ItemExpressao(double preco) {
        this.preco = preco;
    }

    @Override
    public double interpretar() {
        return preco;
    }
}
