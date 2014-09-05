package seb95b.ColoredAdmin;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class CAModifConfig {
	
	private String group;
	private String color;
	private CommandSender sender;
	private ColoredAdmin plugin;
	
	public CAModifConfig(String group, String color, CommandSender sender, ColoredAdmin plugin) {
		
		if(group != null)
			this.group = group;
		
		if(color != null)
			this.color = color;
		
		this.sender = sender;
		this.plugin = plugin;
	}
	
	public void ModifyColor() {
		
		try {
			FileConfiguration configSet = plugin.getConfig();
			configSet.set("config."+ this.group, this.color);
			plugin.saveConfig();
			sender.sendMessage("The group color has been modified to "+ CAListener.getColor(this.color) + this.color);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
