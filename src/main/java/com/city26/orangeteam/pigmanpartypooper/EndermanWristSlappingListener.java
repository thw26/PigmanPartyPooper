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

import java.util.Optional;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

public class EndermanWristSlappingListener implements EventListener<ChangeBlockEvent.Break>  {

	boolean enderman;
	
	public EndermanWristSlappingListener (boolean enderman) {

		this.enderman = enderman;
	}

	@Override
	public void handle(ChangeBlockEvent.Break e) throws Exception {
		if(enderman) {
			try {
				Optional<Event> opt = Optional.of(e);
				if(opt.isPresent()) {
					if(e.getCause().getNamedCauses().get("Source").toString().contains("Enderman")) {
						e.setCancelled(true);
					}
				}
			}
			catch(Error error) {
			}
		}
	}
}
