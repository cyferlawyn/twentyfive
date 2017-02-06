package de.cydev.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.cydev.buildings.Bonus;
import de.cydev.buildings.Empty;
import de.cydev.buildings.IBuilding;
import de.cydev.buildings.Production;
import de.cydev.enums.Attribute;
import de.cydev.enums.Currency;

public class Interaction
{
	public static boolean buyBuilding(Player player, IBuilding building, int row, int col)
	{
		IBuilding[][] buildings = player.getBuildings();
		if (!(buildings[row][col] instanceof Empty))
		{
			return false;
		}

		buildings[row][col] = building;

		return true;
	}

	public static boolean calculateProduction(Player player)
	{
		Long[][] relativeBonuses = prepareArray();
		Long[][] absoluteBonuses = prepareArray();

		calculateProductionBonuses(player, relativeBonuses, absoluteBonuses);

		Map<Currency, Long> effectiveProduction = calculateEffectiveProduction(player, relativeBonuses, absoluteBonuses);
		
		for (Entry<Currency, Long> entry : effectiveProduction.entrySet())
		{
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		return true;
	}

	private static void applyBonusToField(Long[][] bonuses, Bonus bonus, int col, int row)
	{
		bonuses[col][row] += bonus.getValue();
	}

	private static void applyBonusToRow(Long[][] bonuses, Bonus bonus, int row)
	{
		for (int col = 0; col < 5; col++)
		{
			bonuses[col][row] += bonus.getValue();
		}
	}

	private static void applyBonusToColumn(Long[][] bonuses, Bonus bonus, int col)
	{
		for (int row = 0; row < 5; row++)
		{
			bonuses[col][row] += bonus.getValue();
		}
	}

	private static void applyBonusToAllFields(Long[][] bonuses, Bonus bonus)
	{
		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
			{
				bonuses[col][row] += bonus.getValue();
			}
		}
	}

	private static Long[][] prepareArray()
	{
		Long[][] array = new Long[5][5];

		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
			{
				array[col][row] = 0L;
			}
		}

		return array;
	}

	private static void calculateProductionBonuses(Player player, Long[][] relativeBonuses, Long[][] absoluteBonuses)
	{
		IBuilding[][] buildings = player.getBuildings();

		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
			{
				for (Bonus bonus : buildings[row][col].getBonuses())
				{
					switch (bonus.getQualifier())
					{
					case SELF:
						if (bonus.getAttribute() == Attribute.RELATIVE_PRODUCTIVITY)
							applyBonusToField(relativeBonuses, bonus, col, row);
						if (bonus.getAttribute() == Attribute.ABSOLUTE_PRODUCTIVITY)
							applyBonusToField(absoluteBonuses, bonus, col, row);
						break;
					case COLUMN:
						if (bonus.getAttribute() == Attribute.RELATIVE_PRODUCTIVITY)
							applyBonusToColumn(relativeBonuses, bonus, col);
						if (bonus.getAttribute() == Attribute.ABSOLUTE_PRODUCTIVITY)
							applyBonusToColumn(absoluteBonuses, bonus, col);
						break;
					case ROW:
						if (bonus.getAttribute() == Attribute.RELATIVE_PRODUCTIVITY)
							applyBonusToRow(relativeBonuses, bonus, row);
						if (bonus.getAttribute() == Attribute.ABSOLUTE_PRODUCTIVITY)
							applyBonusToRow(absoluteBonuses, bonus, row);
						break;
					case GLOBAL:
						if (bonus.getAttribute() == Attribute.RELATIVE_PRODUCTIVITY)
							applyBonusToAllFields(relativeBonuses, bonus);
						if (bonus.getAttribute() == Attribute.ABSOLUTE_PRODUCTIVITY)
							applyBonusToAllFields(absoluteBonuses, bonus);
						break;
					default:
						break;
					}
				}
			}
		}
	}

	private static Map<Currency, Long> calculateEffectiveProduction(Player player, Long[][] relativeBonuses, Long[][] absoluteBonuses)
	{
		IBuilding[][] buildings = player.getBuildings();

		Map<Currency, Long> effectiveProduction = new HashMap<>();

		for (int row = 0; row < 5; row++)
		{
			for (int col = 0; col < 5; col++)
			{
				for (Production production : buildings[col][row].getProduction())
				{
					Currency currency = production.getCurrency();
					Long formerValue = 0L;

					if (effectiveProduction.containsKey(currency))
					{
						formerValue = effectiveProduction.get(currency);
					}

					Long newValue = production.getValue();
					newValue += absoluteBonuses[col][row];
					Double relativeMultiplier = 1 + ((double)relativeBonuses[col][row] / 100);
					newValue = formerValue + (long)(newValue * relativeMultiplier);					
					
					
					effectiveProduction.put(currency, newValue);
				}
			}
		}
		
		return effectiveProduction;
	}
}
