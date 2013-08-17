package tutoriel.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class TutorialSword extends ItemSword
{
	public TutorialSword(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}

	public boolean getIsRepairable(ItemStack stack, ItemStack outputstack)
	{
		return true;
	}
}