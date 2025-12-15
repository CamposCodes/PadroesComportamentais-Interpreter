package padroescomportamentais.interpreter;

public abstract class OperacaoBinariaExpressao implements Expressao {
    protected final Expressao esquerda;
    protected final Expressao direita;

    public OperacaoBinariaExpressao(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }
}
