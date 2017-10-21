package nl.dizmizzer.factionisland.tpa;

import org.bukkit.entity.Player;

public class TPA {

	public Player p;
	public Long time;

	public TPA(Player p) {
		this.p = p;
		time = System.currentTimeMillis();
	}

}