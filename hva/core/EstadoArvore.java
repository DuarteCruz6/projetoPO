package hva.core;

import java.io.Serializable;

/**
 * Interface representing the state of a tree.
 * 
 * Implementing classes must provide logic for updating the tree's state
 * based on seasonal changes.
 */

public interface EstadoArvore extends Serializable{
    void atualizarEstado(Arvore arvore);
}
