package com.city26.orangeteam.pigmanpartypooper;

import java.util.Optional;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.action.LightningEvent;

public class LightningQuenchingListener implements EventListener<LightningEvent.Strike>  {
	
	boolean lightning;
	
	public LightningQuenchingListener (boolean lightning) {
		this.lightning = lightning;
	}

	@Override
	public void handle(LightningEvent.Strike e) throws Exception {
		if(lightning) {
			try {
				Optional<Event> opt = Optional.of(e);
				if(opt.isPresent()) {
					if (e.getCause().toString().contains("Lightning") && e.getTransactions().toString().contains("minecraft:fire")) {
						e.getTransactions().clear();
					}
				}
			}
			catch(Error error) {
			}
		}
	}
}
