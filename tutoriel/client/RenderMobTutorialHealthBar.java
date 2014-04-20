package tutoriel.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import tutoriel.common.MobTutorialHealthBar;

public class RenderMobTutorialHealthBar extends RenderLiving
{
	protected static final ResourceLocation texture = new ResourceLocation("modtutoriel:textures/entity/boss.png");
	
	public RenderMobTutorialHealthBar()
	{
		super(new ModelBiped(), 0.5F);
	}

	protected ResourceLocation getTextures(MobTutorialHealthBar anizob)
	{
		return texture;
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getTextures((MobTutorialHealthBar)par1Entity);
	}
	
	public void renderHealtBar(MobTutorialHealthBar mob, double x, double y, double z, float par8, float par9)
	{
		BossStatus.setBossStatus(mob, true);
		super.doRender(mob, x, y, z, par8, par9);
	}

	public void doRender(Entity entity, double x, double y, double z, float par8, float par9)
	{
		this.renderHealtBar((MobTutorialHealthBar)entity, x, y, z, par8, par9);
	}
}
