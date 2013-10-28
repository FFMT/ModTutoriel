package tutoriel.common;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidTutorial extends BlockFluidClassic
{
	private Icon stillIcon, flowingIcon;

	public BlockFluidTutorial(int id, Fluid fluid, Material material)
	{
		super(id, fluid, material);
	}

	public Icon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}

	public void registerIcons(IconRegister register)
	{
		stillIcon = register.registerIcon("modtutoriel:tuto_fluid_still");
		flowingIcon = register.registerIcon("modtutoriel:tuto_fluid_flow");
	}

	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if(world.getBlockMaterial(x, y, z).isLiquid())
		{
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if(world.getBlockMaterial(x, y, z).isLiquid())
		{
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}
}