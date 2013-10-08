package tutoriel.proxy;

import tutoriel.client.RenderMobTutorialHealthBar;
import tutoriel.common.MobTutorialHealthBar;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TutoClientProxy extends TutoCommonProxy
{
	public void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(MobTutorialHealthBar.class, new RenderMobTutorialHealthBar());
	}

}
