package tutoriel.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityTutorial2 extends TileEntity
{
	public byte direction;

	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		direction = nbtTag.getByte("direction");
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		for(int i = 0; i < 5; i++)
		{
			nbtTag.setByte("direction", direction);
		}
	}

	public void setDirection(byte direct)
	{
		direction = direct;
	}

	public byte getDirection()
	{
		return direction;
	}
}
