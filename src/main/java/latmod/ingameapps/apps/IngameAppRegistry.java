package latmod.ingameapps.apps;

import ftb.lib.api.gui.PlayerActionRegistry;

import java.util.HashMap;

/**
 * Created by LatvianModder on 26.12.2015.
 */
public class IngameAppRegistry
{
	public static final HashMap<String, IngameApp> apps = new HashMap<>();
	
	public static void register(IngameApp app)
	{
		apps.put(app.ID, app);
		PlayerActionRegistry.add(app.playerAction);
		app.load();
	}
}