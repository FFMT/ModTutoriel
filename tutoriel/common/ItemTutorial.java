package tutoriel.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTutorial extends Item
{
	public ItemTutorial(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
}
