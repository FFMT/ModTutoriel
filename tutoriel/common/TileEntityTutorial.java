package tutoriel.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTutorial extends TileEntity
{
	public String visiteur[] = new String[]{"visiteur0", "visiteur1", "visiteur2", "visiteur3", "visiteur4"};

	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		for(int i = 0; i < 5; i++)
		{
			visiteur[i] = nbtTag.getString("visiteur" + i);
		}
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		for(int i = 0; i < 5; i++)
		{
			nbtTag.setString("visiteur" + i, visiteur[i]);
		}
	}

	public String getPlayerList()
	{
		return visiteur[0] + ", " + visiteur[1] + ", " + visiteur[2] + ", " + visiteur[3] + ", " + visiteur[4] + ", ";
	}

	public void addplayertolist(String playerName)
	{
		if(!visiteur[0].equals(playerName))
			;
		{
			for(int i = 3; i >= 0; i--)
			{
				visiteur[i + 1] = visiteur[i];
			}
			visiteur[0] = playerName;
		}
		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, 2);
	}
}
