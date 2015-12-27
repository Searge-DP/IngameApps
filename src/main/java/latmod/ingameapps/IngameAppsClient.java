package latmod.ingameapps;
import cpw.mods.fml.relauncher.*;
import ftb.lib.EventBusHelper;
import ftb.lib.api.config.ClientConfigRegistry;
import latmod.ingameapps.apps.IngameAppRegistry;
import latmod.ingameapps.apps.slack.SlackApp;
import latmod.ingameapps.apps.trello.TrelloApp;

@SideOnly(Side.CLIENT)
public class IngameAppsClient extends IngameAppsCommon
{
	public void load()
	{
		EventBusHelper.register(new IngameAppsEventHandler());
		ClientConfigRegistry.add(IngameAppRegistry.config_group);

		IngameAppRegistry.register(new TrelloApp());
		IngameAppRegistry.register(new SlackApp());
	}
}