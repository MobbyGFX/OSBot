package org.solace.scripts;

import java.awt.Graphics2D;

import org.osbot.script.ScriptManifest;
import org.solace.framework.Strategy;
import org.solace.framework.StrategyManager;

@ScriptManifest(author = "Solace Coding", info = "Type information like here like?", name = "Rouge Den Cooker", version = 0)
public class TestScript extends StrategyManager {
	
	public boolean initiate() {
		submitStrategy(new Strategy[] {  });
		return true;
	}

	public void paint(Graphics2D g1) {
		g1.drawString("Running...", 20, 20);
	}
	
}
