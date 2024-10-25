package hva.core;

//CADUCA 0,"SEMFOLHAS"
//PERENE 2,"LARGARFOLHAS"

public class Inverno implements EstadoArvore {
    @Override
    public void atualizarEstado(Arvore arvore) {
        // Atualiza as propriedades da árvore para Inverno
        if(arvore.getTipo().equals("CADUCA")){
            arvore.setEsforcoSazonal(0);
            arvore.setCicloBiologico("SEMFOLHAS");
        }else{
            arvore.setEsforcoSazonal(2);
            arvore.setCicloBiologico("LARGARFOLHAS");
        }
    }
}
