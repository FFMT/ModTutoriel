package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import tutoriel.proxy.TutoCommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ModTutoriel", name = "Mod Tutoriel", version = "1.0.0", acceptedMinecraftVersions = "[1.6.1,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ModTutoriel 
{
	@SidedProxy(clientSide = "tutoriel.proxy.TutoClientProxy", serverSide = "tutoriel.proxy.TutoCommonProxy")
	public static TutoCommonProxy proxy;
	
	@Instance("ModTutoriel")
	public static ModTutoriel instance;
	
	//declaration des blocs - blocks statement
	public static Block BlockTutorial, TutorialMetadata;
	public static Item ItemTutorial;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		//Configuration
		
		//Blocks
		BlockTutorial = new BlockTutorial(2000).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("BlockTutorial").func_111022_d("modtutoriel:BlockTutorial");
		TutorialMetadata = new BlockTutorialMetadata(2001).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("TutorialMetadata");

		//Enregistrement des blocs - Blocks registry
		GameRegistry.registerBlock(BlockTutorial, "BlockTutorial");
		GameRegistry.registerBlock(TutorialMetadata, ItemBlockTutorialMetadata.class, "TutorialMetadata", "ModTutoriel");
		
		//Items
		ItemTutorial = new ItemTutorial(12000).setUnlocalizedName("ItemTutorial").func_111206_d("modtutoriel:ItemTutorial");
		
		//Enregistrement des items - Item registry
		GameRegistry.registerItem(ItemTutorial, "ItemTutorial", "ModTutoriel");
		
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