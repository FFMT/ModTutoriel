package tutoriel.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCakeTutorial extends Block
{
	private Icon cakeTopIcon, cakeBottomIcon, cakeInnerIcon;

	public BlockCakeTutorial(int id)
	{
		super(id, Material.cake);
		this.setTickRandomly(true);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}

	public Icon getIcon(int icon1, int icon2)
	{
		return icon1 == 1 ? this.cakeTopIcon : (icon1 == 0 ? this.cakeBottomIcon : (icon2 > 0 && icon1 == 4 ? this.cakeInnerIcon : this.blockIcon));
	}

	public void registerIcons(IconRegister iconregister)
	{
		this.blockIcon = iconregister.registerIcon("modtutoriel:caketuto_side");
		this.cakeInnerIcon = iconregister.registerIcon("modtutoriel:caketuto_inner");
		this.cakeTopIcon = iconregister.registerIcon("modtutoriel:caketuto_top");
		this.cakeBottomIcon = iconregister.registerIcon("modtutoriel:caketuto_bottom");
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int dir012, int dir3, int y)
	{
		int l = par1IBlockAccess.getBlockMetadata(dir012, dir3, y);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int dir012, int dir3, int y)
	{
		int l = world.getBlockMetadata(dir012, dir3, y);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		return AxisAlignedBB.getAABBPool().getAABB((double)((float)dir012 + f1), (double)dir3, (double)((float)y + f), (double)((float)(dir012 + 1) - f), (double)((float)dir3 + f2 - f), (double)((float)(y + 1) - f));
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int dir012, int dir3, int y)
	{
		int l = world.getBlockMetadata(dir012, dir3, y);
		float f = 0.0625F;
		float f1 = (float)(1 + l * 2) / 16.0F;
		float f2 = 0.5F;
		return AxisAlignedBB.getAABBPool().getAABB((double)((float)dir012 + f1), (double)dir3, (double)((float)y + f), (double)((float)(dir012 + 1) - f), (double)((float)dir3 + f2), (double)((float)(y + 1) - f));
	}

	public void setBlockBoundsForItemRender()
	{
		float f = 0.0625F;
		float f1 = 0.5F;
		this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer)
	{
		this.eatCakeSlice(world, x, y, z, entityplayer);
		return true;
	}

	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		this.eatCakeSlice(par1World, par2, par3, par4, par5EntityPlayer);
	}

	private void eatCakeSlice(World world, int x, int y, int z, EntityPlayer entityplayer)
	{
		if(entityplayer.canEat(false))
		{
			entityplayer.getFoodStats().addStats(5, 0.1F);
			int l = world.getBlockMetadata(x, y, z) + 1;

			if(l >= 6)
			{
				world.setBlockToAir(x, y, z);
			}
			else
			{
				world.setBlockMetadataWithNotify(x, y, z, l, 2);
			}
		}
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return !super.canPlaceBlockAt(world, x, y, z) ? false : this.canBlockStay(world, x, y, z);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z)
	{
		if(!this.canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlockMaterial(x, y - 1, z).isSolid();
	}

	public int quantityDropped(Random random)
	{
		return 0;
	}

	public int idDropped(int id, Random random, int par3)
	{
		return 0;
	}

	public int idPicked(World world, int x, int y, int z)
	{
		return ModTutoriel.ItemTutorialCake.itemID;
	}
}