package hva.core;

public class Verao implements EstadoArvore {
    @Override
    public void atualizarEstado(Arvore arvore) {
        //updates the tree's biological cycle and seasonal effort to the Summer terms according to the type of tree
        //CADUCA 2,"COMFOLHAS"
        //PERENE 1,"COMFOLHAS"
        if(arvore.getTipo().equals("CADUCA")){
            arvore.setEsforcoSazonal(2);
        }else{
            arvore.setEsforcoSazonal(1);
        }
        arvore.setCicloBiologico("COMFOLHAS");
    }
}
