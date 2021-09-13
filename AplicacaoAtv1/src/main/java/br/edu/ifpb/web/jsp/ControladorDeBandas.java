
package br.edu.ifpb.web.jsp;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Bandas;
import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.persistency.memory.BandasEmJDBC;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ControladorDeBandas implements Serializable {
    
    private Banda banda = new Banda("", "", new ArrayList<>());
    private Integrante integrante = new Integrante("", LocalDate.of(1, 1, 1), new CPF(""));
    private String bandaEncontrada = "";
    private Bandas bandas;

    public ControladorDeBandas() throws SQLException {
        this.bandas = new BandasEmJDBC();
    }
    
    public String buscar() {
        
        Banda b = this.bandas.recuperarBandaPorNomeFantasia(this.banda.getLocalDeOrigem());
        
        if ( b.getNomeFantasia().equals("") ) {
            this.bandaEncontrada = "Desculpe, mas essa banda não existe !";
        } else {
            this.bandaEncontrada = "Id: "  + b.getId() + " LocalDeOrigem: " + b.getLocalDeOrigem() + " Nome Fantasia: " + b.getNomeFantasia() + " Integrantes: " + b.getIntegrantes().get(0).getNome();
        }
        
        this.banda = new Banda("", "", new ArrayList<>());
        return "/Banda/search";
        
    }
    
    public String editar (Banda banda) { //fazer depois da parte de inserir
        this.banda = banda;
        return "/Banda/edit?faces-redirect=true";
    }
    
    public String excluir (Banda banda) {
        this.bandas.remover(banda);
        return "/Banda/list";
    }
    
    
    public String adicionar() { //arrumar depois, premeira ? 
        
        this.banda.getIntegrantes().add(this.integrante);
        this.bandas.inserir(banda);
       
        this.integrante = new Integrante("", LocalDate.of(1, 1, 1), new CPF(""));
        this.banda = new Banda("", "", new ArrayList<>());
        return "/Banda/list?faces-redirect=true";
        
    }
    
    public List<Banda> listar () {
        return this.bandas.lista();
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public Bandas getBandas() {
        return bandas;
    }

    public void setBandas(Bandas bandas) {
        this.bandas = bandas;
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public String getBandaEncontrada() {
        return bandaEncontrada;
    }

    public void setBandaEncontrada(String bandaEncontrada) {
        this.bandaEncontrada = bandaEncontrada;
    }
 
    
    
}
