package tutoriel.proxy;

import tutoriel.client.RenderMobTutorialHealthBar;
import tutoriel.client.RenderTable;
import tutoriel.client.TESRInventoryRenderer;
import tutoriel.client.TESRInventoryRenderer.TESRIndex;
import tutoriel.client.TileEntityCupboardSpecialRender;
import tutoriel.client.TileEntityMachineSpecialRender;
import tutoriel.client.TileEntitySculptureSpecialRender;
import tutoriel.common.MobTutorialHealthBar;
import tutoriel.common.ModTutoriel;
import tutoriel.common.TileEntityCupboard;
import tutoriel.common.TileEntityMachine;
import tutoriel.common.TileEntitySculpture;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TutoClientProxy extends TutoCommonProxy
{
	public static int renderTableId;
	public static int renderInventoryTESRId;

	public void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(MobTutorialHealthBar.class, new RenderMobTutorialHealthBar());
		
		renderTableId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(renderTableId, new RenderTable());
		renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
	}
	
	@Override
	public void registerTileEntityRender()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySculpture.class, new TileEntitySculptureSpecialRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCupboard.class, new TileEntityCupboardSpecialRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachine.class, new TileEntityMachineSpecialRender());
		
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(ModTutoriel.blockSculpture, 0), new TileEntitySculptureSpecialRender());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(ModTutoriel.blockSculpture, 1), new TileEntityCupboardSpecialRender());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(ModTutoriel.blockSculpture, 2), new TileEntityMachineSpecialRender());
	}
}