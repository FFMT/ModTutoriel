package tutoriel.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockTutorial extends Block
{
	private Icon icontop, iconbottom;

	public BlockTutorial(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("modtutoriel:BlockTutorial");
		icontop = iconRegister.registerIcon("modtutoriel:BlockTutorial_Top");
		iconbottom = iconRegister.registerIcon("modtutoriel:BlockTutorial_Bottom");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		return side == 0 ? iconbottom : side == 1 ? icontop : blockIcon;
	}
}
