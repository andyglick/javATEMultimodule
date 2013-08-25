package it.amattioli.multimodule.zk.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericComposer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;

public class MultimoduleToolbar extends GenericComposer {
	private List<MultimoduleMenu> menus = new ArrayList<MultimoduleMenu>();
	
	public MultimoduleToolbar() {
		for (MultimoduleMenuProvider provider: ServiceLoader.load(MultimoduleMenuProvider.class)) {
			for (MultimoduleMenu menu: provider.getMenus()) {
				menus.add(menu);
			}
		}
		Collections.sort(menus, new Comparator<MultimoduleMenu>() {

			@Override
			public int compare(MultimoduleMenu menu1, MultimoduleMenu menu2) {
				return new Integer(menu1.getOrder()).compareTo(menu2.getOrder());
			}
			
		});
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
		panel.setVflex("1");
		Toolbar toolbar = new Toolbar();
		toolbar.setOrient("vertical");
		toolbar.setAlign("center");
		toolbar.setVflex("1");
		for (MultimoduleMenuItem item: menu.getItems()) {
			Toolbarbutton buttonitem = createButtonItem(item);
			buttonitem.setParent(toolbar);
		}
		toolbar.setParent(panel);
		return panel;
	}

	private Toolbarbutton createButtonItem(MultimoduleMenuItem item) {
		Toolbarbutton menuitem = new Toolbarbutton();
		menuitem.setLabel(item.getLabel());
		menuitem.setImage(item.getImageUrl());
		menuitem.setOrient("vertical");
		menuitem.setHref("menutarget.zul?targeturl="+item.getTargetUrl()+"&title="+item.getLabel());
		return menuitem;
	}
	
}
