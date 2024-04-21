package net.nekorise.endercrossbow.Commands;

import net.nekorise.endercrossbow.EnderCrossbow;
import net.nekorise.endercrossbow.Utils.HEX;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length <= 0 || !(args[0].equalsIgnoreCase("reload")))
        {
            sender.sendMessage(HEX.ApplyColor("&#1fa6f2Usage: &#1fd3f2/ecrossbow reload"));
            return false;
        }
        if (args[0].equalsIgnoreCase("reload"))
        {
            try
            {
                EnderCrossbow.getPlugin().reloadConfig();
                sender.sendMessage(HEX.ApplyColor("&#1fa6f2EnderCrossbow: &#1ff2b3configuration has been reloaded"));
                return true;
            } catch (Exception ex)
            {
                ex.printStackTrace();
                sender.sendMessage(HEX.ApplyColor("&#1fa6f2EnderCrossbow: &#1fd3f2configuration reload failed"));
            }

        }

        return false;
    }
}
