package de.laurinhummel.skyguns.commands;

import de.laurinhummel.skyguns.guns.Pistol;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getPistol implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        //Pistol pistol = new Pistol(Pistol.PistolType.GLOCK_17);
        player.getInventory().addItem(new Pistol(Pistol.PistolType.GLOCK_17).getItem());
        return false;
    }
}
