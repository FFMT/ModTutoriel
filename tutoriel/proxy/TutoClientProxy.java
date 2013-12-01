package tutoriel.proxy;

import tutoriel.client.RenderMobTutorialHealthBar;
import tutoriel.client.RenderTable;
import tutoriel.common.MobTutorialHealthBar;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TutoClientProxy extends TutoCommonProxy
{
	public static int renderTableId;

	public void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(MobTutorialHealthBar.class, new RenderMobTutorialHealthBar());
		renderTableId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(renderTableId, new RenderTable());
	}
}