package tutoriel.common;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTutorialArmor extends ItemArmor
{
	public ItemTutorialArmor(int id, EnumArmorMaterial armorMaterial, int type, int layer)
	{
		super(id, armorMaterial, type, layer);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		if(stack.itemID == ModTutoriel.TutorialLeggings.itemID)
		{
			return "modtutoriel:textures/models/armor/tutorial_layer_2.png";
		}
		else
		{
			return "modtutoriel:textures/models/armor/tutorial_layer_1.png";
		}
	}
}