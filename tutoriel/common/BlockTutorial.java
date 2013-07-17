package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTutorial extends Block
{
	public BlockTutorial(int id) 
	{
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
