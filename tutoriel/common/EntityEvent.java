package tutoriel.common;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityEvent
{
	@ForgeSubscribe
	public void onLivingDrops(LivingDropsEvent event)
	{
		if(event.entity instanceof EntitySkeleton)
		{
			EntitySkeleton skeleton = (EntitySkeleton)event.entity;
			if(skeleton.getSkeletonType() == 0)
			{
				for(int i = 0; i < event.drops.size(); i++)
				{
					if(event.drops.get(i).getEntityItem().itemID != Item.bone.itemID || event.drops.get(i).getEntityItem().itemID != Item.arrow.itemID)
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
				if(event.drops.get(i).getEntityItem().itemID == Item.porkCooked.itemID && event.source.getDamageType().equals("anvil"))
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
					if(event.drops.get(i).getEntityItem().itemID == Item.skull.itemID && event.lootingLevel == 2)
					{
						event.entity.worldObj.newExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 15, true, true);
					}
				}
			}
		}
	}
}