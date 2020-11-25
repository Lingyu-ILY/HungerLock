package com.Lingyu.HungerLock;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	// Fired when plugin is enabled
    @Override
    public void onEnable() {
    	PluginManager pm = this.getServer().getPluginManager();
    	pm.registerEvents(this, this);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	getServer().getConsoleSender().sendMessage("HungerLock is disable!");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFoodChange(FoodLevelChangeEvent event) {
    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
            	Player player = (Player) event.getEntity();
            	float st = player.getSaturation();
            	String message = "§6§l飽和: §e§l" + (new DecimalFormat("0").format(st));
            if(player.getFoodLevel() > 19) {
            	event.setCancelled(true);
                player.setFoodLevel(19);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            }
            }
        }, 1L);
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onSpawn(PlayerRespawnEvent event) {
    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
            	Player player = event.getPlayer();
                player.setFoodLevel(19);
            }
        }, 1L);
    }
}