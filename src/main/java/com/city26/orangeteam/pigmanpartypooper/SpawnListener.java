package com.city26.orangeteam.pigmanpartypooper;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.spongepowered.api.data.property.block.GroundLuminanceProperty;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.entity.SpawnEntityEvent.Spawner;

import com.google.inject.Inject;

public class SpawnListener implements EventListener<Spawner> {

	@Inject
	private final Logger logger;
	
	public SpawnListener (Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public void handle(Spawner e) throws Exception {
		try {
			Optional<Spawner> opt = Optional.of(e);
			if(opt.isPresent()) {
				List<Entity> entities = e.getEntities();
				try {
					for (Entity entity : entities) {logger.info("Pigman Party Pooper: For ENTITY TYPE TEST: " + entity.getType().toString());
						if (e.filterEntities(isPigman(entity)) != EntityTypes.PIG_ZOMBIE) {
							if (entity.getType() == EntityTypes.PIG_ZOMBIE) {
								if (entity.getLocation().getBlock().getProperty(GroundLuminanceProperty.class).get().compareTo(GroundLuminanceProperty.greaterThan(7)) > 0) {logger.info("Pigman Party Pooper: LIGHT LEVEL TEST: " + entity.getLocation().getBlock().getProperty(GroundLuminanceProperty.class).get().toString());
									logger.info("PPP: Cancelling spawn.");
									e.setCancelled(true);
								}
							}
						}
					}
				} catch (ConcurrentModificationException err) {
					logger.info("Pigman Party Pooper expected ConcurrentModificationException.");
				}
			}
		} catch(NoSuchElementException err) {
			    logger.info("Pigman Party Pooper expected NoSuchElementException.");
		}
	}
	
	public static Predicate<Entity> isPigman(Entity entity) {
		return e -> entity.getType() == EntityTypes.PIG_ZOMBIE;
	}

}
