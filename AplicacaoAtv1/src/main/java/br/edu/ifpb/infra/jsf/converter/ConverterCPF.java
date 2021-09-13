
package br.edu.ifpb.infra.jsf.converter;

import br.edu.ifpb.domain.CPF;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(value="converter.cpf", forClass=CPF.class)
public class ConverterCPF implements Converter {

    //Converte String em objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) return null;
        CPF cpf = new CPF(string);
        return cpf;
    }

    //Converte objeto em String
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
       if (t == null) return null;
       CPF cpf = (CPF) t;
       return cpf.getNumero();
    }
    
}
