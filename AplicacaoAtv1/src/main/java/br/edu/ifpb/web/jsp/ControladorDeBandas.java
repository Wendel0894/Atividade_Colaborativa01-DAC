
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
    private List<Banda> bandasEncontradas = new ArrayList<>();
    private Bandas bandas;

    public ControladorDeBandas() throws SQLException {
        this.bandas = new BandasEmJDBC();
    }
    
    public String buscar() {
        
        this.bandasEncontradas = this.bandas.recuperarBandasPorLocalDeOrigem(this.banda.getLocalDeOrigem());
        
        this.banda = new Banda("", "", new ArrayList<>());
        return "/Banda/search";
        
    }
    
    public String editar (Banda banda) { 
        this.banda = banda;
        return "/Banda/edit?faces-redirect=true";
    }
    
    public String excluir (Banda banda) {
        this.bandas.remover(banda);
        return "/Banda/list";
    }
    
    
    public String adicionar() { 
        
       
        if ( this.banda.getId() > 0 ) { //Atualiza
            
            this.bandas.atualizar(banda);
        
        } else { //Insere
            
             this.bandas.inserir(banda);
            
        }
       
       
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

    public List<Banda> getBandasEncontradas() {
        return bandasEncontradas;
    }

    public void setBandasEncontradas(List<Banda> bandasEncontradas) {
        this.bandasEncontradas = bandasEncontradas;
    }

  
 
    
    
}
