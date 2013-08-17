package tutoriel.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class TutorialHoe extends ItemHoe
{
	public TutorialHoe(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}
	
	public boolean getIsRepairable(ItemStack stack, ItemStack outputstack)
	{
		return true;
	}
}