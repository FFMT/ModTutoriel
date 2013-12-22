package tutoriel.client;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tutoriel.common.TileEntityCupboard;

public class TileEntityCupboardSpecialRender extends TileEntitySpecialRenderer implements IInventoryRenderer
{
	private final ModelCupboard model = new ModelCupboard();
	public static final ResourceLocation textureLocation = new ResourceLocation("modtutoriel", "textures/blocks/cupboard.png");

	public TileEntityCupboardSpecialRender()
	{
		this.setTileEntityRenderer(TileEntityRenderer.instance);
	}

	@Override
	public void renderInventory(double x, double y, double z)
	{
		this.renderTileEntityCupboardeAt(null, x, y, z, 0.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float tick)
	{
		this.renderTileEntityCupboardeAt((TileEntityCupboard)tileentity, x, y, z, tick);
	}

	public void renderTileEntityCupboardeAt(TileEntityCupboard te, double x, double y, double z, float tick)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		this.bindTexture(textureLocation);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(te != null)
		{
			GL11.glRotatef(90F * te.getDirection(), 0.0F, 1.0F, 0.0F);
			float angle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * tick;
			angle = 1.0F - angle;
			angle = 1.0F - angle * angle * angle;
			this.model.doorLeft.rotateAngleY = (angle * (float)Math.PI / 2.0F);
			this.model.doorRight.rotateAngleY = -(angle * (float)Math.PI / 2.0F);
		}
		GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
		this.model.render(0.0625F);
		GL11.glPopMatrix();
	}
}