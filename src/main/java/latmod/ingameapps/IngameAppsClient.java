package latmod.ingameapps;

import cpw.mods.fml.relauncher.*;
import latmod.ingameapps.apps.IngameAppRegistry;
import latmod.ingameapps.apps.slack.SlackApp;
import latmod.ingameapps.apps.trello.TrelloApp;

@SideOnly(Side.CLIENT)
public class IngameAppsClient extends IngameAppsCommon
{
	public void load()
	{
		IngameAppRegistry.register(new TrelloApp());
		IngameAppRegistry.register(new SlackApp());
	}
}