package tutoriel.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockTutorialWall extends BlockWall
{
	public static Block block;
	public BlockTutorialWall(int id, Block block)
	{
		super(id, block);
		this.block = block;
	}
	
    public Icon getIcon(int side, int meta)
    {
        return block.getBlockTextureFromSide(side);
    }
	
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(id, 1, 0));
    }

}
