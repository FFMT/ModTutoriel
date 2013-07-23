package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import tutoriel.client.EventSoundTutorial;
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
	public static Item ItemTutorial, ItemWithMetadata, TutorialHelmet, TutorialChestPlate, TutorialLeggings, TutorialBoots;
	
	public static int BlockTutorialID, TutorialMetadataID, ItemTutorialID, ItemWithMetadataID, TutorialHelmetID,
	TutorialChestPlateID, TutorialLeggingsID, TutorialBootsID;
	
	static EnumArmorMaterial TutorialArmor = EnumHelper.addArmorMaterial("Tutorial", 20, new int[]{2, 8, 4, 2}, 15);
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		//Configuration
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			BlockTutorialID = cfg.getBlock("Block Tutoriel", 2000, "this is a comment").getInt();
			TutorialMetadataID = cfg.getBlock("Block Tutoriel Metadata", 2001).getInt();
			
			ItemTutorialID = cfg.getItem("Item Tutoriel", 12000).getInt();
			ItemWithMetadataID = cfg.getItem("Item With Metadata", 12001).getInt();
			TutorialHelmetID = cfg.getItem("Tutorial Helmet", 12002).getInt();
			TutorialChestPlateID = cfg.getItem("Tutorial Chest Plate", 12003).getInt();
			TutorialLeggingsID = cfg.getItem("Tutorial Leggings", 12004).getInt();
			TutorialBootsID = cfg.getItem("Tutorial Boots", 12005).getInt();
		}
		catch(Exception ex)
		{
			event.getModLog().severe("Failed to load configuration");
		}
		finally
		{
			if(cfg.hasChanged())
			{
				cfg.save();
			}
		}
		
		//Son - sound
		if(event.getSide().isClient())
		{
			MinecraftForge.EVENT_BUS.register(new EventSoundTutorial());
		}
		
		//Blocks
		BlockTutorial = new BlockTutorial(BlockTutorialID).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("BlockTutorial").func_111022_d("modtutoriel:BlockTutorial");
		TutorialMetadata = new BlockTutorialMetadata(TutorialMetadataID).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("TutorialMetadata");

		//Enregistrement des blocs - Blocks registry
		GameRegistry.registerBlock(BlockTutorial, "BlockTutorial");
		GameRegistry.registerBlock(TutorialMetadata, ItemBlockTutorialMetadata.class, "TutorialMetadata", "ModTutoriel");
		
		//Items
		ItemTutorial = new ItemTutorial(ItemTutorialID).setUnlocalizedName("ItemTutorial").func_111206_d("modtutoriel:ItemTutorial");
		ItemWithMetadata = new ItemWithMetadata(ItemWithMetadataID).setUnlocalizedName("ItemWithMetadata");
		TutorialHelmet = new ItemTutorialArmor(TutorialHelmetID, TutorialArmor, 0, 0).setUnlocalizedName("TutorialHelmet").func_111206_d("modtutoriel:HelmetTutorial");
		TutorialChestPlate = new ItemTutorialArmor(TutorialChestPlateID, TutorialArmor, 0, 1).setUnlocalizedName("TutorialChestPlate").func_111206_d("modtutoriel:ChestPlateTutorial");
		TutorialLeggings = new ItemTutorialArmor(TutorialLeggingsID, TutorialArmor, 0, 2).setUnlocalizedName("TutorialLeggings").func_111206_d("modtutoriel:LeggingsTutorial");
		TutorialBoots = new ItemTutorialArmor(TutorialBootsID, TutorialArmor, 0, 3).setUnlocalizedName("TutorialBoots").func_111206_d("modtutoriel:BootsTutorial");
		
		//Enregistrement des items - Item registry
		GameRegistry.registerItem(ItemTutorial, "ItemTutorial", "ModTutoriel");
		GameRegistry.registerItem(ItemWithMetadata, "ItemWithMetadata", "ModTutoriel");
		GameRegistry.registerItem(TutorialHelmet, "TutorialHelmet", "ModTutoriel");
		GameRegistry.registerItem(TutorialChestPlate, "TutorialChestPlate", "ModTutoriel");
		GameRegistry.registerItem(TutorialLeggings, "TutorialLeggings", "ModTutoriel");
		GameRegistry.registerItem(TutorialBoots, "TutorialBoots", "ModTutoriel");
		
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
		GameRegistry.addRecipe(new ItemStack(BlockTutorial), new Object[]{"XXX", "ZYZ", "XXX", 'X', Block.blockLapis, 'Y', new ItemStack(Item.dyePowder, 1, 15), 'Z', new ItemStack(Item.dyePowder, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(TutorialMetadata, 4, 2), new Object[]{"XXX", "XXX", "   ", 'X', Block.stone});
		GameRegistry.addRecipe(new ItemStack(TutorialMetadata, 4, 1), new Object[]{"XXX", "XXX", 'X', Block.dirt});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemTutorial, 2), new Object[]{ new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.dyePowder, 1, 6)});
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		//Integration avec les autres mods - integration with others mods
	}
}