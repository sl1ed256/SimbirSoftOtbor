import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        FileHandler fh = null;   // true forces append mode
        try {
            fh = new FileHandler("log.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "Exception: ", e);
        }
        SimpleFormatter sf = new SimpleFormatter();
        fh.setFormatter(sf);
        log.addHandler(fh);

        log.log(Level.INFO, "Program has started");


        System.out.println("Enter web-site:");
        Scanner in = new Scanner(System.in);
        String url = in.nextLine();
        log.log(Level.INFO, "Site read");
        System.out.println("Enter path (where save html document):");
        String outputDirPath = in.nextLine();
        log.log(Level.INFO, "Path read");
        DownloadPage downloader = new DownloadPage(fh);
        try {
            downloader.getURL(url, outputDirPath);
            log.log(Level.INFO, "Page downloaded");
        } catch (Exception e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "Exception: ", e);
        }

        WordScanner wordStatistics = new WordScanner(fh);
        try {
            wordStatistics.getStatistics(url);
            log.log(Level.INFO, "Statistics received");
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
