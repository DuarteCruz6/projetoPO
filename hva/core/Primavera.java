package hva.core;

//CADUCA 1,"GERARFOLHAS"
//PERENE 1,"GERARFOLHAS"

public class Primavera implements EstadoArvore {
    @Override
    public void atualizarEstado(Arvore arvore) {
        // Atualiza as propriedades da árvore para Primavera
        arvore.setEsforcoSazonal(1);
        arvore.setCicloBiologico("GERARFOLHAS");
    }
}
