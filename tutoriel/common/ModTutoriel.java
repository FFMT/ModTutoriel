package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import tutoriel.client.TextureEvent;
import tutoriel.proxy.TutoCommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ModTutoriel", name = "Mod Tutoriel", version = "1.1.0", acceptedMinecraftVersions = "[1.7.2,)")

public class ModTutoriel
{
	@SidedProxy(clientSide = "tutoriel.proxy.TutoClientProxy", serverSide = "tutoriel.proxy.TutoCommonProxy")
	public static TutoCommonProxy proxy;

	@Instance("ModTutoriel")
	public static ModTutoriel instance;

	// declaration des blocs - blocks statement
	public static Block BlockTutorial, TutorialMetadata, StairsTutorial, DoubleSlabTuto, SingleSlabTuto, BlockTutorialCake, BlockNewFenceTutorial, BlockNewWallTutorial, blockFluidTutorial, blockTable, blockSculpture;
	public static Item ItemTutorial, ItemWithMetadata, TutorialHelmet, TutorialChestPlate, TutorialLeggings, TutorialBoots, TutorialEgg, TutorialSword, TutorialPickaxe, TutorialAxe, TutorialShovel, TutorialHoe, ItemTutorialCake, ItemCdTutorial, bucketTutorial;
	public static Fluid fluidTutorial;

	static ArmorMaterial TutorialArmor = EnumHelper.addArmorMaterial("Tutorial", 20, new int[] {2, 8, 4, 2}, 15);
	static ToolMaterial TutorialMaterial = EnumHelper.addToolMaterial("Tutorial", 3, 761, 14.0F, 4, 5);

	// declaration des tables creatives
	public static CreativeTabs TutorialCreativeTabs = new TutorialCreativeTabs("TutorialCreativeTabs");

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		// Fluid
		fluidTutorial = new Fluid("tutorial").setDensity(4000).setViscosity(500).setTemperature(286).setLuminosity(10).setUnlocalizedName("tutorial");
		FluidRegistry.registerFluid(fluidTutorial);
		fluidTutorial = FluidRegistry.getFluid("tutorial");

		// Blocks
		BlockTutorial = new BlockTutorial().setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("BlockTutorial");
		StairsTutorial = new BlockStairsTutorial(BlockTutorial, 0).setBlockName("StairsTutorial");
		TutorialMetadata = new BlockTutorialMetadata().setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("TutorialMetadata");
		DoubleSlabTuto = new BlockSlabTutorial(true, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("TutorialSlab");
		SingleSlabTuto = new BlockSlabTutorial(false, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("TutorialSlab");
		BlockTutorialCake = new BlockCakeTutorial().setHardness(0.5F).setStepSound(Block.soundTypeCloth).setBlockName("TutorialGateau");
		BlockNewFenceTutorial = new BlockFence("snow", Material.snow).setBlockName("TutorialFence").setCreativeTab(ModTutoriel.TutorialCreativeTabs);
		BlockNewWallTutorial = new BlockTutorialWall(Blocks.snow).setBlockName("TutorialWall").setCreativeTab(ModTutoriel.TutorialCreativeTabs);
		
		if(fluidTutorial.getBlock() == null)
		{
			blockFluidTutorial = new BlockFluidTutorial(fluidTutorial, Material.water).setBlockName("fluidTutorial");
			GameRegistry.registerBlock(blockFluidTutorial, "fluidTutorial");
			fluidTutorial.setBlock(blockFluidTutorial);
		}
		else
		{
			blockFluidTutorial = fluidTutorial.getBlock();
		}
		
		blockTable = new BlockTable().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("table");
		blockSculpture = new BlockSculpture().setHardness(5.0F).setResistance(25.0F).setStepSound(Block.soundTypeWood).setBlockName("sculpture");
		
		// Enregistrement des blocs - Blocks registry
		GameRegistry.registerBlock(BlockTutorial, "BlockTutorial");
		GameRegistry.registerBlock(TutorialMetadata, ItemBlockTutorialMetadata.class, "TutorialMetadata", "ModTutoriel");
		GameRegistry.registerBlock(StairsTutorial, "StairTutorial");
		GameRegistry.registerBlock(DoubleSlabTuto, ItemBlockTutorialSlab.class, "DoubleSlabTuto");
		GameRegistry.registerBlock(SingleSlabTuto, ItemBlockTutorialSlab.class, "SingleSlabTuto");
		GameRegistry.registerBlock(BlockTutorialCake, "TutorialGateau");
		GameRegistry.registerBlock(BlockNewFenceTutorial, "TutorialFence");
		GameRegistry.registerBlock(BlockNewWallTutorial, "TutorialWall");
		GameRegistry.registerBlock(blockTable, "table");
		GameRegistry.registerBlock(blockSculpture, ItemBlockSculpture.class, "sculpture");

		// Items
		ItemTutorial = new ItemTutorial().setUnlocalizedName("ItemTutorial").setTextureName("modtutoriel:ItemTutorial").setCreativeTab(ModTutoriel.TutorialCreativeTabs);;
		ItemWithMetadata = new ItemWithMetadata().setUnlocalizedName("ItemWithMetadata");
		TutorialHelmet = new ItemTutorialArmor(TutorialArmor, 0).setUnlocalizedName("TutorialHelmet").setTextureName("modtutoriel:HelmetTutorial");
		TutorialChestPlate = new ItemTutorialArmor(TutorialArmor, 1).setUnlocalizedName("TutorialChestPlate").setTextureName("modtutoriel:ChestPlateTutorial");
		TutorialLeggings = new ItemTutorialArmor(TutorialArmor, 2).setUnlocalizedName("TutorialLeggings").setTextureName("modtutoriel:LeggingsTutorial");
		TutorialBoots = new ItemTutorialArmor(TutorialArmor, 3).setUnlocalizedName("TutorialBoots").setTextureName("modtutoriel:BootsTutorial");
		TutorialEgg = new ItemTutorialEgg(5, 4.5F, false).setUnlocalizedName("TutorialEgg").setTextureName("modtutoriel:ChocolateEgg");
		TutorialSword = new TutorialSword( TutorialMaterial).setUnlocalizedName("TutorialSword").setTextureName("modtutoriel:TutorialSword");
		TutorialPickaxe = new TutorialPickaxe(TutorialMaterial).setUnlocalizedName("TutorialPickaxe").setTextureName("modtutoriel:TutorialPickaxe");
		TutorialAxe = new TutorialAxe(TutorialMaterial).setUnlocalizedName("TutorialAxe").setTextureName("modtutoriel:TutorialAxe");
		TutorialShovel = new TutorialShovel(TutorialMaterial).setUnlocalizedName("TutorialShovel").setTextureName("modtutoriel:TutorialShovel");
		TutorialHoe = new TutorialHoe(TutorialMaterial).setUnlocalizedName("TutorialHoe").setTextureName("modtutoriel:TutorialHoe");
		ItemTutorialCake = new ItemTutorialCake().setUnlocalizedName("TurorialGateauItem").setTextureName("modtutoriel:TutorialCake");
		ItemCdTutorial = new ItemCdTutorial("cd").setUnlocalizedName("record").setCreativeTab(ModTutoriel.TutorialCreativeTabs);
		bucketTutorial = new ItemBucketTutorial(blockFluidTutorial).setUnlocalizedName("bucketTutorial").setTextureName("modtutoriel:bucketTutorial");

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
		GameRegistry.registerItem(ItemCdTutorial, "item_cd_tutoriel");
		GameRegistry.registerItem(bucketTutorial, "BucketTutorial", "ModTutoriel");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("tutorial", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketTutorial), FluidContainerRegistry.EMPTY_BUCKET);

		EntityRegistry.registerGlobalEntityID(MobTutorialHealthBar.class, "MobTutorielHealthBar", EntityRegistry.findGlobalUniqueEntityId(), 0, 0);
		EntityRegistry.registerModEntity(MobTutorialHealthBar.class, "MobTutorialHealthBar", 1254, this, 100, 1, true);

		// Achievements
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		// Event Bus
		MinecraftForge.EVENT_BUS.register(new EntityEvent());
		MinecraftForge.EVENT_BUS.register(new TextureEvent());
		MinecraftForge.EVENT_BUS.register(new BucketEvent());

		// Registry
		GameRegistry.registerTileEntity(TileEntityTutorial.class, "TileEntityTutorial");
		GameRegistry.registerTileEntity(TileEntityTutorial2.class, "TileEntityTutorial2");
		GameRegistry.registerTileEntity(TileEntityBigChest.class, "BigChest");
		GameRegistry.registerTileEntity(TileEntitySculpture.class, "Sculpture");
		GameRegistry.registerTileEntity(TileEntityCupboard.class, "Cupboard");
		GameRegistry.registerTileEntity(TileEntityMachine.class, "StrangeMachine");

		GameRegistry.registerWorldGenerator(new WorldGeneratorTutoriel(), 1);

		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandlerTutorial());

		// Mobs

		// Render
		proxy.registerRender();
		proxy.registerTileEntityRender();
		// NetWork

		// Recette - Recipe
		GameRegistry.addRecipe(new ItemStack(BlockTutorial), new Object[] {"XXX", "ZYZ", "XXX", 'X', Blocks.lapis_block, 'Y', new ItemStack(Items.dye, 1, 15), 'Z', new ItemStack(Items.dye, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(TutorialMetadata, 4, 2), new Object[] {"XXX", "XXX", "   ", 'X', Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(TutorialMetadata, 4, 1), new Object[] {"XXX", "XXX", 'X', Blocks.dirt});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemTutorial, 2), new Object[] {new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(TutorialPickaxe), "XXX", " S ", " S ", 'X', ItemTutorial, 'S', Items.stick);
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		// Integration avec les autres mods - integration with others mods
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandTutoriel());
	}
}