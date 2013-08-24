package tutoriel.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

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
		nbtTag.setByte("direction", direction);
	}

	public void setDirection(byte direct)
	{
		direction = direct;
		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, 2);
	}

	public byte getDirection()
	{
		return direction;
	}
}