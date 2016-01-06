package latmod.ingameapps;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.*;
import ftb.lib.api.EventPlayerActionButtons;
import latmod.ingameapps.apps.*;
import latmod.lib.LMMapUtils;

public class IngameAppsEventHandler
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void addButtons(EventPlayerActionButtons e)
	{
		for(IngameApp app : LMMapUtils.values(IngameAppRegistry.apps, null))
		{
			if(e.addAll || app.enabled.get()) e.actions.add(app.playerAction);
		}
	}
}