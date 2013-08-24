package tutoriel.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class TutorialAxe extends ItemAxe
{
	public TutorialAxe(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}

	public boolean getIsRepairable(ItemStack stack, ItemStack outputstack)
	{
		return true;
	}
}