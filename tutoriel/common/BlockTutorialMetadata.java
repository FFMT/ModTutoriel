package tutoriel.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTutorialMetadata extends Block
{
	public static String[] type = new String[]
	{"block1", "block2", "block3", "block4", "block5", "block6", "block7", "block8"};
	private String[] sideType = new String[]{"bottom", "top", "north", "south", "west", "east"};
	private Icon[][] IconArray;

	public BlockTutorialMetadata(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public void registerIcons(IconRegister iconregister)
	{
		IconArray = new Icon[type.length][6]; //6 car il y 6 side
		for(int i = 0; i < type.length; i++) //la boucle pour les metadata
		{
			for(int j = 0; j < 6; j++) //la boucle pour les côtés
			{
				IconArray[i][j] = iconregister.registerIcon("ModTutoriel:" + type[i] + "_" + sideType[j]);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
	{
		for(int metadata = 0; metadata < type.length; metadata++)
		{
			list.add(new ItemStack(id, 1, metadata));
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		return metadata < type.length && metadata >= 0 ? IconArray[metadata][side] : IconArray[0][0];
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}
}
