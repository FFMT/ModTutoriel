package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import tutoriel.client.EventSoundTutorial;
import tutoriel.client.TextureEvent;
import tutoriel.proxy.TutoCommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ModTutoriel", name = "Mod Tutoriel", version = "1.0.0", acceptedMinecraftVersions = "[1.6.2,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModTutoriel
{
	@SidedProxy(clientSide = "tutoriel.proxy.TutoClientProxy", serverSide = "tutoriel.proxy.TutoCommonProxy")
	public static TutoCommonProxy proxy;

	@Instance("ModTutoriel")
	public static ModTutoriel instance;

	// declaration des blocs - blocks statement
	public static Block BlockTutorial, TutorialMetadata, StairsTutorial, DoubleSlabTuto, SingleSlabTuto, BlockTutorialCake, BlockNewFenceTutorial, BlockNewWallTutorial, blockFluidTutorial;
	public static Item ItemTutorial, ItemWithMetadata, TutorialHelmet, TutorialChestPlate, TutorialLeggings, TutorialBoots, TutorialEgg, TutorialSword, TutorialPickaxe, TutorialAxe, TutorialShovel, TutorialHoe, ItemTutorialCake, ItemCdTutorial, bucketTutorial;
	public static Fluid fluidTutorial;

	public static int BlockTutorialID, TutorialMetadataID, StairsTutorialID, DoubleSlabTutoID, SingleSlabTutoID, fluidTutorialID, ItemTutorialID, ItemWithMetadataID, TutorialHelmetID, TutorialChestPlateID, TutorialLeggingsID, TutorialBootsID, TutorialEggID, TutorialSwordID, TutorialPickaxeID, TutorialAxeID, TutorialShovelID, TutorialHoeID, BlockTutorialCakeID, ItemTutorialCakeID,
			BlockNewFenceTutorialID, BlockNewWallTutorialID, ItemCdTutorialID, bucketTutorialID;

	static EnumArmorMaterial TutorialArmor = EnumHelper.addArmorMaterial("Tutorial", 20, new int[] {2, 8, 4, 2}, 15);
	static EnumToolMaterial TutorialMaterial = EnumHelper.addToolMaterial("Tutorial", 3, 761, 14.0F, 4, 5);

	// declaration des tables creatives
	public static CreativeTabs TutorialCreativeTabs = new TutorialCreativeTabs("TutorialCreativeTabs");

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		// Configuration
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();

			BlockTutorialID = cfg.getBlock("Block Tutoriel", 2000, "this is a comment").getInt();
			TutorialMetadataID = cfg.getBlock("Block Tutoriel Metadata", 2001).getInt();
			StairsTutorialID = cfg.getBlock("Stair Tutoriel", 2002).getInt();
			DoubleSlabTutoID = cfg.getBlock("Double Slab Tutoriel", 2003).getInt();
			SingleSlabTutoID = cfg.getBlock("Single Slab Tutoriel", 2004).getInt();
			BlockTutorialCakeID = cfg.getBlock("Gateau Tutoriel", 2005).getInt();
			BlockNewFenceTutorialID = cfg.getBlock("Fence", 2006).getInt();
			BlockNewWallTutorialID = cfg.getBlock("Wall", 2007).getInt();
			fluidTutorialID = cfg.getBlock("Fluid", 2008).getInt();

			ItemTutorialID = cfg.getItem("Item Tutoriel", 12000).getInt();
			ItemWithMetadataID = cfg.getItem("Item With Metadata", 12001).getInt();
			TutorialHelmetID = cfg.getItem("Tutorial Helmet", 12002).getInt();
			TutorialChestPlateID = cfg.getItem("Tutorial Chest Plate", 12003).getInt();
			TutorialLeggingsID = cfg.getItem("Tutorial Leggings", 12004).getInt();
			TutorialBootsID = cfg.getItem("Tutorial Boots", 12005).getInt();
			TutorialEggID = cfg.getItem("Tutorial Egg", 12006).getInt();
			TutorialSwordID = cfg.getItem("Tutorial Sword", 12007).getInt();
			TutorialPickaxeID = cfg.getItem("Tutorial Pickaxe", 12008).getInt();
			TutorialAxeID = cfg.getItem("Tutorial Axe", 12009).getInt();
			TutorialShovelID = cfg.getItem("Tutorial Shovel", 12010).getInt();
			TutorialHoeID = cfg.getItem("Tutorial Hoe", 12011).getInt();
			ItemTutorialCakeID = cfg.getItem("Gateau Tutorial Item", 12012).getInt();
			ItemCdTutorialID = cfg.getItem("Cd tutorial", 12013).getInt();
			bucketTutorialID = cfg.getItem("Bucket Tutorial", 12014).getInt();
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

		// Son - sound
		if(event.getSide().isClient())
		{
			MinecraftForge.EVENT_BUS.register(new EventSoundTutorial());
		}

		// Fluid
		fluidTutorial = new Fluid("tutorial").setDensity(4000).setViscosity(500).setTemperature(286).setLuminosity(10).setUnlocalizedName("tutorial");
		FluidRegistry.registerFluid(fluidTutorial);
		fluidTutorial = FluidRegistry.getFluid("tutorial");

		// Blocks
		BlockTutorial = new BlockTutorial(BlockTutorialID).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("BlockTutorial");
		StairsTutorial = new BlockStairsTutorial(StairsTutorialID, BlockTutorial, 0).setUnlocalizedName("StairsTutorial");
		TutorialMetadata = new BlockTutorialMetadata(TutorialMetadataID).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("TutorialMetadata");
		DoubleSlabTuto = new BlockSlabTutorial(DoubleSlabTutoID, true).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("TutorialSlab");
		SingleSlabTuto = new BlockSlabTutorial(SingleSlabTutoID, false).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("TutorialSlab");
		BlockTutorialCake = new BlockCakeTutorial(BlockTutorialCakeID).setHardness(0.5F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("TutorialGateau");
		BlockNewFenceTutorial = new BlockFence(BlockNewFenceTutorialID, "snow", Material.snow).setUnlocalizedName("TutorialFence").setCreativeTab(ModTutoriel.TutorialCreativeTabs);
		BlockNewWallTutorial = new BlockTutorialWall(BlockNewWallTutorialID, Block.snow).setUnlocalizedName("TutorialWall").setCreativeTab(ModTutoriel.TutorialCreativeTabs);

		if(fluidTutorial.getBlockID() == -1)
		{
			blockFluidTutorial = new BlockFluidTutorial(fluidTutorialID, fluidTutorial, Material.water).setUnlocalizedName("fluidTutorial");
			GameRegistry.registerBlock(blockFluidTutorial, "fluidTutorial");
			fluidTutorial.setBlockID(blockFluidTutorial);
		}
		else
		{
			blockFluidTutorial = Block.blocksList[fluidTutorial.getBlockID()];
		}

		// Enregistrement des blocs - Blocks registry
		GameRegistry.registerBlock(BlockTutorial, "BlockTutorial");
		GameRegistry.registerBlock(TutorialMetadata, ItemBlockTutorialMetadata.class, "TutorialMetadata", "ModTutoriel");
		GameRegistry.registerBlock(StairsTutorial, "StairTutorial");
		GameRegistry.registerBlock(DoubleSlabTuto, ItemBlockTutorialSlab.class, "DoubleSlabTuto");
		GameRegistry.registerBlock(SingleSlabTuto, ItemBlockTutorialSlab.class, "SingleSlabTuto");
		GameRegistry.registerBlock(BlockTutorialCake, "TutorialGateau");
		GameRegistry.registerBlock(BlockNewFenceTutorial, "TutorialFence");
		GameRegistry.registerBlock(BlockNewWallTutorial, "TutorialWall");

		// Items
		ItemTutorial = new ItemTutorial(ItemTutorialID).setUnlocalizedName("ItemTutorial").setTextureName("modtutoriel:ItemTutorial");
		ItemWithMetadata = new ItemWithMetadata(ItemWithMetadataID).setUnlocalizedName("ItemWithMetadata");
		TutorialHelmet = new ItemTutorialArmor(TutorialHelmetID, TutorialArmor, 0, 0).setUnlocalizedName("TutorialHelmet").setTextureName("modtutoriel:HelmetTutorial");
		TutorialChestPlate = new ItemTutorialArmor(TutorialChestPlateID, TutorialArmor, 0, 1).setUnlocalizedName("TutorialChestPlate").setTextureName("modtutoriel:ChestPlateTutorial");
		TutorialLeggings = new ItemTutorialArmor(TutorialLeggingsID, TutorialArmor, 0, 2).setUnlocalizedName("TutorialLeggings").setTextureName("modtutoriel:LeggingsTutorial");
		TutorialBoots = new ItemTutorialArmor(TutorialBootsID, TutorialArmor, 0, 3).setUnlocalizedName("TutorialBoots").setTextureName("modtutoriel:BootsTutorial");
		TutorialEgg = new ItemTutorialEgg(TutorialEggID, 5, 4.5F, false).setUnlocalizedName("TutorialEgg").setTextureName("modtutoriel:ChocolateEgg");
		TutorialSword = new TutorialSword(TutorialSwordID, TutorialMaterial).setUnlocalizedName("TutorialSword").setTextureName("modtutoriel:TutorialSword");
		TutorialPickaxe = new TutorialPickaxe(TutorialPickaxeID, TutorialMaterial).setUnlocalizedName("TutorialPickaxe").setTextureName("modtutoriel:TutorialPickaxe");
		TutorialAxe = new TutorialAxe(TutorialAxeID, TutorialMaterial).setUnlocalizedName("TutorialAxe").setTextureName("modtutoriel:TutorialAxe");
		TutorialShovel = new TutorialShovel(TutorialShovelID, TutorialMaterial).setUnlocalizedName("TutorialShovel").setTextureName("modtutoriel:TutorialShovel");
		TutorialHoe = new TutorialHoe(TutorialHoeID, TutorialMaterial).setUnlocalizedName("TutorialHoe").setTextureName("modtutoriel:TutorialHoe");
		ItemTutorialCake = new ItemTutorialCake(ItemTutorialCakeID).setUnlocalizedName("TurorialGateauItem").setTextureName("modtutoriel:TutorialCake");
		ItemCdTutorial = new ItemCdTutorial(ItemCdTutorialID, "modtutoriel:cd").setUnlocalizedName("cdTutorial").setCreativeTab(ModTutoriel.TutorialCreativeTabs);
		bucketTutorial = new ItemBucketTutorial(bucketTutorialID, blockFluidTutorial.blockID).setUnlocalizedName("bucketTutorial").setTextureName("modtutoriel:bucketTutorial");
		
		// Enregistrement des items - Item registry
		GameRegistry.registerItem(ItemTutorial, "ItemTutorial", "ModTutoriel");
		GameRegistry.registerItem(ItemWithMetadata, "ItemWithMetadata", "ModTutoriel");
		GameRegistry.registerItem(TutorialHelmet, "TutorialHelmet", "ModTutoriel");
		GameRegistry.registerItem(TutorialChestPlate, "TutorialChestPlate", "ModTutoriel");
		GameRegistry.registerItem(TutorialLeggings, "TutorialLeggings", "ModTutoriel");
		GameRegistry.registerItem(TutorialBoots, "TutorialBoots", "ModTutoriel");
		GameRegistry.registerItem(TutorialEgg, "TutorialEgg", "ModTutoriel");
		GameRegistry.registerItem(TutorialSword, "TutorialSword", "ModTutoriel");
		GameRegistry.registerItem(TutorialPickaxe, "TutorialPickaxe", "ModTutoriel");
		GameRegistry.registerItem(TutorialAxe, "TutorialAxe", "ModTutoriel");
		GameRegistry.registerItem(TutorialShovel, "TutorialShovel", "ModTutoriel");
		GameRegistry.registerItem(TutorialHoe, "TutorialHoe", "ModTutoriel");
		GameRegistry.registerItem(ItemTutorialCake, "TutorialGateauItem", "ModTutoriel");
		GameRegistry.registerItem(bucketTutorial, "BucketTutorial", "ModTutoriel");
		
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("tutorial", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketTutorial), FluidContainerRegistry.EMPTY_BUCKET);

		EntityRegistry.registerGlobalEntityID(MobTutorialHealthBar.class, "MobTutorielHealthBar", EntityRegistry.findGlobalUniqueEntityId(), 0, 0);
		EntityRegistry.registerModEntity(MobTutorialHealthBar.class, "MobTutorialHealthBar", 1254, this, 100, 1, true);
		EntityRegistry.addSpawn(MobTutorialHealthBar.class, 0, 1, 2, EnumCreatureType.creature, BiomeGenBase.forest, BiomeGenBase.plains, BiomeGenBase.extremeHills);

		// Achievements
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		// Event Bus
		MinecraftForge.EVENT_BUS.register(new LivingEvent());
		MinecraftForge.EVENT_BUS.register(new TextureEvent());
		MinecraftForge.EVENT_BUS.register(new BucketEvent());

		// Registry
		GameRegistry.registerTileEntity(TileEntityTutorial.class, "TileEntityTutorial");
		GameRegistry.registerTileEntity(TileEntityTutorial2.class, "TileEntityTutorial2");
		GameRegistry.registerTileEntity(TileEntityBigChest.class, "BigChest");

		GameRegistry.registerWorldGenerator(new WorldGeneratorTutoriel());

		NetworkRegistry.instance().registerGuiHandler(this.instance, new GuiHandlerTutorial());

		// Tools
		MinecraftForge.setToolClass(TutorialPickaxe, "pickaxe", 3);
		MinecraftForge.setToolClass(TutorialAxe, "axe", 3);
		MinecraftForge.setToolClass(TutorialShovel, "shovel", 3);

		// Mobs

		// Render
		proxy.registerRender();
		// NetWork

		// Recette - Recipe
		GameRegistry.addRecipe(new ItemStack(BlockTutorial), new Object[] {"XXX", "ZYZ", "XXX", 'X', Block.blockLapis, 'Y', new ItemStack(Item.dyePowder, 1, 15), 'Z', new ItemStack(Item.dyePowder, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(TutorialMetadata, 4, 2), new Object[] {"XXX", "XXX", "   ", 'X', Block.stone});
		GameRegistry.addRecipe(new ItemStack(TutorialMetadata, 4, 1), new Object[] {"XXX", "XXX", 'X', Block.dirt});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemTutorial, 2), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.dyePowder, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(TutorialPickaxe), "XXX", " S ", " S ", 'X', ItemTutorial, 'S', Item.stick);
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		// Integration avec les autres mods - integration with others mods
	}
}
