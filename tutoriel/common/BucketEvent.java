package tutoriel.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketEvent
{
	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event)
	{
		int id = event.world.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ);
		int metadata = event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ);
		if(id == ModTutoriel.blockFluidTutorial.blockID && metadata == 0)
		{
			event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
			event.result = new ItemStack(ModTutoriel.bucketTutorial);
			event.setResult(Result.ALLOW);
		}
	}
}