package global.card.cards.retributor;

import java.io.Serializable;

import global.card.Card;
import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ActionInteruptException;
import server.EntityListener;
import server.ServerDataHandler;

public class Revenge extends Card {
	private static final long serialVersionUID = 1L;

	private transient REL dam;

	public Revenge(ServerDataHandler sdh) {
		super(2, "Revenge", Rarity.COMMON, CardType.ATTACK, sdh);
	}

	private Revenge(ServerDataHandler sdh, REL nd) {
		super(2, "Revenge", Rarity.COMMON, CardType.ATTACK, sdh);
		dam = nd;
	}

	public void onAddToDeck(Player p) {
		dam = new REL();
		p.addListener(dam);
		p.addListener(dam);
	}

	public void play(Player p, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(2 * dam.damageDealt, p);
		p.damageDealtOut(dealt, name);
	}

	public void playUpgraded(Player p, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(2 * dam.damageDealt, p);
		p.damageDealtOut(dealt, name);
	}

	public Card copyCard() {
		return new Revenge(dataHandler, dam);
	}

	private class REL implements EntityListener, Serializable{
		private static final long serialVersionUID = 1L;
		public int damageDealt;

		@Override
		public void notify(Entity entity, ELM message, Object data) throws ActionInteruptException {
			if(message.is(ELM.TURN_END)) {
				damageDealt = 0;
			}else if(message.is(ELM.ATTACK_DAMAGE_TAKEN)){
				damageDealt += (int)((Object[])data)[0];
			}else if(message.is(ELM.DAMAGE_TAKEN)) {
				damageDealt += (int) data;
			}
		}

	}

}
