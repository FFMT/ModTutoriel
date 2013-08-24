package tutoriel.common;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStep;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSlabTutorial extends BlockStep
{
	public static final String[] StepTypes = new String[] {"tuto", "tutometa1", "diamond", "gold", "iron"};

	public BlockSlabTutorial(int id, boolean isdouble)
	{
		super(id, isdouble);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
		if(!this.isDoubleSlab)
		{
			this.setLightOpacity(0);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		int k = metadata & 7;
		return k == 0 ? ModTutoriel.BlockTutorial.getBlockTextureFromSide(side) : k == 1 ? ModTutoriel.TutorialMetadata.getIcon(side, 0) : k == 2 ? Block.blockDiamond.getBlockTextureFromSide(side) : k == 3 ? Block.blockGold.getBlockTextureFromSide(side) : Block.blockIron.getBlockTextureFromSide(side);
	}

	@SideOnly(Side.CLIENT)
	private static boolean isBlockSingleSlab(int id)
	{
		return id == ModTutoriel.SingleSlabTuto.blockID;
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z)
	{
		return isBlockSingleSlab(this.blockID) ? this.blockID : ModTutoriel.DoubleSlabTuto.blockID;
	}

	public int idDropped(int metadata, Random rand, int fortune)
	{
		return ModTutoriel.SingleSlabTuto.blockID;
	}

	protected ItemStack createStackedBlock(int metadata)
	{
		return new ItemStack(ModTutoriel.SingleSlabTuto.blockID, 2, metadata & 7);
	}

	public String getFullSlabName(int metadata)
	{
		if(metadata < 0 || metadata >= StepTypes.length)
		{
			metadata = 0;
		}

		return super.getUnlocalizedName() + "." + StepTypes[metadata];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
	{
		if(id != ModTutoriel.DoubleSlabTuto.blockID)
		{
			for(int i = 0; i < StepTypes.length; i++)
			{
				list.add(new ItemStack(id, 1, i));
			}
		}
	}
}