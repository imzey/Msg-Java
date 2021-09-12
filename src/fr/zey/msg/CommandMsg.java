package fr.zey.msg;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMsg implements CommandExecutor {
	Main main;
	public CommandMsg(Main main) {
		this.main = main;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("msg")) {
			if(args.length >= 2) {
				Player t = Bukkit.getPlayer(args[0]);
				if(t != null) {
					StringBuilder sb = new StringBuilder();
					for(String arg : args ) {
						sb.append(arg+" ");
					}
					String msg;
					msg = sb.substring(t.getName().length());
					t.sendMessage("Message reçu de "+sender.getName()+"§7: §d"+msg);
					sender.sendMessage("§aMessage envoyé à "+t.getName() + msg);
					if(main.map.containsKey(t.getName()) && !main.map.get(t.getName()).equalsIgnoreCase(sender.getName())) {
						main.map.remove(t.getName());
					}
						main.map.put(t.getName(), sender.getName());
						if(main.map.containsKey(t.getName())) {
							main.map.remove(sender.getName());
					}
					main.map.put(sender.getName(), t.getName());
				}else {
					sender.sendMessage("Ah mince le joueur n'est pas connecté");
				}
			}else {
				sender.sendMessage("/msg <joueur> <msg>");
			}
		}else {
			if(args.length >= 1) {
				if(main.map.containsKey(sender.getName())) {
					StringBuilder sb = new StringBuilder();
					for(String arg : args ) {
						sb.append(arg+" ");
					}
					if(main.map.get(sender.getName()).equals("CONSOLE")) {
						System.out.println("Message reçu de "+sender.getName()+"§7: §d"+sb.toString());
						sender.sendMessage("§aMessage envoyé à la console"+sb.toString());
						if(main.map.containsKey("CONSOLE") && !main.map.get("CONSOLE").equalsIgnoreCase(sender.getName())) {
							main.map.remove("CONSOLE", sender.getName());
						}
						if(main.map.containsKey("CONSOLE")) {
							main.map.put("CONSOLE", sender.getName());
						}
					}else {
						Player t = Bukkit.getPlayer(main.map.get(sender.getName()));
						if(t !=null) {
							t.sendMessage("Message reçu de "+sender.getName()+"§7: §d"+sb.toString());
							sender.sendMessage("§aMessage envoyé à "+t.getName() + sb.toString());
							if(main.map.containsKey(t.getName()) && !main.map.get(t.getName()).equalsIgnoreCase(sender.getName())) {
								main.map.remove(t.getName(), sender.getName());
							}
							if(main.map.containsKey(t.getName())) {
								main.map.put(t.getName(), sender.getName());
							}
						}else {
							sender.sendMessage("§cZut, le joueur est deconnecté ");
						}
						
					}
				}else {
					sender.sendMessage("Tu n'as personne a qui répondre");
				}
			}else {
				sender.sendMessage("Tu dois spécifier un msg");
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
