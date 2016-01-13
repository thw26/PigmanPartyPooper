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
