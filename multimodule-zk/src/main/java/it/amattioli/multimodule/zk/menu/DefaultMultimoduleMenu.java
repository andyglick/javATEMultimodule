package it.amattioli.multimodule.zk.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultMultimoduleMenu implements MultimoduleMenu {
	private String description;
	private int order = 1;
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
	
	public DefaultMultimoduleMenu(String description, int order, List<MultimoduleMenuItem> items) {
		this(description,items);
		this.order = order;
	}
	
	public DefaultMultimoduleMenu(String description, int order, MultimoduleMenuItem... items) {
		this(description,items);
		this.order = order;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public List<MultimoduleMenuItem> getItems() {
		return items;
	}
	
	public void addItem(MultimoduleMenuItem item) {
		items.add(item);
	}

}
