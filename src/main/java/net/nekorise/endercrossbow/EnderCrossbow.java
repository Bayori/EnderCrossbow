package net.nekorise.endercrossbow;

import net.nekorise.endercrossbow.Commands.MainCommand;
import net.nekorise.endercrossbow.Commands.MainTabCompleter;
import net.nekorise.endercrossbow.Events.CrossbowLoad;
import net.nekorise.endercrossbow.Events.CrossbowShoot;
import net.nekorise.endercrossbow.Events.RightClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnderCrossbow extends JavaPlugin
{
    private static EnderCrossbow plugin;
    @Override
    public void onEnable()
    {
        plugin = this;

        saveDefaultConfig();
        registerCommands();
        registerEvents();
    }

    private void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(new CrossbowLoad(), plugin);
        Bukkit.getPluginManager().registerEvents(new CrossbowShoot(), plugin);
        Bukkit.getPluginManager().registerEvents(new RightClick(), plugin);
    }
    private void registerCommands()
    {
        getCommand("ecrossbow").setExecutor(new MainCommand());
        getCommand("ecrossbow").setTabCompleter(new MainTabCompleter());
    }

    public static EnderCrossbow getPlugin()
    {
        return plugin;
    }
}
