package hva.core;

public class Inverno implements EstadoArvore{
    @Override
    public void atualizarEstado(Arvore arvore) {
        //updates the tree's biological cycle and seasonal effort to the Winter terms according to the type of tree
        //CADUCA 0,"SEMFOLHAS"
        //PERENE 2,"LARGARFOLHAS"
        if(arvore.getTipo().equals("CADUCA")){
            arvore.setEsforcoSazonal(0);
            arvore.setCicloBiologico("SEMFOLHAS");
        }else{
            arvore.setEsforcoSazonal(2);
            arvore.setCicloBiologico("LARGARFOLHAS");
        }
    }
}
