package it.amattioli.multimodule.zk.menu;

public class DefaultMultimoduleMenuItem implements MultimoduleMenuItem {
	private String imageUrl;
	private String label;
	private String targetUrl;
	
	public DefaultMultimoduleMenuItem() {
		this("","","");
	}
	
	public DefaultMultimoduleMenuItem(String imageUrl, String label, String targetUrl) {
		setImageUrl(imageUrl);
		setLabel(label);
		setTargetUrl(targetUrl);
	}
	
	@Override
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getTargetUrl() {
		return targetUrl;
	}
	
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

}
