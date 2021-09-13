
package br.edu.ifpb.web.jsp;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;
import br.edu.ifpb.domain.persistency.memory.IntegrantesEmJDBC;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class ControladorDeIntegrantes implements Serializable {
        
    private Integrante integrante = new Integrante("", LocalDate.of(1,1, 1), new CPF(""));
    private Integrantes integrantes;
    private String IntegranteEcontrado = "";

    public ControladorDeIntegrantes() throws SQLException {
        this.integrantes = new IntegrantesEmJDBC();
    }
    
    public String buscar() {
        
       Integrante result = this.integrantes.recuperarIntegrantePorCpf(this.integrante.getCpf().getNumero());
       
       if ( result.getNome().equals("Não Encontrado") ) {
           
          this.IntegranteEcontrado = "Desculpe, mas esse integrante não existe : ("; 
          
       } else {
           
           this.IntegranteEcontrado = 
                   "Id: " + result.getId() + 
                   "\nNome: " + result.getNome() + 
                   "\nData de Nascimento: " + result.getDataDeNascimento() + 
                   "\nCPF: " + result.getCpf().getNumero();
           
       }
       
        this.integrante = new Integrante("", LocalDate.of(1,1,1), new CPF(""));
        return "/Integrantes/search";
        
    }
    
    
    public String excluir(Integrante integrante) {
        
         this.integrantes.remover(integrante); //removendo o integrante
         
         return "/Integrantes/list"; //redirecionando para a pagina de listagem novamente
        
    }
    
    public String editar(Integrante integrante) {
        this.integrante = integrante;
        return "/Integrantes/edit?faces-redirect=true";
    }
    
    public String adicionar() {
        
        
        if ( this.integrante.getId() > 0) {
            
            System.out.println("\nAtualizou\n");
            this.integrantes.atualizar(integrante);
        
        } else {
            
            System.out.println("\nInseriu\n");
            this.integrantes.inserir(this.integrante);
            
        }
        
        
        this.integrante = new Integrante("", LocalDate.of(1,1,1), new CPF(""));
        return "/Integrantes/list?faces-redirect=true";
        
        
    }
    
    public List<Integrante> listar() {
        return this.integrantes.listar();
    }
    
    public Integrante getIntegrante() {
        return this.integrante;
    }
    
    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public String getIntegranteEcontrado() {
        return IntegranteEcontrado;
    }

    public void setIntegranteEcontrado(String IntegranteEcontrado) {
        this.IntegranteEcontrado = IntegranteEcontrado;
    }
    
    
}
