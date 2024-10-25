package hva.core;

//CADUCA 5,"LARGARFOLHAS"
//PERENE 1,"COMFOLHAS"

public class Outono implements EstadoArvore {
    @Override
    public void atualizarEstado(Arvore arvore) {
        // Atualiza as propriedades da árvore para Outono
        if(arvore.getTipo().equals("CADUCA")){
            arvore.setEsforcoSazonal(5);
            arvore.setCicloBiologico("LARGARFOLHAS");
        }else{
            arvore.setEsforcoSazonal(1);
            arvore.setCicloBiologico("COMFOLHAS");
        }
    }
}