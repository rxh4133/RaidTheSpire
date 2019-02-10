package server;

import java.util.ArrayList;

import global.ELM;
import global.Entity;

public class ELList<F extends EntityListener> extends ArrayList<F> {
	private static final long serialVersionUID = 1L;
	
	private boolean notifying;
	private ArrayList<F> toRemove;
	
	public ELList() {
		super();
		toRemove = new ArrayList<F>();
	}
	
	public void notifyAll(Entity e, ELM message, Object data) {
		notifying = true;
		int modify = 0;
		ActionInteruptException doot = null;
		for(F f: this) {
			try {
				f.notify(e, message, data);
			}catch(ModifyValueException mbge) {
				modify += mbge.modifier;
			}catch(ActionInteruptException afe) {
				doot = afe;
			}
		}
		notifying = false;
		removeAll(toRemove);
		toRemove.removeAll(toRemove);
		if(doot != null) {
			throw doot;
		}else if(modify != 0) {
			throw new ModifyValueException(modify);
		}
	}
	
	public F remove(int i) {
		if(notifying) {
			toRemove.add(this.get(i));
			return null;
		}else {
			return super.remove(i);
		}
	}
	
	public boolean remove(F f) {
		if(notifying) {
			if(contains(f)) {
				toRemove.add(f);
			}
		}else {
			return super.remove(f);
		}
		return false;
	}

}
