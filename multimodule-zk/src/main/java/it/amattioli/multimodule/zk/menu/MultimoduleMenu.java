package it.amattioli.multimodule.zk.menu;

import java.util.List;

public interface MultimoduleMenu {
	
	public String getName();

	public String getDescription();
	
	public List<MultimoduleMenuItem> getItems();
	
	public int getOrder();
	
}
