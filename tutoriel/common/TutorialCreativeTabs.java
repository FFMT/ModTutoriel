package tutoriel.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TutorialCreativeTabs extends CreativeTabs
{
	public TutorialCreativeTabs(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(ModTutoriel.BlockTutorial);
	}
}