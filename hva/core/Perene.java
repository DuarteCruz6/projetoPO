package hva.core;

//Perene class
//Arvore class son

public class Perene extends Arvore{
    //specific attributes to the perene's trees
    private final int[] _esforcoSazonal = {1,1,1,2};
    private final String[] _cicloBiologico = {"GERARFOLHAS","COMFOLHAS","COMFOLHAS","LARGARFOLHAS"};

    public Perene(String id, String nome, int dificuldadeBase, Estacao estacao){
        //creates the Perene tree, which is a son of class Arvore
        super(id, nome, dificuldadeBase, "Perene",estacao.getEstacaoAtual());
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