import java.util.*;

public class CommandHandler {
	
	public static String[] breakCommand(String cmd) {
		ArrayList<String> cmdArrayList = new ArrayList<String>(); //  creates an array to fill
		int lastIndex = 0;
		for (int i = 0; i < cmd.length(); i++) {
			if(cmd.charAt(i) == 32) {
				cmdArrayList.add(cmd.substring(lastIndex, i)); // adds the thing...
				lastIndex = i+1;
			}
		}
		cmdArrayList.add(cmd.substring(lastIndex, cmd.length()));
		String[] cmdArray = new String[cmdArrayList.size()];
		int ph = 0;
		for (String s:cmdArrayList) {
			cmdArray[ph] = s;
			ph++;
		}
		return cmdArray;
	}
	
	public static void execute(String[] args) { // handles the command issued to the server
		if (args.length >= 1) {
			if (args.length == 1) tryOneArgCommands(args);
			else if (args.length == 2) tryTwoArgCommands(args);
		}
	}
	
	// COMMAND ARGUMENT CHANNELER
	private static void tryOneArgCommands(String[] args) {
		if (args[0].equals("volume_up")) {
			volume_up();
			return;
		}
		if (args[0].equals("volume_down")) {
			volume_down();
			return;
		}
	}
	private static void tryTwoArgCommands(String[] args) {
		if (args[0].equals("volume")) {
			if (args[1].equals("up")) {
				volume_up();
				return;
			}
			if (args[1].equals("down")) {
				volume_down();
				return;
			}
		}
	}
	
	// FUNCTION CONTROLS
	public static void volume_up() {
		System.out.println("Increasing volume level...");
	}
	public static void volume_down() {
		System.out.println("Decreasing volume level...");
	}
}