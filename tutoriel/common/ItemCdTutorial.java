package tutoriel.common;

import net.minecraft.item.ItemRecord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
