package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public class ItemBucketTutorial extends ItemBucket
{
	public ItemBucketTutorial(Block fluid)
	{
		super(fluid);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}
}