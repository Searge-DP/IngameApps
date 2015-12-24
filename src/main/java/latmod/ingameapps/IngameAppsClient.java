package latmod.ingameapps;
import cpw.mods.fml.relauncher.*;
import ftb.lib.api.PlayerAction;
import ftb.lib.api.config.ClientConfigRegistry;
import ftb.lib.client.TextureCoords;
import latmod.lib.config.*;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class IngameAppsClient extends IngameAppsCommon
{
	public static final PlayerAction trello = new PlayerAction(TextureCoords.getSquareIcon(new ResourceLocation(IngameApps.MOD_ID_L, "textures/trello.png"), 32))
	{
		public void onClicked(int playerID)
		{ /* TODO: Trello */ }
		
		public String getTitle()
		{ return ClientConfigRegistry.provider.getEntryTitle(add_trello); }
	};
	
	public static final PlayerAction slack = new PlayerAction(TextureCoords.getSquareIcon(new ResourceLocation(IngameApps.MOD_ID_L, "textures/slack.png"), 32))
	{
		public void onClicked(int playerID)
		{ /* TODO: Slack */ }
		
		public String getTitle()
		{ return ClientConfigRegistry.provider.getEntryTitle(add_slack); }
	};
	
	public static final ConfigEntryBool add_trello = new ConfigEntryBool("trello", false);
	public static final ConfigEntryBool add_slack = new ConfigEntryBool("slack", false);
	
	public void load()
	{
		ClientConfigRegistry.add(new ConfigGroup("ingame_apps").addAll(IngameAppsClient.class));
	}
}