package myLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    public static void addLog(String log) {
        SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
        String data = format.format(new Date());


        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter("D://logi.txt",true));
            writer.write(data+ " " +log+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }


    }

}
