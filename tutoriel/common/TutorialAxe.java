package tutoriel.common;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class TutorialAxe extends ItemAxe
{
	public TutorialAxe(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
		this.setHarvestLevel("axe", 3);
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