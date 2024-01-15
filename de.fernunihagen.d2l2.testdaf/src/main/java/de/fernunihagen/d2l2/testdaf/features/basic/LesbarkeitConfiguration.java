package de.fernunihagen.d2l2.testdaf.features.basic;

public enum LesbarkeitConfiguration {

	ErsteWSF("erste_wsf"),
	ZweiteWSF("zweite_wsf"),
	DritteWSF("dritte_wsf"),
	VierteWSF("vierte_wsf");

	private String stringValue;
	
	LesbarkeitConfiguration(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}
}
