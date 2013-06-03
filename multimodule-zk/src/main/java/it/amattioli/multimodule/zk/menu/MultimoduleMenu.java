package it.amattioli.multimodule.zk.menu;

import java.util.List;

public interface MultimoduleMenu {

	public String getDescription();
	
	public List<MultimoduleMenuItem> getItems();
	
}
