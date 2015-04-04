package me.fruitninja567;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class main extends JavaPlugin implements Listener {

		@Override
		public void onEnable() {
			Bukkit.getPluginManager().registerEvents(this, this);
		}   	
		public void TestKnockbackHacks(Player p) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
			final Player KnockbackHacker = p;
			final Location lastLocation = KnockbackHacker.getLocation();
	        try {
	            String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	            Class<?> CraftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
	            Object handle = CraftPlayer.getMethod("getHandle").invoke(p);
	            Integer ping = (Integer) handle.getClass().getDeclaredField("ping").get(handle);
	            final int ping2 = ping.intValue();
	            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this,  new Runnable() {
					 public void run() {
							if (KnockbackHacker.getLocation().getX() == lastLocation.getX() & KnockbackHacker.getLocation().getY() == lastLocation.getY() & KnockbackHacker.getLocation().getZ() == lastLocation.getZ()) {
								KnockbackHacker.sendMessage("Detected Hacks - " + ping2);
								SecondKnockBackTest(KnockbackHacker, ping2);
							}
						
					}
				},(long) Math.ceil(ping2/50 + 2));
	            
	        } catch (ClassNotFoundException e) {
	        }
			}
		public void SecondKnockBackTest(Player p, int pi) {
			final Player KnockbackHacker = p;
			final Location loc = p.getLocation();
			KnockbackHacker.teleport(new Location(p.getWorld(),268.5,110,-43.5));
				p.setVelocity(new Vector(0,1,0));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this,  new Runnable() {
				 public void run() {
					 if (KnockbackHacker.getLocation().getX() == 268.5 & KnockbackHacker.getLocation().getY() == 110 & KnockbackHacker.getLocation().getZ() == -43.5) {
							KnockbackHacker.kickPlayer("Detected anti-Knockback Hacks");
						} else {
							KnockbackHacker.teleport(loc);
						}
					
				}
			},(long) Math.ceil(pi/50 + 2));
		}
		@EventHandler
	    void onKnockback(EntityDamageByEntityEvent e) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		if (e.getEntity().getType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			Location loc = p.getLocation();
			if (loc.add(0,2,0).getBlock().getType() == Material.AIR) {
			TestKnockbackHacks((Player) e.getEntity());
			}
			}
		}
		
		}
		
		
	
