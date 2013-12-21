package tutoriel.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tutoriel.common.ContainerCupboard;
import tutoriel.common.TileEntityCupboard;

public class GuiCupboard extends GuiContainer
{
	public static ResourceLocation texture = new ResourceLocation("modtutoriel", "textures/gui/container/bigChest.png");
	private TileEntityCupboard cupboard;
	private IInventory playerInventory;
	
	public GuiCupboard(InventoryPlayer playerInv, TileEntityCupboard tileCupboard)
	{
		super(new ContainerCupboard(playerInv, tileCupboard));
		this.cupboard = tileCupboard;
		this.playerInventory = playerInv;
		this.ySize = 230;
	}
	
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(this.playerInventory.isInvNameLocalized() ? this.playerInventory.getInvName() : I18n.getString(this.playerInventory.getInvName()), 8, 129, 0);
        this.fontRenderer.drawString(this.cupboard.isInvNameLocalized() ? this.cupboard.getInvName() : I18n.getString(this.cupboard.getInvName()), 8, 7, 0);
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