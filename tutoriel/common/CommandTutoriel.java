package tutoriel.common;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;

public class CommandTutoriel extends CommandBase
{
	public static boolean spawn;
	
	@Override
	public String getCommandName()
	{
		return "tutoriel";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.tutoriel.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		if (arguments.length <= 0)
			throw new WrongUsageException("Type '" + this.getCommandUsage(sender) + "' for help.");
		if (arguments[0].matches("spawn"))
		{
			if (arguments[1].matches("true"))
			{
				spawn = true;
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Spawn Activé"));
			} else if (arguments[1].matches("false"))
			{
				spawn = false;
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Spawn Désactivé"));
			} else if (arguments[1].matches("help"))
			{
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Format: '" + this.getCommandName() + " <command> <arguments>'"));
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Available commands:"));
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("- true : Active le spawn."));
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("- false : Désactive le spawn."));
			}
		} else if (arguments[0].matches("help"))
		{
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Format: '" + this.getCommandName() + " <command> <arguments>'"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Available commands:"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("- spawn : Active ou désactive le spawn."));
		}
		throw new WrongUsageException(this.getCommandUsage(sender));
	}
	
	public List addTabCompletionOptions(ICommandSender sender, String[] arguments)
	{
		return arguments.length == 1 ? getListOfStringsMatchingLastWord(arguments, new String[] {"spawn"}): (arguments.length == 2 ? getListOfStringsMatchingLastWord(arguments, new String[] {"true", "false"}): null);
	}
}