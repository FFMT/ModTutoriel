package tutoriel.common;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;

public class ItemCdTutorial extends ItemRecord
{
	protected ItemCdTutorial(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	
    @SideOnly(Side.CLIENT)
    public String getRecordTitle()
    {
        return "ModTutorial - " + this.recordName;
    }
}
