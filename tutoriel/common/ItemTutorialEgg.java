package tutoriel.common;

import net.minecraft.item.ItemFood;

public class ItemTutorialEgg extends ItemFood
{
	public ItemTutorialEgg(int foodAmount, float saturation, boolean isWolfFood)
	{
		super(foodAmount, saturation, isWolfFood);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}
}
