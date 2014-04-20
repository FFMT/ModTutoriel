package tutoriel.client;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tutoriel.common.TileEntityMachine;

public class TileEntityMachineSpecialRender extends TileEntitySpecialRenderer implements IInventoryRenderer
{
	private final ModelMachine model = new ModelMachine();
	public static final ResourceLocation textureLocation = new ResourceLocation("modtutoriel", "textures/blocks/machine.png");
	
	public TileEntityMachineSpecialRender()
	{
		this.func_147497_a(TileEntityRendererDispatcher.instance);
	}
	
	@Override
	public void renderInventory(double x, double y, double z)
	{
		this.renderTileEntityMachineAt(null, x, y, z, 0.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float tick)
	{
		this.renderTileEntityMachineAt((TileEntityMachine)tileentity, x, y, z, tick);
	}
	
	public void renderTileEntityMachineAt(TileEntityMachine te, double x, double y, double z, float tick)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		this.bindTexture(textureLocation);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(te != null)
		{
			GL11.glRotatef(90F * te.getDirection(), 0.0F, 1.0F, 0.0F);
			float angle = te.prevMotorAngle + (te.motorAngle - te.prevMotorAngle);
			this.model.axe.rotateAngleX = (angle * (float)Math.PI * 2.0F);
			this.model.propeller.rotateAngleX = (angle * (float)Math.PI * 2.0F);
			this.model.lever.rotateAngleX = te.leverAngle;
		}
		GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
		this.model.render(0.0625F);
		GL11.glPopMatrix();
	}
}