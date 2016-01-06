package latmod.ingameapps.apps;

import latmod.lib.config.ConfigGroup;

import java.util.HashMap;

/**
 * Created by LatvianModder on 26.12.2015.
 */
public class IngameAppRegistry
{
	public static final HashMap<String, IngameApp> apps = new HashMap<>();
	public static final ConfigGroup config_group = new ConfigGroup("ingame_apps");

	public static void register(IngameApp app)
	{
		apps.put(app.ID, app);
		config_group.add(app.enabled, false);
		app.load();
	}
}