package padroescomportamentais.interpreter;

public class SomaExpressao extends OperacaoBinariaExpressao {
    public SomaExpressao(Expressao esquerda, Expressao direita) {
        super(esquerda, direita);
    }

    @Override
    public double interpretar() {
        return esquerda.interpretar() + direita.interpretar();
    }
}
