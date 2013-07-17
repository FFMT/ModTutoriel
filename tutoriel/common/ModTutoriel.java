package tutoriel.common;

import tutoriel.proxy.TutoCommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ModTutoriel", name = "Mod Tutoriel", version = "1.0.0", acceptedMinecraftVersions = "[1.6.1,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ModTutoriel 
{
	@SidedProxy(clientSide = "tutoriel.proxy.TutoClientProxy", serverSide = "tutoriel.proxy.TutoCommonProxy")
	public static TutoCommonProxy proxy;
	
	@Instance("ModTutoriel")
	public static ModTutoriel instance;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		//Configuration
		
		//Blocks
		
		//Items
		
		//Achievements
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		//Registry

		//Mobs
		
		//Render
		proxy.registerRender();
		//NetWork
		
		//Recette - Recipe
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		//Integration avec les autres mods - integration with others mods
	}
}