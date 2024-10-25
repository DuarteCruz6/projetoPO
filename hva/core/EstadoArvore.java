package hva.core;

/**
 * Interface representing the state of a tree.
 * 
 * Implementing classes must provide logic for updating the tree's state
 * based on seasonal changes.
 */

public interface EstadoArvore {
    void atualizarEstado(Arvore arvore);
}
