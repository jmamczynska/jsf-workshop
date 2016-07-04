package pl.course.product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pl.course.entity.Packaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductBean implements Serializable {

	/**
	 * Serialization id
	 */
	private static final long serialVersionUID = 9217796277887202743L;

	private List<Integer> packageType;

	private List<Packaging> packaging;

	@PostConstruct
	public void init() {
		packaging = new ArrayList<>();
		packaging.add(new Packaging(1, "Karton"));
		packaging.add(new Packaging(2, "Folia"));
		packaging.add(new Packaging(3, "Koperta"));
	}

	public List<Packaging> getPackaging() {
		return packaging;
	}

	public List<Integer> getPackageType() {
		return packageType;
	}

	public void setPackageType(List<Integer> packageType) {
		this.packageType = packageType;
	}

}
