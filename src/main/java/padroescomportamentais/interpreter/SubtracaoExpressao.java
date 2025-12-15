package padroescomportamentais.interpreter;

public class SubtracaoExpressao extends OperacaoBinariaExpressao {
    public SubtracaoExpressao(Expressao esquerda, Expressao direita) {
        super(esquerda, direita);
    }

    @Override
    public double interpretar() {
        return esquerda.interpretar() - direita.interpretar();
    }
}
