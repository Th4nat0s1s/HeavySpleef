/*
 * This file is part of HeavySpleef.
 * Copyright (c) 2014-2016 Matthias Werning
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
package de.xaniox.heavyspleef.core.flag;

import org.apache.commons.lang.Validate;
import org.dom4j.Element;

/**
 * Represents a flag which has not been loaded yet. The value
 * of this flag is still stored in a xml {@link org.dom4j.Element}. You may retrieve
 * the actual flag with its value when its class is available in
 * a given {@link FlagRegistry} with {@link #loadFlag(FlagRegistry)}
 * 
 * @author matzefratze123
 */
public class UnloadedFlag extends NullFlag {
		
	private String flagName;
	private Element xmlElement;

	/**
	 * Checks wether or not the actual flag's class is available
	 * in a {@link FlagRegistry} 
	 */
	public boolean validateLoad(FlagRegistry registry) {
		Class<? extends AbstractFlag<?>> flagClass = registry.getFlagClass(flagName);
		return registry.isFlagPresent(flagClass) && registry.checkHooks(flagClass);
	}
	
	/**
	 * Sets any xml content of this unloaded flag which
	 * will be used for loading the flag if necessary
	 * 
	 * @param element The actual flag value in form of a xml element
	 */
	public void setXmlElement(Element element) {
		this.xmlElement = element;
		this.flagName = element.attributeValue("name");
	}
	
	/**
	 * Trys to load this flag from the given {@link FlagRegistry}. If there
	 * is no class yet available for this flag a {@link NoSuchFlagException} will be thrown
	 * 
	 * @param registry The registry from which this flag is being loaded
	 * @return A fresh instance of the actual flag
	 */
	public AbstractFlag<?> loadFlag(FlagRegistry registry) {
		Validate.notNull(flagName, "Flag xml must be set before calling loadFlag");
		
		if (!validateLoad(registry)) {
			throw new NoSuchFlagException("Flag cannot be loaded as its flag class is not registered");
		}
		
		AbstractFlag<?> flag = registry.newFlagInstance(flagName, AbstractFlag.class);
		flag.unmarshal(xmlElement);
		return flag;
	}
	
	@Override
	public String getValueAsString() {
		return "Add-On currently not installed";
	}
	
	public String getFlagName() {
		return flagName;
	}
	
	public Element getXmlElement() {
		return xmlElement;
	}
	
}