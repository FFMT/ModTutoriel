package tutoriel.common;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTutorialArmor extends ItemArmor
{
	public ItemTutorialArmor(ArmorMaterial armorMaterial, int type)
	{
		super(armorMaterial, 0, type);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == ModTutoriel.TutorialLeggings)
		{
			return "modtutoriel:textures/models/armor/tutorial_layer_2.png";
		}
		else
		{
			return "modtutoriel:textures/models/armor/tutorial_layer_1.png";
		}
	}
}