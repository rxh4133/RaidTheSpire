package server;

import java.util.ArrayList;
import java.util.Collections;

import global.ELM;
import global.Entity;
import global.NotifyPayload;

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
	
	public void notifyAll(Entity e, ELM mesage, NotifyPayload data) {
		notifying = true;
		Collections.sort(this);
		boolean modified = true;
		ActionInteruptException doot = null;
		for(int i = 0; i < size(); i++) {
			try {
				get(i).notify(e, mesage, data);
			}catch(ModifyValueException mbge) {
				data.n += mbge.modifier;
				modified = true;
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
		}else if(modified) {
			throw new ModifyValueException(data.n);
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
