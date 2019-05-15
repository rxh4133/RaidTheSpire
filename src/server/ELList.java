package server;

import java.util.ArrayList;
import java.util.Collections;

import global.ELM;
import global.Entity;
import global.NotifyPayload;

public class ELList<F extends EntityListener> extends ArrayList<F> {
	private static final long serialVersionUID = 1L;
	
	private boolean iterating;
	private ArrayList<EntityListener> toRemove;
	private ArrayList<F> toAdd;
	
	public ELList() {
		super();
		toRemove = new ArrayList<EntityListener>();
		toAdd = new ArrayList<F>();
	}
	
	public void notifyAll(Entity e, ELM mesage, NotifyPayload data) {
		iterating = true;
		Collections.sort(this);
		ActionInteruptException doot = null;
		for(int i = 0; i < size(); i++) {
			try {
				get(i).notify(e, mesage, data);
			}catch(ActionInteruptException afe) {
				doot = afe;
			}
		}
		iterating = false;
		removeAll(toRemove);
		toRemove.removeAll(toRemove);
		addAll(toAdd);
		toAdd.removeAll(toAdd);
		if(doot != null) {
			throw doot;
		}
	}
	
	public void startIterating() {
		iterating = true;
	}
	
	public void stopIterating() {
		iterating = false;
		removeAll(toRemove);
		toRemove.removeAll(toRemove);
		addAll(toAdd);
		toAdd.removeAll(toAdd);
	}
	
	public F remove(int i) {
		if(iterating) {
			toRemove.add(this.get(i));
			return null;
		}else {
			return super.remove(i);
		}
	}
	
	@Override
	public boolean remove(Object f) {
		if(iterating) {
			if(contains(f) && f instanceof EntityListener) {
				toRemove.add((EntityListener) f);
			}
		}else {
			return super.remove(f);
		}
		return false;
	}
	
	public boolean add(F f) {
		if(iterating) {
			toAdd.add(f);
		}else {
			return super.add(f);
		}
		return false;
	}

}
