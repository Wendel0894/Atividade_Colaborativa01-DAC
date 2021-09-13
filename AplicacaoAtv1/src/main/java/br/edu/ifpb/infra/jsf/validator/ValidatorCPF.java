
package br.edu.ifpb.infra.jsf.validator;

import br.edu.ifpb.domain.CPF;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="validator.cpf")
public class ValidatorCPF implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
       CPF cpf = (CPF) t;
       if ( cpf.ehValido() ) return;
       
       //Caso o tamanho do CPF informado for menor ou maior que 11 digitos, uma excessão é gerada
       
       throw new ValidatorException (
               new FacesMessage("CPF invalido, entre com um CPF validor : )")
       );
    }  
}
