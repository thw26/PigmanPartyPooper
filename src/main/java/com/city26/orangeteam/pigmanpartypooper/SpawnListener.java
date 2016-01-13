package com.city26.orangeteam.pigmanpartypooper;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.property.block.GroundLuminanceProperty;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.entity.SpawnEntityEvent.Spawner;

public class SpawnListener implements EventListener<Spawner> {

	public SpawnListener () {
	}
	
	@Override
	public void handle(Spawner e) throws Exception {
		try {
			Optional<Spawner> opt = Optional.of(e);
			if(opt.isPresent()) {
				List<Entity> entities = opt.get().getEntities();
				try {
					for (Entity entity : entities) {
						if(entity.getType() == EntityTypes.PIG_ZOMBIE) {
							Collection<Property<?, ?>> properties = entity.getLocation().getApplicableProperties();
							for (Property<?, ?> property : properties) {
								if(property.getClass() == GroundLuminanceProperty.class) {
									if((double) property.getValue() > 7.0) {
										entity.remove();
									}
								}
							}
						}
					}
				} catch (ConcurrentModificationException err) {
				}
			}
		} catch(NoSuchElementException err) {
		}
	}
}
