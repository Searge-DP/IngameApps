package latmod.ingameapps;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.*;
import ftb.lib.api.EventPlayerActionButtons;

public class IngameAppsEventHandler
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addButtons(EventPlayerActionButtons e)
	{
		if(e.addAll || IngameAppsClient.add_trello.get()) e.actions.add(IngameAppsClient.trello);
		if(e.addAll || IngameAppsClient.add_slack.get()) e.actions.add(IngameAppsClient.slack);
	}
}