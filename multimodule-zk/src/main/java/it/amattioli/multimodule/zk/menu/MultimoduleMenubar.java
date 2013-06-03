package it.amattioli.multimodule.zk.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericComposer;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

public class MultimoduleMenubar extends GenericComposer {
	private List<MultimoduleMenu> menus = new ArrayList<MultimoduleMenu>();
	
	public MultimoduleMenubar() {
		for (MultimoduleMenuProvider provider: ServiceLoader.load(MultimoduleMenuProvider.class)) {
			for (MultimoduleMenu menu: provider.getMenus()) {
				menus.add(menu);
			}
		}
	}
	
	public void onCreate(Event evt) {
		Tabbox tabbox = (Tabbox)evt.getTarget();
		for (MultimoduleMenu menu: menus) {
			Tab tab = createTab(menu);
			tab.setParent(tabbox.getTabs());
			
			Tabpanel panel = createPanel(menu);
			panel.setParent(tabbox.getTabpanels());

		}
	}	

	private Tab createTab(MultimoduleMenu menu) {
		Tab tab = new Tab();
		tab.setLabel(menu.getDescription());
		return tab;
	}
	
	private Tabpanel createPanel(MultimoduleMenu menu) {
		Tabpanel panel = new Tabpanel();
		panel.setStyle("padding: 0px");
		Menubar menubar = new Menubar();
		menubar.setOrient("vertical");
		for (MultimoduleMenuItem item: menu.getItems()) {
			Menuitem menuitem = createMenuItem(item);
			menuitem.setParent(menubar);
		}
		menubar.setParent(panel);
		return panel;
	}

	private Menuitem createMenuItem(MultimoduleMenuItem item) {
		Menuitem menuitem = new Menuitem();
		menuitem.setLabel(item.getLabel());
		menuitem.setHref("menutarget.zul?targeturl="+item.getTargetUrl()+"&title="+item.getLabel());
		return menuitem;
	}
	
}
