package pl.course.product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pl.course.entity.Product;
import java.io.Serializable;

@Named
@SessionScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = -5753334735020095311L;

	private int currentId;
	private Product product;

	@PostConstruct
	public void init() {
		product = new Product(1, "Product 1");
	}
	
	public String show() {
		product.setName(product.getName().concat("1"));
		return "";
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
