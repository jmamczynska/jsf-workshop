package pl.course.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pl.course.entity.Packaging;
import pl.course.product.ProductBean;

@Named
@RequestScoped
public class PackagingConverter implements Converter {

	@Inject
	private ProductBean productBean;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String asString) {
		if (asString != null) {
			for (Packaging packaging : productBean.getPackaging()) {
				if (packaging.getId() == Integer.valueOf(asString)) {
					return packaging;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object instanceof Packaging) {
			return String.valueOf(((Packaging) object).getId());
		}
		return "";
	}

}
