package tutoriel.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSculpture extends ItemBlock
{
	public ItemBlockSculpture(int id)
	{
		super(id);
	}
	
	public int getMetadata(int metadata)
	{
		return metadata;
	}

	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata > BlockSculpture.subBlock.length || metadata < 0)
		{
			metadata = 0;
		}
		return "tile" + "." + BlockSculpture.subBlock[metadata];
	}
}