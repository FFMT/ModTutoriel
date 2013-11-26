package tutoriel.common;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

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
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.usage").setColor(EnumChatFormatting.RED));
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.creeper.usage").setColor(EnumChatFormatting.RED));
			}
			else if(arguments[1].matches("enable"))
			{
				sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("enableCreeper", "true");
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.creeper.enable"));
			}
			else if(arguments[1].matches("disable"))
			{
				sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("enableCreeper", "false");
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.creeper.disable"));
			}
			else if(arguments[1].matches("help"))
			{
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.usage"));
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.creeper.usage"));
			}
			else
			{
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.creeper.invalid").setColor(EnumChatFormatting.RED));
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
					sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.success", playermp.getEntityName(), arguments[1]));
				}
				else
				{
					sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.failure"));
				}
			}
			else if(arguments.length == 3)
			{
				if(arguments[2].matches("help"))
				{
					sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.usage"));
					sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.usage.1"));
					sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.usage.2"));
				}
				else
				{
					EntityPlayerMP playermp = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(arguments[1]);
					if(playermp != null)
					{
						playermp.setFire(this.parseInt(sender, arguments[2]));
						sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.success", playermp.getEntityName(), arguments[2]));
					}
					else
					{
						sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.failure"));
					}
				}
			}
			else
			{
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.usage").setColor(EnumChatFormatting.RED));
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.usage.1").setColor(EnumChatFormatting.RED));
				sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.tutoriel.fire.usage.2").setColor(EnumChatFormatting.RED));
			}
		}
		else if(arguments[0].matches("help"))
		{
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.tutoriel.help"));
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