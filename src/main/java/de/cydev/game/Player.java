package de.cydev.game;

import de.cydev.buildings.Empty;
import de.cydev.buildings.IBuilding;

public class Player
{
	private IBuilding[][] buildings;

	public Player()
	{
		setBuildings(new IBuilding[5][5]);
		
		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
			{
				getBuildings()[row][col] = new Empty();
			}
		}
	}

	public IBuilding[][] getBuildings()
	{
		return buildings;
	}

	public void setBuildings(IBuilding[][] buildings)
	{
		this.buildings = buildings;
	}
}
