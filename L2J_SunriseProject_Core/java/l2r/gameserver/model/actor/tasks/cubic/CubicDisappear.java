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
package l2r.gameserver.model.actor.tasks.cubic;

import l2r.gameserver.model.actor.instance.L2CubicInstance;

/**
 * Cubic disappear task.
 * @author L2GamePlay Team
 */
public class CubicDisappear implements Runnable
{
	private final L2CubicInstance _cubic;
	
	public CubicDisappear(L2CubicInstance cubic)
	{
		_cubic = cubic;
	}
	
	@Override
	public void run()
	{
		if (_cubic != null)
		{
			_cubic.stopAction();
			_cubic.cancelDisappear();
			_cubic.getOwner().getCubics().remove(_cubic.getId());
			_cubic.getOwner().broadcastUserInfo();
		}
	}
}
