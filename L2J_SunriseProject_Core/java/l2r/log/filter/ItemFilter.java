/*
 * Copyright (C) 2004-2015 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package l2r.log.filter;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import l2r.gameserver.model.items.instance.L2ItemInstance;
import l2r.gameserver.model.items.type.EtcItemType;
import l2r.gameserver.model.items.type.ItemType;

/**
 * @author L2GamePlay Team Team
 */
public class ItemFilter implements Filter
{
	// This is an example how to exclude consuming of shots and arrows from logging
	private static final String EXCLUDE_PROCESS = "Consume";
	private static final Set<ItemType> EXCLUDED_ITEM_TYPES = new HashSet<>();
	
	static
	{
		EXCLUDED_ITEM_TYPES.add(EtcItemType.ARROW);
		EXCLUDED_ITEM_TYPES.add(EtcItemType.SHOT);
	}
	
	@Override
	public boolean isLoggable(LogRecord record)
	{
		if (!"item".equals(record.getLoggerName()))
		{
			return false;
		}
		
		String[] messageList = record.getMessage().split(":");
		if ((messageList.length < 2) || !EXCLUDE_PROCESS.contains(messageList[1]))
		{
			return true;
		}
		
		L2ItemInstance item = ((L2ItemInstance) record.getParameters()[0]);
		if (!EXCLUDED_ITEM_TYPES.contains(item.getItemType()))
		{
			return true;
		}
		
		return false;
	}
}
