/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;


public interface Integrantes extends Serializable {
 
    public void inserir(Integrante integrante);
    
    public List<Integrante> listar();
    
    public void atualizar(Integrante integrante);
    
    public void remover(Integrante integrante);
    
    public Integrante recuperarIntegrantePorCpf(String cpf);
    
}
