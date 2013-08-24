package tutoriel.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class TutorialPickaxe extends ItemPickaxe
{
	public TutorialPickaxe(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}

	public boolean getIsRepairable(ItemStack stack, ItemStack outputstack)
	{
		return true;
	}
}