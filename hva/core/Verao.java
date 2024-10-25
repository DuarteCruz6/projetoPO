package hva.core;

//CADUCA 2,"COMFOLHAS"
//PERENE 1,"COMFOLHAS"


public class Verao implements EstadoArvore {
    @Override
    public void atualizarEstado(Arvore arvore) {
        // Atualiza as propriedades da árvore para Verão
        if(arvore.getTipo().equals("CADUCA")){
            arvore.setEsforcoSazonal(2);
        }else{
            arvore.setEsforcoSazonal(1);
        }
        arvore.setCicloBiologico("COMFOLHAS");
    }
}
