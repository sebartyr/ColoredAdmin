package seb95b.ColoredAdmin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ColoredAdmin extends JavaPlugin
implements Listener
{

	private static final Logger log = Logger.getLogger("Minecraft");
    private static ArrayList<String> admin = new ArrayList<String>();
    private static ArrayList<String> modo = new ArrayList<String>();
    private String[] array = new String[2];
    
	public static ArrayList<String> getStatus(String val) {
		
		if(val.equalsIgnoreCase("admin"))
			return admin;
		else if(val.equalsIgnoreCase("modo"))
			return modo;
		
		return admin;
	}

	public void loadList() {
		
		admin.clear();
		modo.clear();
		
		array[0] = "plugins/ColoredAdmin/admin.txt";
		array[1] = "plugins/ColoredAdmin/modo.txt";
		
		for(String status : array) {
		
			//lecture du fichier texte	
			try{
				File ips = new File(status); 
				FileReader ipsr = new FileReader(ips);
				BufferedReader br = new BufferedReader(ipsr);
				String ligne;
				while ((ligne = br.readLine()) != null) {
					if(status.equals(array[0]))
						admin.add(ligne);
					else if(status.equals(array[1]))
						modo.add(ligne);
				}
				br.close(); 
			}		
			catch (Exception e){
				try {
					File f = new File(status) ;
					File p = f.getParentFile();
					if (!p.exists())
						p.mkdirs();
					if (!f.exists())
						f.createNewFile(); 
				} 
				catch (Exception e1) {
					System.out.println(e1.toString());
				}
			}
		
		}	
	}
	
    
	@Override
	public void onEnable() {
		
		this.saveDefaultConfig();
		
		loadList();
		
		getServer().getPluginManager().registerEvents(new CAListener(this), this);

		log.info("ColoredAdmin enabled !");
	}
	

	@Override
	public void onDisable() {
		log.info("ColoredAdmin disabled !");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if((commandLabel.equalsIgnoreCase("ca")) && (args.length == 3)) {
			
			if((sender.hasPermission("coloredadmin.admin")) || (sender.isOp())) {
		
				if(args[0].equalsIgnoreCase("add")) {
					
					CAModifList addList = new CAModifList(args[1], args[2], sender);
					addList.add();
					loadList();
					
				return true;
				}
				
				else if(args[0].equalsIgnoreCase("remove")) {
						
					CAModifList removeList = new CAModifList(args[1], args[2], sender);
					removeList.remove();
					loadList();
					
				return true;
				}
				
				else if(args[0].equalsIgnoreCase("modifycolor")) {
					
					CAModifConfig modifConfig = new CAModifConfig(args[1], args[2], sender, this);
					modifConfig.ModifyColor();
					CAListener.loadConfig();

				return true;
				}

			 return false;
			}
			else {
				
				sender.sendMessage(ChatColor.RED+"Sorry, but you aren't allowed to use this command !");
				return false;
			}
			
			
		}
		
		else if((commandLabel.equalsIgnoreCase("ca")) && (args.length == 1) && (args[0].equalsIgnoreCase("help"))) {
			sender.sendMessage(ChatColor.GOLD +"---- "+ this +" help ----");
			sender.sendMessage(ChatColor.WHITE +"/ca add [admin/modo] [player] : command for adding a player to a group");
			sender.sendMessage(ChatColor.WHITE +"/ca remove [admin/modo] [player] : command for removing a player from a group");
			sender.sendMessage(ChatColor.WHITE +"/ca modifycolor [admin/modo] [color] : command for modifying the color of a group");
			
			return true;
		}
		
		return false;
	}
	
			    	
}
