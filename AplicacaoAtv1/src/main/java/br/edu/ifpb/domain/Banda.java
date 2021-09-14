
package br.edu.ifpb.domain;

import java.util.List;

public class Banda {
    
    private int id;
    private String localDeOrigem;
    private String nomeFantasia;
    private List<Integrante> integrantes;

    public Banda(String localDeOrigem, String nomeFantasia, List<Integrante> integrantes) {
        this.localDeOrigem = localDeOrigem;
        this.nomeFantasia = nomeFantasia;
        this.integrantes = integrantes;
    }
 
    public Banda(int id, String localDeOrigem, String nomeFantasia, List<Integrante> integrantes) {
        this.id = id;
        this.localDeOrigem = localDeOrigem;
        this.nomeFantasia = nomeFantasia;
        this.integrantes = integrantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    
   
  
    
}
