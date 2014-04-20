package tutoriel.common;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class TutorialPickaxe extends ItemPickaxe
{
	public TutorialPickaxe(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
		this.setHarvestLevel("pickaxe", 3);
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