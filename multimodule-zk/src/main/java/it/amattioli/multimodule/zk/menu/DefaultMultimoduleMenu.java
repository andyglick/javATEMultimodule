package it.amattioli.multimodule.zk.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultMultimoduleMenu implements MultimoduleMenu {
	private String description;
	private List<MultimoduleMenuItem> items;
	
	public DefaultMultimoduleMenu() {
		this("", Collections.EMPTY_LIST);
	}
	
	public DefaultMultimoduleMenu(String description, List<MultimoduleMenuItem> items) {
		this.description = description;
		this.items = items;
	}
	
	public DefaultMultimoduleMenu(String description, MultimoduleMenuItem... items) {
		this.description = description;
		this.items = Arrays.asList(items);
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public List<MultimoduleMenuItem> getItems() {
		return items;
	}
	
	public void addItem(MultimoduleMenuItem item) {
		items.add(item);
	}

}
