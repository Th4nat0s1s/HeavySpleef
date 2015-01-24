/*
 * This file is part of HeavySpleef.
 * Copyright (c) 2014-2015 matzefratze123
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.matzefratze123.heavyspleef.flag.defaults;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import de.matzefratze123.heavyspleef.core.GameProperty;
import de.matzefratze123.heavyspleef.core.event.GameEndEvent;
import de.matzefratze123.heavyspleef.core.event.GameListener;
import de.matzefratze123.heavyspleef.core.event.GameStartEvent;
import de.matzefratze123.heavyspleef.core.event.PlayerLoseGameEvent;
import de.matzefratze123.heavyspleef.core.flag.Flag;
import de.matzefratze123.heavyspleef.core.player.SpleefPlayer;
import de.matzefratze123.heavyspleef.flag.presets.BooleanFlag;

@Flag(name = "scoreboard")
public class FlagScoreboard extends BooleanFlag {

	private static final String SCOREBOARD_NAME = "heavyspleef";
	private static final String SCOREBOARD_CRITERIA = "dummy";
	
	private static final String OBJECTIVE_NAME = ChatColor.GOLD + "Knockouts";
	
	private static final String IS_ALIVE_SYMBOL = ChatColor.GREEN + "\u2714 " + ChatColor.WHITE;
	private static final String IS_DEAD_SYMBOL = ChatColor.RED + "\u2718 " + ChatColor.GRAY;
	
	private final ScoreboardManager manager;
	private Scoreboard scoreboard;
	private Objective objective;
	
	public FlagScoreboard() {
		this.manager = Bukkit.getScoreboardManager();
	}
	
	@Override
	public void defineGameProperties(Map<GameProperty, Object> properties) {}

	@Override
	public boolean hasGameProperties() {
		return false;
	}

	@Override
	public boolean hasBukkitListenerMethods() {
		return false;
	}

	@Override
	public void getDescription(List<String> description) {
		description.add("Enables a sidebar scoreboard to show the status of the game");
	}
	
	@GameListener
	public void onGameStart(GameStartEvent event) {
		scoreboard = manager.getNewScoreboard();
		objective = scoreboard.registerNewObjective(SCOREBOARD_NAME, SCOREBOARD_CRITERIA);
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(OBJECTIVE_NAME);
		
		for (SpleefPlayer player : event.getGame().getPlayers()) {
			Team team = scoreboard.registerNewTeam(player.getName());
			team.setPrefix(IS_ALIVE_SYMBOL);
			team.addPlayer(player.getBukkitPlayer());
			
			Score score = objective.getScore(player.getName());
			score.setScore(0);
			
			player.getBukkitPlayer().setScoreboard(scoreboard);
		}
	}
	
	@GameListener
	public void onPlayerLose(PlayerLoseGameEvent event) {
		SpleefPlayer player = event.getPlayer();
		
		Team team = scoreboard.getTeam(player.getName());
		team.setPrefix(IS_DEAD_SYMBOL);
		
		//Note: Scoreboard restoring is managed by the PlayerState
		
		OfflinePlayer killer = event.getKiller();
		if (killer != null) {
			Score killerScore = objective.getScore(killer.getName());
			int previousScore = killerScore.getScore();
			
			killerScore.setScore(++previousScore);
		}
	}
	
	@GameListener
	public void onGameEnd(GameEndEvent event) {
		//Remove that reference
		scoreboard = null;
	}

}
