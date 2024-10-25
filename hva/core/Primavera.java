package hva.core;

public class Primavera implements EstadoArvore{
    @Override
    public void atualizarEstado(Arvore arvore) {
        //updates the tree's biological cycle and seasonal effort to the Spring terms according to the type of tree
        //CADUCA 1,"GERARFOLHAS"
        //PERENE 1,"GERARFOLHAS"
        arvore.setEsforcoSazonal(1);
        arvore.setCicloBiologico("GERARFOLHAS");
    }
}
