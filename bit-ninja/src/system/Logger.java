package system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class Logger {
	public static boolean log(String ipAddress, String content){
		Date now = new Date();
		try{
			File log = new File(BitSystem.getLogPath()
					+ now.toString().substring(0, 10).replace(" ", "-")
					+ ".txt");
			if(!log.exists()){
				log.createNewFile();
			}
			FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("["
					+ now.toString().replace(" ", "-")
					+ "] : "
					+"["
					+ ipAddress
					+ "] : "
					+ content);
			pw.close();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
