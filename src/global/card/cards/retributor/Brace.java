package global.card.cards.retributor;

import global.card.Card;
import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.statuseffect.StatusEffect;
import global.card.CardType;
import global.statuseffect.statuseffects.StartDraw;
import server.ServerDataHandler;

public class Brace extends Card {
	private static final long serialVersionUID = 1L;

	public Brace(ServerDataHandler sdh) {
		super(1, TP.C_T_BRACE_N, TP.C_T_BRACE_D, TP.C_T_BRACE_F, Rarity.COMMON, CardType.SKILL, true, false, sdh);
	}

	public void play(Player play, int target) {
		tinp();
		play.gainBlockFromCard(7);
		play.addSE(new BEL(play, 1));
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlockFromCard(7);
		play.addSE(new BEL(play, 2));
	}

	private class BEL extends StatusEffect{
		private static final long serialVersionUID = 1L;
		private Player owner;
		private int toDraw;

		public BEL(Player play, int td) {
			super("Bracing", 1);
			owner = play;
			toDraw = td;
		}

		@Override
		public void notify(Entity entity, ELM message, NotifyPayload data) {
			if(message.is(ELM.ATTACK_DAMAGE_TAKEN)) {
				if(owner.getBlock() == 0) {
					owner.addSE(new StartDraw(toDraw));
					owner.removeSE(this);
				}
			}else if(message.is(ELM.TURN_START)){
				owner.removeSE(this);
			}
		}


	}

}
