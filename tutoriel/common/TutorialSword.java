package tutoriel.common;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class TutorialSword extends ItemSword
{
	public TutorialSword(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
	}

	public boolean getIsRepairable(ItemStack input, ItemStack repair)
	{
		if(repair.getItem() == ModTutoriel.ItemTutorial)
		{
			return true;
		}
		return false;
	}
}