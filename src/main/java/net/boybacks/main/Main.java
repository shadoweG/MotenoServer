package net.boybacks.main;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class Main {

    static File quotes = new File("quotes.txt");
    static List<String> quotesList = new ArrayList<String>();
    static int quoteCount = 0;

    public static void main(String[] args) throws IOException {
        saveQuoteCount();
        quoteManager();
        quoteReset();
    }


    public static void quoteManager() throws IOException {
        FileInputStream fstream_school = new FileInputStream(quotes);
        DataInputStream data_input = new DataInputStream(fstream_school);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));

        String str_line;

        while ((str_line = buffer.readLine()) != null) {
            str_line = str_line.trim();
            if ((str_line.length() != 0)) {
                quotesList.add(str_line);
            }
        }
    }

    static Properties prop = new Properties();

    public static void saveQuoteCount() {
        File f = new File("config.properties");
        if(!f.exists() && !f.isDirectory()) {
            System.out.println("[Test Log (File Creation)] File is already been created!");
            saveManager();
        }
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
        } catch (FileNotFoundException e) {
            System.out.println("[Test Log (File Read)] Ooooooooooops Error there is no such file config.properties...");
            e.printStackTrace();
        }
        try {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("[Test Log (File Read)] Ooooooooooops Error with input...");
            e.printStackTrace();
        }
        quoteCount = Integer.parseInt((String) properties.get("quoteCount"));
    }

    public static void JSONQuoteSaver() {
        String quote = quotesList.get(quoteCount);
        String[] text = quote.split(" = ");
        String path = "quote.json";

        JSONObject json = new JSONObject();
        json.put("quote", text[0]);
        json.put("author", text[1]);

        try (FileWriter file = new FileWriter(path)) {
            file.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(text[0]);
        System.out.println(text[1]);
    }

    public static void saveManager() {
        try {
            prop.setProperty("quoteCount", String.valueOf(quoteCount));

            prop.store(new FileOutputStream("config.properties"), null);
            System.out.println("[Test Log (File Creation)] Quotes score has been saved!");

        } catch (IOException ex) {
            System.out.println("[Test Log (File Creation)] Ooooooooooops Error...");
            ex.printStackTrace();
        }
    }

    public static void quoteReset() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                //int second = calendar.get(Calendar.SECOND);
                if (hour == 0 && minute == 1) {
                    System.out.println("Reset dziennego cytatu!");
                    JSONQuoteSaver();
                    quoteCount++;
                    saveManager();
                }
                else {
                    System.out.println("Nie ma odpowiedniego czasu!");
                }
            }
        };

        Timer timer = new Timer();

        timer.schedule(task, 1000, 60*1000);

    }
}
