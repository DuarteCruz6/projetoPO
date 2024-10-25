package hva.core;

public enum EstacaoEnumerator {
    PRIMAVERA(0),
    VERAO(1),
    OUTONO(2),
    INVERNO(3);

    private final int valor;

    EstacaoEnumerator(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

}
