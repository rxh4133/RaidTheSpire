package global.card.cards.retributor;

import global.card.Card;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.TP;
import global.statuseffect.StatusEffect;
import global.card.CardType;
import server.ServerDataHandler;

public class HateSpike extends Card{
	private static final long serialVersionUID = 1L;

	public HateSpike(ServerDataHandler sdh) {
		super(1, TP.C_T_HATESPIKE_N, TP.C_T_HATESPIKE_D, TP.C_T_HATESPIKE_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
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
}
