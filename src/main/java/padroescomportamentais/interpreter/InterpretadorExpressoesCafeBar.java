package padroescomportamentais.interpreter;

import java.util.*;

/**
 * Interpretador de expressões para cardápio de bar/cafeteria.
 * Suporta números, nomes de itens do cardápio, operadores + - * / e parênteses.
 * Tokens devem estar separados por espaços.
 *
 * Mensagens de exceção:
 * - "Expressão com elemento inválido" para token desconhecido
 * - "Expressão inválida" para expressão malformada
 */
public class InterpretadorExpressoesCafeBar implements InterpretadorExpressao {
    private final String expressao;
    private Expressao raiz;

    private static final Map<String, Double> CARDAPIO;
    static {
        Map<String, Double> m = new HashMap<>();
        m.put("ESPRESSO", 3.0);
        m.put("LATTE", 4.0);
        m.put("CAPPUCCINO", 4.5);
        m.put("SANDUICHE", 2.0);
        m.put("PAO", 1.5);
        m.put("CHA", 2.5);
        m.put("SUCOS", 3.5);
        CARDAPIO = Collections.unmodifiableMap(m);
    }

    public InterpretadorExpressoesCafeBar(String expressao) {
        this.expressao = expressao;
    }

    @Override
    public double interpretar() {
        if (raiz == null) {
            raiz = parse(expressao);
        }
        return raiz.interpretar();
    }

    private Expressao parse(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            throw new IllegalArgumentException("Expressão inválida");
        }

        List<String> tokens = tokenize(expr);
        List<String> rpn = toRPN(tokens);
        Expressao tree = buildTreeFromRPN(rpn);
        if (tree == null) {
            throw new IllegalArgumentException("Expressão inválida");
        }
        return tree;
    }

    private List<String> tokenize(String expr) {
        String[] raw = expr.trim().split("\\s+");
        return Arrays.asList(raw);
    }

    private int precedence(String op) {
        if (op.equals("*") || op.equals("/")) return 2;
        if (op.equals("+") || op.equals("-")) return 1;
        return 0;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isItem(String token) {
        return CARDAPIO.containsKey(token.toUpperCase());
    }

    private List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> ops = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token) || isItem(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!ops.isEmpty() && isOperator(ops.peek()) &&
                        (precedence(ops.peek()) >= precedence(token))) {
                    output.add(ops.pop());
                }
                ops.push(token);
            } else if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                boolean foundLeftParen = false;
                while (!ops.isEmpty()) {
                    String top = ops.pop();
                    if (top.equals("(")) {
                        foundLeftParen = true;
                        break;
                    } else {
                        output.add(top);
                    }
                }
                if (!foundLeftParen) {
                    throw new IllegalArgumentException("Expressão inválida");
                }
            } else {
                throw new IllegalArgumentException("Expressão com elemento inválido");
            }
        }

        while (!ops.isEmpty()) {
            String top = ops.pop();
            if (top.equals("(") || top.equals(")")) {
                throw new IllegalArgumentException("Expressão inválida");
            }
            output.add(top);
        }

        return output;
    }

    private Expressao buildTreeFromRPN(List<String> rpn) {
        Deque<Expressao> stack = new ArrayDeque<>();
        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(new NumeroExpressao(Double.parseDouble(token)));
            } else if (isItem(token)) {
                double preco = CARDAPIO.get(token.toUpperCase());
                stack.push(new ItemExpressao(preco));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Expressão inválida");
                }
                Expressao direita = stack.pop();
                Expressao esquerda = stack.pop();
                Expressao opExpr;
                switch (token) {
                    case "+": opExpr = new SomaExpressao(esquerda, direita); break;
                    case "-": opExpr = new SubtracaoExpressao(esquerda, direita); break;
                    case "*": opExpr = new MultiplicacaoExpressao(esquerda, direita); break;
                    case "/": opExpr = new DivisaoExpressao(esquerda, direita); break;
                    default: throw new IllegalArgumentException("Expressão com elemento inválido");
                }
                stack.push(opExpr);
            } else {
                throw new IllegalArgumentException("Expressão com elemento inválido");
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expressão inválida");
        }

        return stack.pop();
    }
}
