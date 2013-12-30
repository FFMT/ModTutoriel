package tutoriel.common;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMachine extends TileEntityDirectional
{
	public float leverAngle, motorAngle, prevMotorAngle;

	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		this.leverAngle = nbtTag.getFloat("leverAngle");
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		nbtTag.setFloat("leverAngle", this.leverAngle);
	}

	public void eraseLeverAngle()
	{
		if(!this.worldObj.isRemote && this.leverAngle < 1F)
		{
			leverAngle += 0.05F;
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, zCoord);
		}
	}

	public void deraseLeverAngle()
	{
		if(!this.worldObj.isRemote && this.leverAngle > -1F)
		{
			leverAngle -= 0.05F;
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, zCoord);
		}
	}

	public void updateEntity()
	{
		this.prevMotorAngle = this.motorAngle;
		this.motorAngle += this.leverAngle / 10;
		if(leverAngle >= 0)
		{
			if(motorAngle > (prevMotorAngle + (motorAngle - prevMotorAngle)) * (float)Math.PI * 2.0F)
			{
				motorAngle = 0.0F;
			}
		}
		else
		{
			if(motorAngle > (prevMotorAngle + (motorAngle - prevMotorAngle)) * (float)Math.PI * 2.0F)
			{
				motorAngle = 1.0F;
			}
		}
	}
}