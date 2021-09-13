/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra.jsf.converter;

import java.time.LocalDate;
import java.time.Month;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="converter.data", forClass=LocalDate.class)
public class ConverterData implements Converter{

    //Converte String em objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null) return null;
        LocalDate dataDeNascimento = LocalDate.of(
                Integer.parseInt(string.substring(0, 4)), //ano
                Integer.parseInt(string.substring(5, 7)), //mês
                Integer.parseInt(string.substring(8, 10)) //dia
        );
        return dataDeNascimento;
    }

    //Converte objeto em string
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
       if (t == null) return null;
       LocalDate dataDeAniversario = (LocalDate) t;
       return dataDeAniversario.getYear() + "/" + dataDeAniversario.getMonthValue()+ "/" + dataDeAniversario.getDayOfMonth();
    }
    
}
