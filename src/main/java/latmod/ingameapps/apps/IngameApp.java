package latmod.ingameapps.apps;

import ftb.lib.api.PlayerAction;
import ftb.lib.client.TextureCoords;
import latmod.ingameapps.IngameApps;
import latmod.lib.config.ConfigEntryBool;
import latmod.lib.util.FinalIDObject;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public abstract class IngameApp extends FinalIDObject
{
	public final String fullID;
	public final String resourcePath;
	public final ConfigEntryBool enabled;
	public final PlayerAction playerAction;

	public IngameApp(String id, boolean enabledDef)
	{
		super(id);

		fullID = "ingameapp." + ID;
		resourcePath = "apps/" + ID + "/";

		enabled = new ConfigEntryBool(id, enabledDef)
		{
			public String getFullID()
			{ return IngameApp.this.fullID; }
		};

		playerAction = new PlayerAction(fullID, TextureCoords.getSquareIcon(new ResourceLocation(IngameApps.MOD_ID_L, resourcePath + "icon.png"), 32))
		{
			public void onClicked(int playerID)
			{ IngameApp.this.onButtonClicked(); }

			public String getTitleKey()
			{ return ID; }
		};
	}

	public String getTitle()
	{ return I18n.format(fullID); }

	public abstract void load();
	public abstract void onButtonClicked();
}