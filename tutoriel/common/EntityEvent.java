package tutoriel.common;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityEvent
{
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		if(event.entity instanceof EntitySkeleton)
		{
			EntitySkeleton skeleton = (EntitySkeleton)event.entity;
			if(skeleton.getSkeletonType() == 0)
			{
				for(int i = 0; i < event.drops.size(); i++)
				{
					if(event.drops.get(i).getEntityItem().getItem() != Items.bone || event.drops.get(i).getEntityItem().getItem() != Items.arrow)
					{
						event.drops.remove(i);
					}
				}
			}
		}

		if(event.entity instanceof EntityPig)
		{
			for(int i = 0; i < event.drops.size(); i++)
			{
				if(event.drops.get(i).getEntityItem().getItem() == Items.cooked_porkchop && event.source.getDamageType().equals("anvil"))
				{
					event.entity.worldObj.newExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 5, true, true);
				}
			}
		}

		if(event.entity instanceof EntitySkeleton)
		{
			EntitySkeleton skeleton = (EntitySkeleton)event.entity;
			if(skeleton.getSkeletonType() == 1)
			{
				for(int i = 0; i < event.drops.size(); i++)
				{
					if(event.drops.get(i).getEntityItem().getItem() == Items.skull && event.lootingLevel == 2)
					{
						event.entity.worldObj.newExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 15, true, true);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntityCreeper)
		{
			if(event.world.getWorldInfo().getGameRulesInstance().hasRule("enableCreeper"))
			{
				if(!event.world.getWorldInfo().getGameRulesInstance().getGameRuleBooleanValue("enableCreeper"))
				{
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if(event.entity instanceof EntityCreeper)
		{
			if(event.entity.worldObj.getWorldInfo().getGameRulesInstance().hasRule("enableCreeper"))
			{
				if(!event.entity.worldObj.getWorldInfo().getGameRulesInstance().getGameRuleBooleanValue("enableCreeper"))
				{
					event.entity.setDead();
				}
			}
		}
	}
}