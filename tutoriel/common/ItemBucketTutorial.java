package tutoriel.common;

import net.minecraft.item.ItemBucket;

public class ItemBucketTutorial extends ItemBucket
{
	public ItemBucketTutorial(int id, int fluidId)
	{
		super(id, fluidId);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}
}