package latmod.ingameapps.apps;

import ftb.lib.api.config.ClientConfigRegistry;
import latmod.lib.FastMap;
import latmod.lib.config.ConfigGroup;

/**
 * Created by LatvianModder on 26.12.2015.
 */
public class IngameAppRegistry
{
	public static final FastMap<String, IngameApp> apps = new FastMap<>();
	public static final ConfigGroup config_group = new ConfigGroup("ingame_apps");

	public static void register(IngameApp app)
	{
		apps.put(app.ID, app);
		config_group.add(app.enabled);
		app.load();
	}
}