package tutoriel.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTutorialMetadata extends ItemBlock
{
	public ItemBlockTutorialMetadata(int id)
	{
		super(id);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int metadata)
	{
		return metadata;
	}

	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata > BlockTutorialMetadata.type.length || metadata < 0)
		{
			metadata = 0;
		}
		return super.getUnlocalizedName() + "." + BlockTutorialMetadata.type[metadata];
	}
}
