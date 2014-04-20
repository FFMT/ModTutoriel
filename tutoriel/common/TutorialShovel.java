package tutoriel.common;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class TutorialShovel extends ItemSpade
{
	public TutorialShovel(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
		this.setHarvestLevel("shovel", 3);
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