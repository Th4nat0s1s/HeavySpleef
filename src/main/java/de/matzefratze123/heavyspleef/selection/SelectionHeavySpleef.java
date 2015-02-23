/*
 * HeavySpleef - Advanced spleef plugin for bukkit
 *
 * Copyright (C) 2013-2014 matzefratze123
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.matzefratze123.heavyspleef.selection;

import org.bukkit.Location;

public class SelectionHeavySpleef extends Selection {

	private Location	first;
	private Location	second;

	public SelectionHeavySpleef(String owner) {
		super(owner);
	}

	@Override
	public Location getFirst() {
		return this.first;
	}

	@Override
	public Location getSecond() {
		return this.second;
	}

	@Override
	public boolean hasSelection() {
		return this.first != null && this.second != null;
	}

	@Override
	public boolean isTroughWorlds() {
		if (this.first == null || this.second == null)
			return false;

		return this.first.getWorld() != this.second.getWorld();
	}

	@Override
	public void setFirst(Location location) {
		this.first = location;
	}

	@Override
	public void setSecond(Location location) {
		this.second = location;
	}

}
