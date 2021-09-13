
package br.edu.ifpb.infra.jsf.validator;

import br.edu.ifpb.domain.Integrante;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validator.integrante")
public class ValidatorIntegrante implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        
        Integrante integrante = (Integrante) t;
        
        System.out.println("t: " + t);
        System.out.println("Integrante: " + integrante);
        
        if (integrante.getCpf().ehValido()) return;
        
        throw new ValidatorException(
                new FacesMessage("Desculpe, o valor do CPF do integrante é invalido.")
        );
        
    }
    
}
