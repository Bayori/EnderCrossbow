package net.nekorise.endercrossbow.Events;

import de.tr7zw.changeme.nbtapi.*;
import net.nekorise.endercrossbow.EnderCrossbow;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;


public class CrossbowShoot implements Listener
{
    @EventHandler
    public void onShoot(EntityShootBowEvent event)
    {
        if (!(event.getEntity() instanceof Player) || event.getBow().getType() != Material.CROSSBOW)
        {
            return;
        }
        Player player = (Player) event.getEntity();

        if (!player.hasPermission("ecrosswbow.shoot")) { return; }
        ItemStack crossbow = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(crossbow);
        String chargerProjectile = nbtItem.getCompoundList("ChargedProjectiles").get(0).getString("id");

        if (chargerProjectile.equals("minecraft:ender_pearl"))
        {
            event.setCancelled(true);
            EnderPearl enderPearl = player.launchProjectile(EnderPearl.class);

            double shootPower = getDoubleCfg("shot-power");
            if (getBoolCfg("vector-normalize"))
            {
                enderPearl.setVelocity(player.getEyeLocation().getDirection().normalize().multiply(shootPower));
            }
            else
            {
                enderPearl.setVelocity(player.getEyeLocation().getDirection().multiply(shootPower));
            }

            player.playSound(player, Sound.ENTITY_ENDER_PEARL_THROW, 1, 1);
        }
    }

    private double getDoubleCfg(String path)
    {
        return EnderCrossbow.getPlugin().getConfig().getDouble(path);
    }
    private boolean getBoolCfg(String path)
    {
        return EnderCrossbow.getPlugin().getConfig().getBoolean(path);
    }
}
