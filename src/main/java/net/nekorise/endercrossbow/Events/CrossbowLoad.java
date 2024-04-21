package net.nekorise.endercrossbow.Events;

import de.tr7zw.changeme.nbtapi.*;
import io.papermc.paper.event.entity.EntityLoadCrossbowEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;

import java.util.List;

public class CrossbowLoad implements Listener
{
    @EventHandler
    public void onCrossbowLoad(EntityLoadCrossbowEvent event)
    {
        if (!(event.getEntity() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getEntity();

        if (!player.hasPermission("ecrosswbow.load")) { return; }

        loadEnderPearl(player, event);

    }

    private void loadEnderPearl(Player player, EntityLoadCrossbowEvent event)
    {
        int i = 0;
        for (ItemStack itemInv : player.getInventory())
        {
            if (itemInv == null)
            {
                i++;
                continue;
            }
            if (itemInv.getType() == Material.ARROW || itemInv.getType() == Material.TIPPED_ARROW)
            {
                break;
            }
            if (itemInv.getType() == Material.ENDER_PEARL)
            {
                ItemStack item = player.getItemInHand();
                CrossbowMeta crossbowMeta = (CrossbowMeta) item.getItemMeta();
                ItemStack chargedProjectile = new ItemStack(Material.FIREWORK_ROCKET);
                crossbowMeta.setChargedProjectiles(List.of(chargedProjectile));
                item.setItemMeta(crossbowMeta);
                NBTItem nbtItem = new NBTItem(item);
                nbtItem.getCompoundList("ChargedProjectiles").get(0).setString("id", "minecraft:ender_pearl");
                item = nbtItem.getItem();
                player.setItemInHand(item);
                itemInv.setAmount(itemInv.getAmount()-1);
                player.getInventory().setItem(i, itemInv);
                event.setCancelled(true);

                break;
            }
            i++;
        }
    }
}
