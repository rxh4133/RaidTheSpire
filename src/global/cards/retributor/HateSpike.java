package global.cards.retributor;

import global.Card;
import global.CardType;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.StatusEffect;
import server.ServerDataHandler;

public class HateSpike extends Card{
	private static final long serialVersionUID = 1L;

	public HateSpike(ServerDataHandler sdh) {
		super(1, "Hate Spike", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		Player p = this.getPTarget(target);
		
		StatusEffect thorns = p.getSE("Thorns");
		if(thorns != null) {
			for(int i = 0; i < dataHandler.enemies.size(); i++) {
				int dealt = getETarget(i).takeAttackDamage(thorns.value, play);
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
				int dealt = e.takeAttackDamage((int) (thorns.value * 1.5), play);
				play.damageDealtOut(dealt, name);
			}
		}
	}

	public Card copyCard() {
		return new HateSpike(dataHandler);
	}
}
