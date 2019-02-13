package global;

public enum Rarity {
	STARTER("Starter"),
	COMMON("Common"),
	UNCOMMON("Uncommon"),
	RARE("Rare"),
	MYTHIC("Mythic"),
	STATUS("Status");
	
	private String displayText;
	
	private Rarity(String dispText) {
		displayText = dispText;
	}
	
	public String getDT() {
		return displayText;
	}
}
