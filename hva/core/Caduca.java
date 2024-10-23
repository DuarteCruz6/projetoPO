package hva.core;

//Caduca class
//Arvore class son

public class Caduca extends Arvore{

    public Caduca(String id, String nome, int idade, int dificuldadeBase, Estacao estacao){
        //creates the Caduca tree, which is a son of class Arvore
        super(id, nome, idade, dificuldadeBase, "CADUCA",estacao.getEstacaoAtual(),new int[]{1,2,5,0},new String[]{"GERARFOLHAS","COMFOLHAS","LARGARFOLHAS","SEMFOLHAS"});
    }
}
