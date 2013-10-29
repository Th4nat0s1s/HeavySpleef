/**
 *   HeavySpleef - The simple spleef plugin for bukkit
 *   
 *   Copyright (C) 2013 matzefratze123
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package de.matzefratze123.heavyspleef.command;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.matzefratze123.heavyspleef.HeavySpleef;
import de.matzefratze123.heavyspleef.command.UserType.Type;
import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.core.GameManager;
import de.matzefratze123.heavyspleef.util.Permissions;

@UserType(Type.PLAYER)
public class CommandVote extends HSCommand {

	public CommandVote() {
		setPermission(Permissions.VOTE);
		setOnlyIngame(true);
		setUsage("/spleef vote");
		setHelp("Votes to start the game");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = (Player)sender;
		
		if (!HeavySpleef.getSystemConfig().getBoolean("general.autostart-vote-enabled", true)) {
			player.sendMessage(_("votesDisabled"));
			return;
		}
		
		if (!GameManager.isActive(player)) {
			player.sendMessage(_("onlyLobby"));
			return;
		}
		
		Game game = GameManager.fromPlayer(player);
		if (!game.isPreLobby()) {
			player.sendMessage(_("onlyLobby"));
			return;
		}
		if (game.hasVote(player)) {
			player.sendMessage(_("alreadyVoted"));
			return;
		}
		
		game.addVote(player);
		player.sendMessage(_("successfullyVoted"));
	}

}
