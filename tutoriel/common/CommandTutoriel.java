package tutoriel.common;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTutoriel extends CommandBase
{
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
		if(arguments.length <= 0)
			throw new WrongUsageException(this.getCommandUsage(sender));
		if(arguments[0].matches("creeper"))
		{
			if(arguments.length == 1)
			{
				notifyAdmins(sender, "commands.tutoriel.usage");
				notifyAdmins(sender, "commands.tutoriel.creeper.usage");
			}
			else if(arguments[1].matches("enable"))
			{
				sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("enableCreeper", "true");
				notifyAdmins(sender, "commands.tutoriel.creeper.enable");
			}
			else if(arguments[1].matches("disable"))
			{
				sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("enableCreeper", "false");
				notifyAdmins(sender, "commands.tutoriel.creeper.disable");
			}
			else if(arguments[1].matches("help"))
			{
				notifyAdmins(sender, "commands.tutoriel.usage");
				notifyAdmins(sender, "commands.tutoriel.creeper.usage");
			}
			else
			{
				notifyAdmins(sender, "commands.tutoriel.creeper.invalid");
			}
		}
		else if(arguments[0].matches("fire"))
		{
			if(arguments.length == 2)
			{
				EntityPlayerMP playermp = this.getCommandSenderAsPlayer(sender);
				if(playermp != null)
				{
					playermp.setFire(this.parseInt(sender, arguments[1]));
					notifyAdmins(sender, "commands.tutoriel.fire.success", new Object[]{playermp.getCommandSenderName(), arguments[1]});
				}
				else
				{
					notifyAdmins(sender,"commands.tutoriel.fire.failure");
				}
			}
			else if(arguments.length == 3)
			{
				if(arguments[2].matches("help"))
				{
					notifyAdmins(sender,"commands.tutoriel.usage");
					notifyAdmins(sender,"commands.tutoriel.fire.usage.1");
					notifyAdmins(sender,"commands.tutoriel.fire.usage.2");
				}
				else
				{
					EntityPlayerMP playermp = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(arguments[1]);
					if(playermp != null)
					{
						playermp.setFire(this.parseInt(sender, arguments[2]));
						notifyAdmins(sender,"commands.tutoriel.fire.success", new Object[]{playermp.getCommandSenderName(), arguments[2]});
					}
					else
					{
						notifyAdmins(sender,"commands.tutoriel.fire.failure");
					}
				}
			}
			else
			{
				notifyAdmins(sender,"commands.tutoriel.usage");
				notifyAdmins(sender,"commands.tutoriel.fire.usage.1");
				notifyAdmins(sender,"commands.tutoriel.fire.usage.2");
			}
		}
		else if(arguments[0].matches("help"))
		{
			notifyAdmins(sender,"commands.tutoriel.help");
		}
		else
		{
			throw new WrongUsageException(this.getCommandUsage(sender));
		}
	}

	public List addTabCompletionOptions(ICommandSender sender, String[] arguments)
	{
		return arguments.length == 1 ? getListOfStringsMatchingLastWord(arguments, new String[] {"creeper", "fire"}) : (arguments.length == 2 && arguments[0].matches("creeper") ? getListOfStringsMatchingLastWord(arguments, new String[] {"enable", "disable"}) : (arguments.length == 2 && arguments[0].matches("fire") ? getListOfStringsMatchingLastWord(arguments, this.getPlayers()) : null));
	}

	protected String[] getPlayers()
	{
		return MinecraftServer.getServer().getAllUsernames();
	}
}