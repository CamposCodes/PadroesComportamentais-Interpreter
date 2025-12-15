package padroescomportamentais.interpreter;

public class DivisaoExpressao extends OperacaoBinariaExpressao {
    public DivisaoExpressao(Expressao esquerda, Expressao direita) {
        super(esquerda, direita);
    }

    @Override
    public double interpretar() {
        return esquerda.interpretar() / direita.interpretar();
    }
}
