package padroescomportamentais.interpreter;

public class MultiplicacaoExpressao extends OperacaoBinariaExpressao {
    public MultiplicacaoExpressao(Expressao esquerda, Expressao direita) {
        super(esquerda, direita);
    }

    @Override
    public double interpretar() {
        return esquerda.interpretar() * direita.interpretar();
    }
}
