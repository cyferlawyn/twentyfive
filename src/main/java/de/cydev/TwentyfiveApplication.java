package de.cydev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.cydev.buildings.Farm;
import de.cydev.game.Interaction;
import de.cydev.game.Player;

@SpringBootApplication
@EnableAutoConfiguration
public class TwentyfiveApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TwentyfiveApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception
	{
		Player player = new Player();
		Interaction.buyBuilding(player, new Farm(), 2, 2);
		Interaction.buyBuilding(player, new Farm(), 1, 2);
		Interaction.calculateProduction(player);
	}
}
