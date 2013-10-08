package tutoriel.common;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.world.World;

public class MobTutorialHealthBar extends EntityCreature implements IBossDisplayData
{

	public MobTutorialHealthBar(World par1World)
	{
		super(par1World);
		this.setHealth(this.getMaxHealth());
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
	}

}