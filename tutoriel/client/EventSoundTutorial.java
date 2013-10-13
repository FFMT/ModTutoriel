package tutoriel.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventSoundTutorial
{
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		try
		{
			event.manager.addSound("modtutoriel:explosion1.ogg");
			event.manager.addSound("modtutoriel:explosion2.ogg");
			event.manager.addSound("modtutoriel:explosion3.ogg");
			event.manager.addStreaming("modtutoriel:cd.ogg");
		}
		catch(Exception e)
		{
			System.out.println("Failed to registry sound");
		}
	}
}
