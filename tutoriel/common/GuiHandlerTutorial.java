package tutoriel.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tutoriel.client.GuiBigChest;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerTutorial implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityBigChest)
		{
			return new ContainerBigChest(player.inventory, (TileEntityBigChest)te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityBigChest)
		{
			return new GuiBigChest(player.inventory, (TileEntityBigChest)te);
		}
		return null;
	}
}