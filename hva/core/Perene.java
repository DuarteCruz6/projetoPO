package hva.core;

public class Perene extends Arvore{
    private final int[] _esforcoSazonal = {1,1,1,2};
    private final String[] _cicloBiologico = {"GERARFOLHAS","COMFOLHAS","COMFOLHAS","LARGARFOLHAS"};

    public Perene(String id, String nome, int dificuldadeBase, Estacao estacao){
        super(id, nome, dificuldadeBase, "Perene",estacao.getEstacaoAtual());
    }

    @Override
    public int getEsforcoSazonal(Estacao estacao){
        int estacaoAtual=estacao.getEstacaoAtual();
        return _esforcoSazonal[estacaoAtual];
    }

    @Override
    public String getCicloBiologico(Estacao estacao){
        int estacaoAtual=estacao.getEstacaoAtual();
        return _cicloBiologico[estacaoAtual];
    }
}