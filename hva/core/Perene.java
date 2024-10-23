package hva.core;

//Perene class
//Arvore class son

public class Perene extends Arvore{
    //specific attributes of perenes trees

    public Perene(String id, String nome, int idade, int dificuldadeBase, Estacao estacao){
        //creates the Perene tree, which is a son of class Arvore
        super(id, nome, idade, dificuldadeBase, "PERENE",estacao.getEstacaoAtual(),new int[]{1,1,1,2},new String[]{"GERARFOLHAS","COMFOLHAS","COMFOLHAS","LARGARFOLHAS"});
    }
}