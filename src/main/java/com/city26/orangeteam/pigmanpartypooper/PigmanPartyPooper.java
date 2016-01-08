package com.city26.orangeteam.pigmanpartypooper;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.SpawnEntityEvent.Spawner;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "PigmanPartyPooper", name = "Pigman Party Pooper", version = "0.4.3")
public class PigmanPartyPooper {

	@Inject
	private Logger logger;
	
    @Listener
    public void gameInit(GameInitializationEvent event) {
    	logger.info("Registering Pigman Party Pooper Event Listener...");
		getGame().getEventManager().registerListener(this, Spawner.class, new SpawnListener(logger));
	}
	
	public Game getGame() {
		return Sponge.getGame();
	}

}
