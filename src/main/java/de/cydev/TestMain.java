package de.cydev;

import de.cydev.buildings.Bonus;
import de.cydev.buildings.Church;
import de.cydev.buildings.Farm;
import de.cydev.buildings.IBuilding;
import de.cydev.enums.Attribute;
import de.cydev.enums.Qualifier;
import de.cydev.game.Interaction;
import de.cydev.game.Player;

public class TestMain
{
	public static void main(String[] args) {
		Player player = new Player();
		IBuilding farm = new Farm();
		farm.getBonuses().add(new Bonus(Attribute.ABSOLUTE_PRODUCTIVITY, Qualifier.SELF, 100L));
		farm.getBonuses().add(new Bonus(Attribute.RELATIVE_PRODUCTIVITY, Qualifier.SELF, 50L));
		Interaction.buyBuilding(player, farm, 0, 0);
		Interaction.buyBuilding(player, new Church(), 0, 1);
		Interaction.buyBuilding(player, new Church(), 0, 2);
		Interaction.buyBuilding(player, new Farm(), 0, 3);
		Interaction.buyBuilding(player, new Farm(), 0, 4);
		Interaction.calculateProduction(player);
	}
}
