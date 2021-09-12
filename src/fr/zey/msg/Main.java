package fr.zey.msg;


import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	
	public HashMap<String, String> map = new HashMap<>();

	
	

	@Override
	public void onEnable() {
		getCommand("msg").setExecutor(new CommandMsg(this));
		getCommand("r").setExecutor(new CommandMsg(this));
		getCommand("reply").setExecutor(new CommandMsg(this));
	}
	
	
	@Override
	public void onDisable() {
	}

}
