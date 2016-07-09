package pl.course.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import pl.course.entity.Product;
import java.io.Serializable;

@Named
@SessionScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = -5753334735020095311L;

	private static final String VIEW_MODE = "VIEW";
	private static final String EDIT_MODE = "EDIT";
	private static final String ADD_MODE = "ADD";

	private String mode = VIEW_MODE;

	private int currentId;
	private Product product;
	private List<Product> productList;

	@PostConstruct
	public void init() {
		productList = new ArrayList<>();
		productList.add(new Product(1, "Pendrive 16GB", "Sony", false));
		productList.add(new Product(2, "S≥uchawki", "Denon", false));
		productList.add(new Product(3, "Pendrive 32GB", "Sony", false));
	}

	public List<Product> getProductList() {
		return productList;
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

	public Product getProduct() {
		if (!mode.equals(ADD_MODE)) {
			product = productList.get(currentId - 1);
		}
		return product;
	}

	public void processProduct(ActionEvent event) {
		if (mode.equals(VIEW_MODE)) {
			mode = EDIT_MODE;
			return;
		} else if (mode.equals(ADD_MODE)) {
			productList.add(product);
		}
		mode = VIEW_MODE;
	}

	public void newProduct() {
		product = new Product();
		product.setId(productList.size() + 1);
		currentId = product.getId();
		mode = ADD_MODE;
	}

	public String navigate(String arg) {
		if (mode.equals(VIEW_MODE)) {
			return "productList";
		}

		return "details";
	}

	public void cancel() {
		mode = VIEW_MODE;
	}

	/**
	 * Metoda walidujaca format kodu produktu
	 * 
	 * @param context
	 *            - kontekst faces
	 * @param component
	 *            - obiekt komponentu
	 * @param object
	 *            - obiekt przetwarzany przez komponent
	 */
	public void codePatternValidator(FacesContext context, UIComponent component, Object object) {
		String regex = "^(\\w{2}-\\d{3})$";
		if (object!= null && !object.toString().matches(regex)) {
			FacesMessage message = new FacesMessage("Niepoprawny format kodu. Wprowadü kod w formacie AB-123");
			throw new ValidatorException(message);
		}
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
