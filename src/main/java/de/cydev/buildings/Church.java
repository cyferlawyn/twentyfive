package de.cydev.buildings;

import java.util.ArrayList;
import java.util.List;

import de.cydev.enums.Attribute;
import de.cydev.enums.Qualifier;

public class Church implements IBuilding
{
	private List<Bonus> bonuses;
	private List<Production> production;

	public Church()
	{
		super();
		
		setBonuses(new ArrayList<>());
		setProduction(new ArrayList<>());
		
		getBonuses().add(new Bonus(Attribute.RELATIVE_PRODUCTIVITY, Qualifier.GLOBAL, 25L));
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
