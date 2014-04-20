package tutoriel.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TutorialCreativeTabs extends CreativeTabs
{
	public TutorialCreativeTabs(String label)
	{
		super(label);
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(ModTutoriel.BlockTutorial);
	}

	@Override
	public int func_151243_f()
	{
		return 0;
	}
}