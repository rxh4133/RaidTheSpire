package global.card.cards.resipiscent;

import java.util.ArrayList;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import global.statuseffect.StatusEffect;
import server.ELList;
import server.ServerDataHandler;

public class Weave extends Card {
	private static final long serialVersionUID = 1L;

	public Weave(ServerDataHandler sdh) {
		super(2, TP.C_S_WEAVE_N, TP.C_S_WEAVE_D, TP.C_S_WEAVE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		ArrayList<Player> players = dataHandler.players;
		ELList<StatusEffect> buffsPrevious = getPlayerBuffs(players.get(0));
		players.get(0).getEffects().removeAll(buffsPrevious);
		ELList<StatusEffect> buffsCurrent = null;
		for(int i = 1; i < players.size(); i++) {
			buffsCurrent = getPlayerBuffs(players.get(i));
			players.get(i).getEffects().removeAll(buffsCurrent);
			players.get(i).getEffects().addAll(buffsPrevious);
			buffsPrevious = buffsCurrent;
		}
		players.get(0).getEffects().addAll(buffsPrevious);
		
		ArrayList<Player> playersReverse = new ArrayList<Player>();
		
		for(int i = players.size() - 1; i >= 0; i++) {
			playersReverse.add(players.get(i));
		}
		
		ELList<StatusEffect> debuffsPrevious = getPlayerBuffs(playersReverse.get(0));
		playersReverse.get(0).getEffects().removeAll(debuffsPrevious);
		ELList<StatusEffect> debuffsCurrent = null;
		for(int i = 1; i < playersReverse.size(); i++) {
			debuffsCurrent = getPlayerDebuffs(playersReverse.get(i));
			playersReverse.get(i).getEffects().removeAll(debuffsCurrent);
			playersReverse.get(i).getEffects().addAll(debuffsPrevious);
			debuffsPrevious = debuffsCurrent;
		}
		playersReverse.get(0).getEffects().addAll(debuffsPrevious);
	}
	
	private ELList<StatusEffect> getPlayerBuffs(Player p){
		ELList<StatusEffect> buffs = new ELList<StatusEffect>();
		
		for(StatusEffect se: p.getEffects()) {
			if(se.isBuff()) {
				buffs.add(se);
			}
		}
		
		return buffs;
	}
	
	private ELList<StatusEffect> getPlayerDebuffs(Player p){
		ELList<StatusEffect> debuffs = new ELList<StatusEffect>();
		
		for(StatusEffect se: p.getEffects()) {
			if(se.isDebuff()) {
				debuffs.add(se);
			}
		}
		
		return debuffs;
	}
	
}
