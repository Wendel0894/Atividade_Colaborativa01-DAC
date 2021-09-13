
package br.edu.ifpb.infra.jsf.converter;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;
import br.edu.ifpb.domain.persistency.memory.IntegrantesEmJDBC;
import java.sql.SQLException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="converter.integrante", forClass=Integrante.class)
public class ConverterIntegrante implements Converter {
    
    //Converter string em objeto
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        if (string == null) return null;
        
        Integrante integrante = new Integrante(new CPF(string));
        System.out.println("CPF: " + integrante.getCpf().getNumero());
        return integrante;
     
    }

    //Converte objeto em string
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
       
        if(t == null) return null;
        
        Integrante integrante = (Integrante) t;
        
        return integrante.getCpf().getNumero();
        
    }

   
   
    
}
