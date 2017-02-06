package de.cydev.buildings;

import de.cydev.enums.Attribute;
import de.cydev.enums.Qualifier;

public class Bonus
{
	private Attribute attribute;
	private Qualifier qualifier;
	private Long value;
	
	public Bonus(Attribute attribute, Qualifier qualifier, Long value)
	{
		super();
		this.attribute = attribute;
		this.qualifier = qualifier;
		this.value = value;
	}
	
	public Attribute getAttribute()
	{
		return attribute;
	}
	public void setAttribute(Attribute attribute)
	{
		this.attribute = attribute;
	}
	public Qualifier getQualifier()
	{
		return qualifier;
	}
	public void setQualifier(Qualifier qualifier)
	{
		this.qualifier = qualifier;
	}
	public Long getValue()
	{
		return value;
	}
	public void setValue(Long value)
	{
		this.value = value;
	}
}
