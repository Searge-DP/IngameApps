package latmod.ingameapps.apps;

import ftb.lib.TextureCoords;
import ftb.lib.api.PlayerAction;
import ftb.lib.api.friends.ILMPlayer;
import latmod.ingameapps.IngameApps;
import latmod.lib.util.FinalIDObject;
import net.minecraft.util.ResourceLocation;

public abstract class IngameApp extends FinalIDObject
{
	public final String resourcePath;
	public final PlayerAction playerAction;
	
	public IngameApp(String id, boolean enabledDef)
	{
		super(id);
		
		resourcePath = "apps/" + ID + "/";
		
		playerAction = new PlayerAction(PlayerAction.Type.SELF, "ingameapp." + ID, -20, TextureCoords.getSquareIcon(new ResourceLocation(IngameApps.MOD_ID_L, resourcePath + "icon.png"), 32))
		{
			public void onClicked(ILMPlayer self, ILMPlayer other)
			{ IngameApp.this.onButtonClicked(); }
		};
	}
	
	public abstract void load();
	public abstract void onButtonClicked();
}