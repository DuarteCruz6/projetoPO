package hva.core;

public class Outono implements EstadoArvore {
    @Override
    public void atualizarEstado(Arvore arvore) {
        //updates the tree's biological cycle and seasonal effort to the Autumn terms according to the type of tree
        //CADUCA 5,"LARGARFOLHAS"
        //PERENE 1,"COMFOLHAS"
        if(arvore.getTipo().equals("CADUCA")){
            arvore.setEsforcoSazonal(5);
            arvore.setCicloBiologico("LARGARFOLHAS");
        }else{
            arvore.setEsforcoSazonal(1);
            arvore.setCicloBiologico("COMFOLHAS");
        }
    }
}