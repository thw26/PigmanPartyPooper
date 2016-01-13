/*
 * This file is licensed under the MIT License (MIT).
 * 
 * Copyright (c) 2016 T. H. Wright
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
