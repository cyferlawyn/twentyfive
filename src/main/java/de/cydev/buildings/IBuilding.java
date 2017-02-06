package de.cydev.buildings;

import java.util.List;

public interface IBuilding
{
	List<Bonus> getBonuses();
	
	List<Production> getProduction();
}
