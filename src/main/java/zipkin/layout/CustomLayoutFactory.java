package zipkin.layout;

import org.springframework.boot.loader.tools.Layout;
import org.springframework.boot.loader.tools.LayoutFactory;

import java.io.File;

public class CustomLayoutFactory implements LayoutFactory {

	private String name = "custom";

	public CustomLayoutFactory() {
	}

	public CustomLayoutFactory(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Layout getLayout(File source) {
		return new CustomLayout(this.name);
	}

}
