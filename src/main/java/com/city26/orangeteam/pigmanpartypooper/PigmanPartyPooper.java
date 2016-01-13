package com.city26.orangeteam.pigmanpartypooper;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.action.LightningEvent;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.entity.SpawnEntityEvent.Spawner;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "PigmanPartyPooper", name = "Pigman Party Pooper", version = "1.3.0")
public class PigmanPartyPooper {

	boolean pigman = true, enderman = true, lightning = true;
	
	@Inject
	private Logger logger;
	
    @Listener
    public void gameInit(GameInitializationEvent event) {
    	logger.info("Registering Pigman Party Pooper Event Listener...");
    	if(pigman) getGame().getEventManager().registerListener(this, Spawner.class, new SpawnListener());
		getGame().getEventManager().registerListener(this, ChangeBlockEvent.Break.class, new EndermanWristSlappingListener(enderman));
		getGame().getEventManager().registerListener(this, LightningEvent.Strike.class, new LightningQuenchingListener(lightning));
	}
	
	public Game getGame() {
		return Sponge.getGame();
	}

}
