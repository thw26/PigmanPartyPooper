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
