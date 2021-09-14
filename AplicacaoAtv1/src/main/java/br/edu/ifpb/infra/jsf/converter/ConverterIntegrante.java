
package br.edu.ifpb.infra.jsf.converter;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;
import br.edu.ifpb.domain.persistency.memory.IntegrantesEmJDBC;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="converter.integrante", forClass=Integrante.class)
public class ConverterIntegrante implements Converter {
    
    private Integrantes integrantes;
    
    public ConverterIntegrante() throws SQLException {
        this.integrantes = new IntegrantesEmJDBC();
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        if (string == null) {
            
            return null;
            
        } else {
            
            Integrante integrante = this.integrantes.recuperarIntegrantePorCpf(string);
            
            if ( integrante.getNome().equals("") ) {
                
                return null;
            
            } 

            return integrante;
            
        }
        
    }

    //Converte objeto em string
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
       
      if (t == null) return null;
      
      Integrante integrante = (Integrante) t;
      
      return integrante.getCpf().getNumero();
      
      
        
    }

   
   
    
}
