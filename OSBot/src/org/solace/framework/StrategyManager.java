package org.solace.framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.osbot.script.Script;

public abstract class StrategyManager extends Script {

	public List<Strategy> container = Collections
			.synchronizedList(new ArrayList<Strategy>());
	public Iterator<Strategy> task = null;
	private boolean shouldRun = false;

	public abstract boolean initiate();

	public void submitStrategy(Strategy... strategies) {
		if (strategies == null)
			return;
		for (Strategy st : strategies) {
			if (st != null && !container.contains(st)) {
				container.add(st);
			}
		}
	}

	public void revokeStrategy(Strategy... strategies) {
		if (strategies == null)
			return;
		for (Strategy st : strategies) {
			if (st != null && container.contains(st)) {
				container.remove(st);
			}
		}
	}

	public void onStart() {
		if (initiate()) {
			shouldRun = true;
		} else {
			try {
				warn("Conditions not met, exiting.");
				stop();
			} catch (InterruptedException e) {
			}
		}
	}

	public int onLoop() throws InterruptedException {
		if (shouldRun) {
			if (task == null || !task.hasNext())
				task = container.iterator();
			else {
				final Strategy current = task.next();
				if (current != null && current.enable()) {
					current.execute();
				}
			}
		}
		return 50;
	}

	public void onExit() {
		shouldRun = false;
		close();
	}

	public void close() {
		
	}

	public void onPaint(Graphics g) {
		Graphics2D g1 = (Graphics2D) g;
		paint(g1);
	}

	public abstract void paint(Graphics2D g1);

	public interface Filter<T> {
		public boolean accept(T element);
	}
	
}
