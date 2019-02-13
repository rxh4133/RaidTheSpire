package global.card.cards.retributor;

import global.card.Card;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.statuseffect.StatusEffect;
import global.card.CardType;
import server.ServerDataHandler;

public class HateSpike extends Card{
	private static final long serialVersionUID = 1L;

	public HateSpike(ServerDataHandler sdh) {
		super(1, "Hate Spike", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public HateSpike(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Hate Spike", Rarity.COMMON, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "An ally deals damage equal to 1 + 1 (1.5) times their thorns.";
		flavor = "Use they hate they didn't know they had.";
	}
	
	public void play(Player play, int target) {
		tinp();
		Player p = this.getPTarget(target);
		
		StatusEffect thorns = p.getSE("Thorns");
		if(thorns != null) {
			for(int i = 0; i < dataHandler.enemies.size(); i++) {
				int dealt = getETarget(i).takeAttackDamage(thorns.value + 1, play);
				play.damageDealtOut(dealt, name);
			}
		}
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		Player p = this.getPTarget(target);
		
		StatusEffect thorns = p.getSE("Thorns");
		if(thorns != null) {
			for(Enemy e: dataHandler.enemies) {
				int dealt = e.takeAttackDamage((int) (thorns.value * 1.5) + 1, play);
				play.damageDealtOut(dealt, name);
			}
		}
	}

	public Card copyCard() {
		return new HateSpike(dataHandler, playable, upgraded);
	}
}
