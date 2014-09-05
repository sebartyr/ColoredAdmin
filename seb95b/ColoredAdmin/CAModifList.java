package seb95b.ColoredAdmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class CAModifList {
	
	private String fichier;
	private String player;
	private ArrayList<String> fileInModif = new ArrayList<String>();
	private CommandSender sender;
	
	public CAModifList(String file, String player, CommandSender sender) {
		if(file.equalsIgnoreCase("admin"))
			this.fichier = "admin.txt";
		else if(file.equalsIgnoreCase("modo")) 
			this.fichier = "modo.txt";
		
		if(player != null)
			this.player = player;
		
		this.sender = sender;
	}
	

	public void add() {
		try {
				if(this.fichier != null) {
				File file = new File("plugins/ColoredAdmin/" + this.fichier);
				FileWriter file2 = new FileWriter(file, true);
				BufferedWriter filew = new BufferedWriter(file2);
				filew.write(this.player);
				filew.write("\n");
				filew.close();
				this.sender.sendMessage("Player "+ ChatColor.DARK_RED + this.player + ChatColor.WHITE +" has been added to the "+ ChatColor.DARK_RED + this.fichier.replace(".txt", "").toLowerCase() + ChatColor.WHITE +" group !");
				}
			} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
	
	}
	
	public void remove() {
		
		try {
			if(this.fichier != null) {
				File f1 = new File("plugins/ColoredAdmin/"+ this.fichier); 
				FileReader f2 = new FileReader(f1);
				BufferedReader f3 = new BufferedReader(f2);
				String ligne;
				while ((ligne = f3.readLine()) != null) {
					if(!ligne.equalsIgnoreCase(this.player))
						this.fileInModif.add(ligne);
				}
				f3.close(); 
				
				try {
					File file = new File("plugins/ColoredAdmin/" + this.fichier);
					FileWriter file2 = new FileWriter(file);
					BufferedWriter filew = new BufferedWriter(file2);
					for(int i = 0; i < this.fileInModif.size(); i++) {
						filew.write(this.fileInModif.get(i));
						filew.write("\n");
					}
					filew.close();
					this.sender.sendMessage("Player "+ ChatColor.DARK_RED + this.player + ChatColor.WHITE +" has been removed from the "+ ChatColor.DARK_RED + this.fichier.replace(".txt", "").toLowerCase() + ChatColor.WHITE +" group !");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

}
