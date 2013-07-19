package tutoriel.common;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemWithMetadata extends Item
{
	private String[] type = new String[]
	{"item1", "item2", "item3", "item4"};
	private Icon[] IconArray;

	public ItemWithMetadata(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int metadata)
	{
		return metadata;
	}

	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata > type.length || metadata < 0)
		{
			metadata = 0;
		}
		return super.getUnlocalizedName() + "." + type[metadata];
	}

	public void registerIcons(IconRegister iconregister)
	{
		IconArray = new Icon[type.length];
		for(int i = 0; i < type.length; i++)
		{
			IconArray[i] = iconregister.registerIcon("modtutoriel:" + type[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list)
	{
		for(int metadata = 0; metadata < type.length; metadata++)
		{
			list.add(new ItemStack(id, 1, metadata));
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int metadata)
	{
		return metadata < type.length && metadata >= 0 ? IconArray[metadata] : IconArray[0];
	}
}
