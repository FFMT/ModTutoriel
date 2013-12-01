package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tutoriel.proxy.TutoClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSculpture extends Block
{
	public BlockSculpture(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}

	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntitySculpture();
	}

	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return TutoClientProxy.renderInventoryTESRId;
	}

	public Icon getIcon(int side, int metadata)
	{
		return Block.blockIron.getIcon(0, 0);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntitySculpture)
		{
			((TileEntitySculpture)te).setDirection((byte)direction);
			world.markBlockForUpdate(x, y, z);
		}
	}
}