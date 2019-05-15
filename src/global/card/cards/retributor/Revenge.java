package global.card.cards.retributor;

import java.io.Serializable;

import global.card.Card;
import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ActionInteruptException;
import server.EntityListener;
import server.ServerDataHandler;

public class Revenge extends Card {
	private static final long serialVersionUID = 1L;

	private transient REL dam;

	public Revenge(ServerDataHandler sdh) {
		super(2, TP.C_T_REVENGE_N, TP.C_T_REVENGE_D, TP.C_T_REVENGE_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
	}

	public void onAddToDeck(Player p) {
		dam = new REL();
		p.addListener(dam);
	}

	public void play(Player p, int entityTarget, int cardTarget) {
		tinp();
		int dealt = getETarget(entityTarget).takeAttackDamage(2 * dam.damageDealt, p);
		p.damageDealtOut(dealt, name);
	}

	public void playUpgraded(Player p, int entityTarget, int cardTarget) {
		tinp();
		int dealt = getETarget(entityTarget).takeAttackDamage(3 * dam.damageDealt, p);
		p.damageDealtOut(dealt, name);
	}
	
	private void setListener(REL rel) {
		this.dam = rel;
	}

	public Card clone() {
		Card c = super.clone();
		((Revenge) c).setListener(dam);
		return c;
	}

	private class REL implements EntityListener, Serializable{
		private static final long serialVersionUID = 1L;
		public int damageDealt;

		@Override
		public void notify(Entity entity, ELM message, NotifyPayload data) throws ActionInteruptException {
			if(message.is(ELM.TURN_END)) {
				damageDealt = 0;
			}else if(message.is(ELM.ATTACK_DAMAGE_TAKEN)){
				damageDealt += data.n;
			}else if(message.is(ELM.DAMAGE_TAKEN)) {
				damageDealt += data.n;
			}
		}

		@Override
		public int compareTo(EntityListener o) {
			return getPriority() - o.getPriority();
		}

		@Override
		public int getPriority() {
			return 25;
		}

	}
}
