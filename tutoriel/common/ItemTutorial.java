package tutoriel.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTutorial extends Item
{
	public ItemTutorial(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		world.playSoundAtEntity(player, "modtutoriel:explosion", 1.0F, 1.0F);
		return stack;
	}
}