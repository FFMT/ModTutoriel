package tutoriel.client;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tutoriel.common.TileEntitySculpture;

public class TileEntitySculptureSpecialRender extends TileEntitySpecialRenderer implements IInventoryRenderer
{
	private final ModelTutorial model = new ModelTutorial();
	public static final ResourceLocation textureLocation = new ResourceLocation("modtutoriel", "textures/blocks/modelTutoriel.png");
	public TileEntitySculptureSpecialRender()
	{
		this.func_147497_a(TileEntityRendererDispatcher.instance);
	}
	
	@Override
	public void renderInventory(double x, double y, double z)
	{
		this.renderTileEntitySculptureAt(null, x, y, z, 0.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
	{
		this.renderTileEntitySculptureAt((TileEntitySculpture)te, x, y, z, f);
	}
	
	public void renderTileEntitySculptureAt(TileEntitySculpture te, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		this.bindTexture(textureLocation);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(te != null)
		{
			GL11.glRotatef(90F * te.getDirection(), 0.0F, 1.0F, 0.0F);
		}
		this.model.render(0.0625F);
		GL11.glPopMatrix();
	}
}