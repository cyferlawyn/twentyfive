package de.cydev.buildings;

import de.cydev.enums.Currency;

public class Production
{
	private Currency currency;
	private Long value;
	
	public Production(Currency currency, Long value)
	{
		super();
		
		this.currency = currency;
		this.value = value;
	}
	
	public Currency getCurrency()
	{
		return currency;
	}
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
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
