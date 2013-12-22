package tutoriel.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tutoriel.proxy.TutoClientProxy;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSculpture extends Block
{
	public static String[] subBlock = new String[] {"sculpture", "cupboard", "machine"};

	public BlockSculpture(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}

	public TileEntity createTileEntity(World world, int metadata)
	{
		switch(metadata)
		{
		case 0:
			return new TileEntitySculpture();
		case 1:
			return new TileEntityCupboard();
		case 2:
			return new TileEntityMachine();
		default:
			return null;
		}
	}

	public boolean hasTileEntity(int metadata)
	{
		switch(metadata)
		{
		case 0:
			return true;
		case 1:
			return true;
		case 2:
			return true;
		default:
			return false;
		}
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
		if(metadata == 0 || metadata == 2)
		{
			return Block.stone.getIcon(0, 0);
		}
		return Block.wood.getIcon(0, 0);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityDirectional)
		{
			((TileEntityDirectional)te).setDirection((byte)direction);
			world.markBlockForUpdate(x, y, z);
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list)
	{
		for(int metadata = 0; metadata < subBlock.length; metadata++)
		{
			list.add(new ItemStack(id, 1, metadata));
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(world.getBlockMetadata(x, y, z) == 1)
		{
			FMLNetworkHandler.openGui(player, ModTutoriel.instance, 1, world, x, y, z);
			return true;
		}
		return false;
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}

	public void breakBlock(World world, int x, int y, int z, int side, int metadata)
	{
		if(metadata == 1)
		{
			dropContainerItem(world, x, y, z);
		}
		super.breakBlock(world, x, y, z, side, metadata);
	}

	protected void dropContainerItem(World world, int x, int y, int z)
	{
		TileEntityCupboard teCupboard = (TileEntityCupboard)world.getBlockTileEntity(x, y, z);

		if(teCupboard != null)
		{
			for(int slotId = 0; slotId < teCupboard.getSizeInventory(); slotId++)
			{
				ItemStack stack = teCupboard.getStackInSlot(slotId);

				if(stack != null)
				{
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; stack.stackSize > 0; world.spawnEntityInWorld(entityitem))
					{
						int k1 = world.rand.nextInt(21) + 10;

						if(k1 > stack.stackSize)
						{
							k1 = stack.stackSize;
						}

						stack.stackSize -= k1;
						entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(stack.itemID, k1, stack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

						if(stack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		switch(world.getBlockMetadata(x, y, z))
		{
		case 0:
			return AxisAlignedBB.getAABBPool().getAABB((double)x + 0.1D, (double)y + 0.1D, (double)z + 0.1D, (double)x + 0.9D, (double)y + 0.9D, (double)z + 0.9D);
		case 2:
			return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + 0.6D, (double)z + this.maxZ);
		default:
			return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		switch(world.getBlockMetadata(x, y, z))
		{
		case 0:
			return AxisAlignedBB.getAABBPool().getAABB((double)x + 0.1D, (double)y + 0.1D, (double)z + 0.1D, (double)x + 0.9D, (double)y + 0.9D, (double)z + 0.9D);
		case 2:
			return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + 0.6D, (double)z + this.maxZ);
		default:
			return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
		}
	}
	
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventValue)
    {
        super.onBlockEventReceived(world, x, y, z, eventId, eventValue);
        TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(eventId, eventValue) : false;
    }
}