package global.card.cards.retributor;

import java.io.Serializable;

import global.card.Card;
import global.EntityListenerMessage;
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

	private transient RevengeEntityListener dam;

	public Revenge(ServerDataHandler sdh) {
		super(2, TP.C_T_REVENGE_N, TP.C_T_REVENGE_D, TP.C_T_REVENGE_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
	}

	public void onAddToDeck(Player p) {
		dam = new RevengeEntityListener();
		p.addListener(dam);
	}

	@Override
	protected void playLogic(Player p, int entityTarget, int cardTarget) {
		int dealt = getETarget(entityTarget).takeAttackDamage(2 * dam.damageDealt, p);
		p.damageDealtOut(dealt, name);
	}

	@Override
	protected void playUpgradedLogic(Player p, int entityTarget, int cardTarget) {
		int dealt = getETarget(entityTarget).takeAttackDamage(3 * dam.damageDealt, p);
		p.damageDealtOut(dealt, name);
	}
	
	private void setListener(RevengeEntityListener rel) {
		this.dam = rel;
	}

	public Card clone() {
		Card c = super.clone();
		((Revenge) c).setListener(dam);
		return c;
	}

	private class RevengeEntityListener implements EntityListener, Serializable{
		private static final long serialVersionUID = 1L;
		public int damageDealt;

		@Override
		public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) throws ActionInteruptException {
			if(message.is(EntityListenerMessage.TURN_END)) {
				damageDealt = 0;
			}else if(message.is(EntityListenerMessage.ATTACK_DAMAGE_TAKEN)){
				damageDealt += data.n;
			}else if(message.is(EntityListenerMessage.DAMAGE_TAKEN)) {
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
