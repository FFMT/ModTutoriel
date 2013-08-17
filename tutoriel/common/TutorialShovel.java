package tutoriel.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class TutorialShovel extends ItemSpade
{
	public TutorialShovel(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}
	
	public boolean getIsRepairable(ItemStack stack, ItemStack outputstack)
	{
		return true;
	}
}