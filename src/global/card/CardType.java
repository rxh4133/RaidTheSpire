package global.card;

public enum CardType {
	ATTACK("Attack"),
	SKILL("Skill"),
	POWER("Power"),
	STATUS("Status"),
	CURSE("Curse");
	
	private String displayText;
	
	private CardType(String dispText) {
		displayText = dispText;
	}
	
	public String getDT() {
		return displayText;
	}
}
