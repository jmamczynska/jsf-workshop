package pl.course.validator;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class UseByDateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		if (object instanceof Date) {
			Date date = (Date) object;
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -3);
			if (date.before(calendar.getTime())) {
				FacesMessage message = new FacesMessage("Nie mozna wprowadziæ produktu zakupionego przed ponad trzema laty");
				throw new ValidatorException(message);
			}
		}
	}
}
