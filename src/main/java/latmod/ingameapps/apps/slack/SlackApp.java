package latmod.ingameapps.apps.slack;

import ftb.lib.FTBLib;
import latmod.ingameapps.apps.IngameApp;

/**
 * Created by LatvianModder on 26.12.2015.
 */
public class SlackApp extends IngameApp
{
	public SlackApp()
	{
		super("slack", true);
	}
	
	public void load()
	{
	}
	
	public void onButtonClicked()
	{
		FTBLib.printChat(null, "Slack integration is not implemented yet!");
	}
}