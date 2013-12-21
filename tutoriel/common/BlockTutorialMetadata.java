package tutoriel.common;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTutorialMetadata extends BlockContainer
{
	public static String[] type = new String[] {"block1", "block2", "block3", "block4", "block5", "block6", "block7", "block8"};
	private Icon[] Icon1 = new Icon[6];
	private Icon[] Icon2 = new Icon[5];
	private Icon[] Icon3 = new Icon[4];
	private Icon[] Icon4 = new Icon[3];
	private Icon[] Icon5 = new Icon[2];
	private Icon[] Icon6 = new Icon[2];
	private Icon Icon7, Icon8;

	public BlockTutorialMetadata(int id)
	{
		super(id, Material.rock);
		this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
	}

	public void registerIcons(IconRegister iconregister)
	{
		Icon1[0] = iconregister.registerIcon("modtutoriel:block1_bottom");
		Icon1[1] = iconregister.registerIcon("modtutoriel:block1_top");
		Icon1[2] = iconregister.registerIcon("modtutoriel:block1_north");
		Icon1[3] = iconregister.registerIcon("modtutoriel:block1_south");
		Icon1[4] = iconregister.registerIcon("modtutoriel:block1_west");
		Icon1[5] = iconregister.registerIcon("modtutoriel:block1_east");

		Icon2[0] = iconregister.registerIcon("modtutoriel:block2");
		Icon2[1] = iconregister.registerIcon("modtutoriel:block2_north");
		Icon2[2] = iconregister.registerIcon("modtutoriel:block2_south");
		Icon2[3] = iconregister.registerIcon("modtutoriel:block2_west");
		Icon2[4] = iconregister.registerIcon("modtutoriel:block2_east");

		Icon3[0] = iconregister.registerIcon("modtutoriel:block3_top");
		Icon3[1] = iconregister.registerIcon("modtutoriel:block3_bottom");
		Icon3[2] = iconregister.registerIcon("modtutoriel:block3_front");
		Icon3[3] = iconregister.registerIcon("modtutoriel:block3");

		Icon4[0] = iconregister.registerIcon("modtutoriel:block4_top");
		Icon4[1] = iconregister.registerIcon("modtutoriel:block4_bottom");
		Icon4[2] = iconregister.registerIcon("modtutoriel:block4");

		Icon5[0] = iconregister.registerIcon("modtutoriel:block5_bottom_top");
		Icon5[1] = iconregister.registerIcon("modtutoriel:block5");

		Icon6[0] = iconregister.registerIcon("modtutoriel:block6_top");
		Icon6[1] = iconregister.registerIcon("modtutoriel:block6");

		Icon7 = iconregister.registerIcon("modtutoriel:block7");
		Icon8 = iconregister.registerIcon("modtutoriel:block8");
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
		switch(metadata)
		{
		case 0:
			return Icon1[side];
		case 1:
			return side > 1 ? Icon2[side - 1] : Icon2[0];
		case 2:
			return side == 1 ? Icon3[0] : side == 0 ? Icon3[1] : side == 3 ? Icon3[2] : Icon3[3];
		case 3:
			return side < 3 ? Icon4[side] : Icon4[2];
		case 4:
			return side < 2 ? Icon5[0] : Icon5[1];
		case 5:
			return side == 1 ? Icon6[0] : Icon6[1];
		case 6:
			return Icon7;
		case 7:
			return Icon8;
		default:
			return blockIcon;
		}
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		switch(metadata)
		{
		case 0:
			return new TileEntityTutorial();
		case 2:
			return new TileEntityTutorial2();
		case 3:
			return new TileEntityBigChest();
		default:
			return null;
		}
	}

	public boolean hasTileEntity(int metadata)
	{
		if(metadata == 0 || metadata == 1 || metadata == 2 || metadata == 3)
			return true;
		else
			return false;
	}

	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockaccess, int x, int y, int z, int side)
	{
		if(blockaccess.getBlockMetadata(x, y, z) == 2)
		{
			TileEntity te = blockaccess.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityTutorial2)
			{
				TileEntityTutorial2 tetuto = (TileEntityTutorial2)te;
				int direction = tetuto.getDirection();
				return side == 1 ? Icon3[0] : (side == 0 ? Icon3[1] : (direction == 2 && side == 2 ? Icon3[2] : (direction == 3 && side == 5 ? Icon3[2] : (direction == 0 && side == 3 ? Icon3[2] : (direction == 1 && side == 4 ? Icon3[2] : Icon3[3])))));
			}
		}
		return this.getIcon(side, blockaccess.getBlockMetadata(x, y, z));
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(world.getBlockMetadata(x, y, z) == 0)
		{
			if(!world.isRemote)
			{
				TileEntityTutorial te = (TileEntityTutorial)world.getBlockTileEntity(x, y, z);
				te.addplayertolist(player.getEntityName());
				player.addChatMessage("Derniers utilisateurs : " + te.getPlayerList());
			}
			return true;
		}
		
		if(world.getBlockMetadata(x, y, z) == 3)
		{
			FMLNetworkHandler.openGui(player, ModTutoriel.instance, 0, world, x, y, z);
			return true;
		}
		return false;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && stack.getItemDamage() == 2 && te instanceof TileEntityDirectional)
		{
			((TileEntityDirectional)te).setDirection((byte)direction);
			world.markBlockForUpdate(x, y, z);
		}
		if(te != null && stack.getItemDamage() == 3 && te instanceof TileEntityBigChest && stack.hasDisplayName())
		{
			((TileEntityBigChest)te).setCustomGuiName(stack.getDisplayName());
		}
	}
	
    public void breakBlock(World world, int x, int y, int z, int side, int metadata)
    {
    	if(metadata == 3)
    	{
    		dropContainerItem(world, x, y, z);
    	}
        super.breakBlock(world, x, y, z, side, metadata);
    }
    
    protected void dropContainerItem(World world, int x, int y, int z)
    {
    	TileEntityBigChest bigchest = (TileEntityBigChest)world.getBlockTileEntity(x, y, z);

        if (bigchest != null)
        {
            for (int slotId = 0; slotId < bigchest.getSizeInventory(); slotId++)
            {
                ItemStack stack = bigchest.getStackInSlot(slotId);

                if (stack != null)
                {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; stack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int k1 = world.rand.nextInt(21) + 10;

                        if (k1 > stack.stackSize)
                        {
                            k1 = stack.stackSize;
                        }

                        stack.stackSize -= k1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(stack.itemID, k1, stack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

                        if (stack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                        }
                    }
                }
            }
        }
    }
}