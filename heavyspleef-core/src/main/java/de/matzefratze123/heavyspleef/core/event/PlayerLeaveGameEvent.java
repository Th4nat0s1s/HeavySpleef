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
package de.matzefratze123.heavyspleef.core.event;

import org.bukkit.Location;

import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.core.QuitCause;
import de.matzefratze123.heavyspleef.core.player.SpleefPlayer;

public class PlayerLeaveGameEvent extends PlayerGameEvent implements Cancellable {

	private boolean cancel;
	private QuitCause cause;
	private Location teleportationLocation;
	private SpleefPlayer killer;
	
	public PlayerLeaveGameEvent(Game game, SpleefPlayer player, SpleefPlayer killer, QuitCause cause) {
		super(game, player);
		
		this.killer = killer;
		this.cause = cause;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}
	
	public QuitCause getCause() {
		return cause;
	}
	
	public SpleefPlayer getKiller() {
		return killer;
	}

	public void setTeleportationLocation(Location location) {
		this.teleportationLocation = location;
	}
	
	public Location getTeleportationLocation() {
		return teleportationLocation;
	}
	
}
