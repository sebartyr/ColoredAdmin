package seb95b.ColoredAdmin;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class CAListener implements Listener {
	
	public static ColoredAdmin plugin;
	public static String colorAdmin;
	public static String colorModo;
	
public CAListener(ColoredAdmin plugin) {
	CAListener.plugin = plugin;
	loadConfig();

}

public static void loadConfig() {
	
	FileConfiguration config = plugin.getConfig();
	
	if(config.get("config.admin") != null)
		colorAdmin = plugin.getConfig().getString("config.admin");
	else
		colorAdmin = "DARK_RED";
	
	if(config.get("config.modo") != null)
		colorModo = config.getString("config.modo");
	else
		colorModo = "RED";
}

public static ChatColor getColor(String color) {
	
	if((color.equalsIgnoreCase("BLACK")) || (color.equalsIgnoreCase("&0")))
		return ChatColor.BLACK;
	
	else if((color.equalsIgnoreCase("DARK_BLUE")) || (color.equalsIgnoreCase("&1")))
		return ChatColor.DARK_BLUE;
	
	else if((color.equalsIgnoreCase("DARK_GREEN")) || (color.equalsIgnoreCase("&2")))
		return ChatColor.DARK_GREEN;
	
	else if((color.equalsIgnoreCase("DARK_AQUA")) || (color.equalsIgnoreCase("&3")))
		return ChatColor.DARK_AQUA;
	
	else if((color.equalsIgnoreCase("DARK_RED")) || (color.equalsIgnoreCase("&4")))
		return ChatColor.DARK_RED;
	
	else if((color.equalsIgnoreCase("DARK_PURPLE")) || (color.equalsIgnoreCase("&5")))
		return ChatColor.DARK_PURPLE;
	
	else if((color.equalsIgnoreCase("GOLD")) || (color.equalsIgnoreCase("&6")))
		return ChatColor.GOLD;
	
	else if((color.equalsIgnoreCase("GRAY")) || (color.equalsIgnoreCase("&7")))
		return ChatColor.GRAY;
	
	else if((color.equalsIgnoreCase("DARK_GRAY")) || (color.equalsIgnoreCase("&8")))
		return ChatColor.DARK_GRAY;
	
	else if((color.equalsIgnoreCase("BLUE")) || (color.equalsIgnoreCase("&9")))
		return ChatColor.BLUE;
	
	else if((color.equalsIgnoreCase("GREEN")) || (color.equalsIgnoreCase("&a")))
		return ChatColor.GREEN;
	
	else if((color.equalsIgnoreCase("AQUA")) || (color.equalsIgnoreCase("&b")))
		return ChatColor.AQUA;
	
	else if((color.equalsIgnoreCase("RED")) || (color.equalsIgnoreCase("&c")))
		return ChatColor.RED;
	
	else if((color.equalsIgnoreCase("LIGHT_PURPLE")) || (color.equalsIgnoreCase("&d")))
		return ChatColor.LIGHT_PURPLE;
	
	else if((color.equalsIgnoreCase("YELLOW")) || (color.equalsIgnoreCase("&e")))
		return ChatColor.YELLOW;
	
	else if((color.equalsIgnoreCase("WHITE")) || (color.equalsIgnoreCase("&f")))
		return ChatColor.WHITE;
	
	else if((color.equalsIgnoreCase("MAGIC")) || (color.equalsIgnoreCase("&k")))
		return ChatColor.MAGIC;
	
	else if((color.equalsIgnoreCase("BOLD")) || (color.equalsIgnoreCase("&l")))
		return ChatColor.BOLD;
	
	else if((color.equalsIgnoreCase("STRIKETHROUGH")) || (color.equalsIgnoreCase("&m")))
		return ChatColor.STRIKETHROUGH;
	
	else if((color.equalsIgnoreCase("UNDERLINE")) || (color.equalsIgnoreCase("&n")))
		return ChatColor.UNDERLINE;
	
	else if((color.equalsIgnoreCase("ITALIC")) || (color.equalsIgnoreCase("&o")))
		return ChatColor.ITALIC;
	
	return ChatColor.DARK_RED;
}

	@EventHandler
	public void onMessageChat(PlayerChatEvent event) {
		
		for(int i = 0; i < ColoredAdmin.getStatus("admin").size(); i++) {
		event.setMessage(event.getMessage().replace(ColoredAdmin.getStatus("admin").get(i), getColor(colorAdmin) + ColoredAdmin.getStatus("admin").get(i) + ChatColor.WHITE));
		}
		
		for(int i = 0; i < ColoredAdmin.getStatus("modo").size(); i++) {
		event.setMessage(event.getMessage().replace(ColoredAdmin.getStatus("modo").get(i), getColor(colorModo) + ColoredAdmin.getStatus("modo").get(i) + ChatColor.WHITE));
		}
	}
	
}
