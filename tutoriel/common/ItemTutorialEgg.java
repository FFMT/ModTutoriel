package tutoriel.common;

import net.minecraft.item.ItemFood;

public class ItemTutorialEgg extends ItemFood
{
	public ItemTutorialEgg(int id, int foodAmount, float saturation, boolean isWolfFood)
	{
		super(id, foodAmount, saturation, isWolfFood);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}
}
