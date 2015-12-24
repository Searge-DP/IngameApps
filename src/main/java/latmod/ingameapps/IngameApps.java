package latmod.ingameapps;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ftb.lib.EventBusHelper;

@Mod(modid = IngameApps.MOD_ID, name = "IngameApps", version = "@VERSION@", dependencies = "required-after:FTBL")
public class IngameApps
{
	public static final String MOD_ID = "IngameApps";
	public static final String MOD_ID_L = MOD_ID.toLowerCase();
	public static final String ASSETS = MOD_ID_L + ':';
	
	@Mod.Instance(IngameApps.MOD_ID)
	public static IngameApps inst;
	
	@SidedProxy(clientSide = "latmod.ingameapps.IngameAppsClient", serverSide = "latmod.ingameapps.IngameAppsCommon")
	public static IngameAppsCommon proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		IngameAppsConfig.load();
		proxy.load();
		EventBusHelper.register(new IngameAppsEventHandler());
	}
}