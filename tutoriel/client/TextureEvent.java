package tutoriel.client;

import net.minecraftforge.client.event.TextureStitchEvent;
import tutoriel.common.ModTutoriel;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TextureEvent
{
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPostTextureStitch(TextureStitchEvent.Post event)
	{
		if(event.map.getTextureType() == 0)
		{
			ModTutoriel.fluidTutorial.setIcons(ModTutoriel.blockFluidTutorial.getBlockTextureFromSide(1), ModTutoriel.blockFluidTutorial.getBlockTextureFromSide(2));
		}
	}
}