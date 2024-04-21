package net.nekorise.endercrossbow.Events;

import net.nekorise.endercrossbow.EnderCrossbow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RightClick implements Listener
{
    ItemStack pearl;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRightClick(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        if (!player.hasPermission("ecrosswbow.load")) { return; }

        if (event.getAction().isRightClick() && player.getItemInHand().getType() == Material.CROSSBOW)
        {
            Inventory inventory = player.getInventory();
            ItemStack item;
            for (int i = 0; i <= inventory.getSize(); i++)
            {
                if (inventory.getItem(i) == null) { continue; }

                item = inventory.getItem(i);

                if (item.getType() == Material.ARROW || item.getType()  == Material.TIPPED_ARROW) { break; }

                if (item.getType() == Material.ENDER_PEARL)
                {
                     pearl = item;

                    item.setType(Material.ARROW);
                    inventory.setItem(i, item);

                    int finalI = i;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(EnderCrossbow.getPlugin(), () -> setPearlBack(inventory, finalI), 2);

                    break;
                }
            }
        }
    }
    private void setPearlBack(Inventory inventory, int slot)
    {
        pearl.setType(Material.ENDER_PEARL);
        inventory.setItem(slot, pearl);
    }
}
