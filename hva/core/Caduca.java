package hva.core;

//Caduca class
//Arvore class son

public class Caduca extends Arvore{
    //specific attributes to the caduca's trees
    private final int[] _esforcoSazonal = {1,2,5,0};
    private final String[] _cicloBiologico = {"GERARFOLHAS","COMFOLHAS","LARGARFOLHAS","SEMFOLHAS"};

    public Caduca(String id, String nome, int dificuldadeBase, Estacao estacao){
        //creates the Caduca tree, which is a son of class Arvore
        super(id, nome, dificuldadeBase, "Caduca",estacao.getEstacaoAtual());
    }

    @Override
    public int getEsforcoSazonal(Estacao estacao){
        //returns the seasonal effort to clean the tree at the present season
        int estacaoAtual=estacao.getEstacaoAtual();
        return _esforcoSazonal[estacaoAtual];
    }

    @Override
    public String getCicloBiologico(Estacao estacao){
        //returns the tree's bilogical cycle term at the present season
        int estacaoAtual=estacao.getEstacaoAtual();
        return _cicloBiologico[estacaoAtual];
    }
}
