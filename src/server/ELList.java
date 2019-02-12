package server;

import java.util.ArrayList;

import global.ELM;
import global.Entity;

public class ELList<F extends EntityListener> extends ArrayList<F> {
	private static final long serialVersionUID = 1L;
	
	private boolean notifying;
	private ArrayList<EntityListener> toRemove;
	private ArrayList<F> toAdd;
	
	public ELList() {
		super();
		toRemove = new ArrayList<EntityListener>();
		toAdd = new ArrayList<F>();
	}
	
	public void notifyAll(Entity e, ELM message, Object data) {
		notifying = true;
		int modify = 0;
		ActionInteruptException doot = null;
		for(int i = 0; i < size(); i++) {
			try {
				get(i).notify(e, message, data);
			}catch(ModifyValueException mbge) {
				modify += mbge.modifier;
			}catch(ActionInteruptException afe) {
				doot = afe;
			}
		}
		notifying = false;
		removeAll(toRemove);
		toRemove.removeAll(toRemove);
		addAll(toAdd);
		toAdd.removeAll(toAdd);
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
	
	@Override
	public boolean remove(Object f) {
		if(notifying) {
			if(contains(f) && f instanceof EntityListener) {
				toRemove.add((EntityListener) f);
			}
		}else {
			return super.remove(f);
		}
		return false;
	}
	
	public boolean add(F f) {
		if(notifying) {
			toAdd.add(f);
		}else {
			return super.add(f);
		}
		return false;
	}

}
