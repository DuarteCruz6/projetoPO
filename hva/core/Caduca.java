package hva.core;

public class Caduca extends Arvore{
    private final int[] _esforcoSazonal = {1,2,5,0};
    private final String[] _cicloBiologico = {"GERARFOLHAS","COMFOLHAS","LARGARFOLHAS","SEMFOLHAS"};

    public Caduca(String id, String nome, int dificuldadeBase, Estacao estacao){
        super(id, nome, dificuldadeBase, "Caduca",estacao.getEstacaoAtual());
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
