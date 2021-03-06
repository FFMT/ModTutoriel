package tutoriel.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tutoriel.common.ContainerBigChest;
import tutoriel.common.TileEntityBigChest;

public class GuiBigChest extends GuiContainer
{
	public static ResourceLocation texture = new ResourceLocation("modtutoriel", "textures/gui/container/bigChest.png");
	private TileEntityBigChest bigChest;
	private IInventory playerInventory;
	public GuiBigChest(InventoryPlayer inventory, TileEntityBigChest tileEntity)
	{
		super(new ContainerBigChest(inventory, tileEntity));
		this.bigChest = tileEntity;
		this.playerInventory = inventory;
		this.ySize = 230;
	}
	
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(this.playerInventory.hasCustomInventoryName() ? this.playerInventory.getInventoryName() : I18n.format(this.playerInventory.getInventoryName()), 8, 129, 0);
        this.fontRendererObj.drawString(this.bigChest.hasCustomInventoryName() ? this.bigChest.getInventoryName() : I18n.format(this.bigChest.getInventoryName()), 8, 7, 0);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
}