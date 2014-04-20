package tutoriel.common;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class ItemCdTutorial extends ItemRecord
{
	protected ItemCdTutorial(String name)
	{
		super(name);
	}
    
    public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation("modtutoriel:records.tuto");
    }
}