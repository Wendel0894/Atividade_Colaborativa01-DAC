
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

public interface Bandas extends Serializable{
    
    public void inserir(Banda banda);
    
    public List<Banda> lista();
    
    public void atualizar(Banda banda);
    
    public void remover(Banda banda);
    
    public List<Integrante> listarIntegrantes();
    
    public void adicionarIntegrante(Banda banda, Integrante integrante);
    
    public void removerIntegrante(Banda banda, Integrante integrante);
   
}
