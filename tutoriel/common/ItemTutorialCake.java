package tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTutorialCake extends Item
{
    public ItemTutorialCake(int id)
    {
        super(id);
        this.setCreativeTab(ModTutoriel.TutorialCreativeTabs);
    }

    public boolean onItemUse(ItemStack ItemStack, EntityPlayer entityplayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if (world.getBlockId(x, y, z) != Block.snow.blockID)
        {
            if (par7 == 0)
            {
                --y;
            }

            if (par7 == 1)
            {
                ++y;
            }

            if (par7 == 2)
            {
                --z;
            }

            if (par7 == 3)
            {
                ++z;
            }

            if (par7 == 4)
            {
                --x;
            }

            if (par7 == 5)
            {
                ++x;
            }

            if (!world.isAirBlock(x, y, z))
            {
                return false;
            }
        }

        if (!entityplayer.canPlayerEdit(x, y, z, par7, ItemStack))
        {
            return false;
        }
        else
        {
            if (Block.redstoneWire.canPlaceBlockAt(world, x, y, z))
            {
                --ItemStack.stackSize;
                world.setBlock(x, y, z, ModTutoriel.BlockTutorialCake.blockID);
				String placesound = ModTutoriel.BlockTutorialCake.stepSound.getPlaceSound();
				float volume = ModTutoriel.BlockTutorialCake.stepSound.getVolume();
				float pitch = ModTutoriel.BlockTutorialCake.stepSound.getPitch();
				world.playSoundEffect(x, y, z, placesound, volume, pitch);
            }
            return true;
        }
    }
}