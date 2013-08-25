package it.amattioli.multimodule.zk.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultMultimoduleMenu implements MultimoduleMenu {
	private String name;
	private String description;
	private int order = 1;
	private List<MultimoduleMenuItem> items;
	
	public DefaultMultimoduleMenu() {
		this("", "", Collections.EMPTY_LIST);
	}
	
	public DefaultMultimoduleMenu(String name, String description, List<MultimoduleMenuItem> items) {
		this.name = name;
		this.description = description;
		this.items = items;
	}
	
	public DefaultMultimoduleMenu(String name, String description, MultimoduleMenuItem... items) {
		this.name = name;
		this.description = description;
		this.items = Arrays.asList(items);
	}
	
	public DefaultMultimoduleMenu(String name, String description, int order, List<MultimoduleMenuItem> items) {
		this(name, description, items);
		this.order = order;
	}
	
	public DefaultMultimoduleMenu(String name, String description, int order, MultimoduleMenuItem... items) {
		this(name, description, items);
		this.order = order;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
