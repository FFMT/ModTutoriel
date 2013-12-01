package tutoriel.proxy;

import tutoriel.client.RenderMobTutorialHealthBar;
import tutoriel.client.RenderTable;
import tutoriel.client.TileEntitySculptureSpecialRender;
import tutoriel.common.MobTutorialHealthBar;
import tutoriel.common.ModTutoriel;
import tutoriel.common.TileEntitySculpture;
import tutoriel.proxy.TESRInventoryRenderer.TESRIndex;
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
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(ModTutoriel.blockSculpture, 0), new TileEntitySculptureSpecialRender());
	}
}