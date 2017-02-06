package de.cydev.buildings;

import java.util.ArrayList;
import java.util.List;

public class Empty implements IBuilding
{
	private List<Bonus> bonuses;
	private List<Production> production;

	public Empty()
	{
		super();
		
		setBonuses(new ArrayList<>());
		setProduction(new ArrayList<>());
	}

	@Override
	public List<Bonus> getBonuses()
	{
		return bonuses;
	}

	public void setBonuses(List<Bonus> bonuses)
	{
		this.bonuses = bonuses;
	}

	@Override
	public List<Production> getProduction()
	{
		return production;
	}

	public void setProduction(List<Production> production)
	{
		this.production = production;
	}
}
