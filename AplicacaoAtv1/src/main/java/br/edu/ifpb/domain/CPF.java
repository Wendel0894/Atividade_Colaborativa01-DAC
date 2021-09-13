/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

/**
 *
 * @author wendel
 */
public class CPF {
    
    private static final int TAMANHO = 11;
    private String numero;

    public CPF(String numero) {
        this.numero = numero;
    }
    
    public boolean validar() {
        return this.numero.length() == 11;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public boolean ehValido(){
        return this.numero.length() == TAMANHO;
    }
    
    
    
    
    
}
