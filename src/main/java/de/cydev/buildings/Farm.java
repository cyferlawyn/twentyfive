package de.cydev.buildings;

import java.util.ArrayList;
import java.util.List;

import de.cydev.enums.Currency;

public class Farm implements IBuilding
{
	private List<Bonus> bonuses;
	private List<Production> production;

	public Farm()
	{
		super();
		
		setBonuses(new ArrayList<>());
		setProduction(new ArrayList<>());
		
		getProduction().add(new Production(Currency.WHEAT, 100L));
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
