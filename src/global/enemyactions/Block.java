package global.enemyactions;

import global.Enemy;
import global.EnemyAction;
import server.ServerDataHandler;

public class Block extends EnemyAction{
	private static final long serialVersionUID = 1L;
	
	private int blckAmt;
	public Block(Enemy e, String n, ServerDataHandler sdh, int block) {
		super(e, n, sdh);
		blckAmt = block;
	}
	public Block(Enemy e, ServerDataHandler sdh, int block) {
		super(e, "Block", sdh);
		blckAmt = block;
	}
	
	public void doAction() {
		enemy.gainBlock(blckAmt);
	}
	
	public String toString() {
		return name + ": B:" + blckAmt;
	}
}
