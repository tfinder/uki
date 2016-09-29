import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Whitelist;

public class Search {

    public static void main(String[] args) throws IOException {
//        Connection con = null;
//        PreparedStatement pst = null;
//        PreparedStatement pst1 = null;
//        Statement st1 = null;
//        String url = "jdbc:mysql://localhost:3306/search?autoReconnect=true&useSSL=false";
//        String user = "root";
//        String password = "123";
        Document doc = Jsoup.connect("http://www.uinsby.ac.id/").timeout(0).get();
        Elements links = doc.select("body");
        String text = links.text();
        String b = "";
        b = text.replaceAll("dan", "").replaceAll("tapi", "").replaceAll("[&,|]", "").replaceAll("dengan", "").replaceAll("yang", "");
        String[] kata = b.split(" ");
        ArrayList hasil = new ArrayList();
        for (int i = 0; i < kata.length; i++) {
            if (!kata[i].equals("")) {
                hasil.add(kata[i]);
            }
        }

        ArrayList hitung = new ArrayList();
        for (int i = 0; i < hasil.size(); i++) {
            hitung.add(hasil.get(i));
        }

        ArrayList angka = new ArrayList();
        Hashtable data = new Hashtable();
        Enumeration out;
        String name = "";
        int par = 0;

        for (int i = 0; i < hasil.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (hasil.get(i).equals(hasil.get(j))) {
                    hitung.remove(hasil.get(j));
                    break;
                } else {

                }
            }
        }

        for (Object key : hitung) {
            if (null == key) {
                break;
            }
            for (String s : kata) {
                if (key.equals(s)) {
                    par++;
                }
            }
            angka.add(par);
            par = 0;
        }

        for (int i = 0; i < hitung.size(); i++) {
            data.put(hitung.get(i), angka.get(i));
        }
        out = data.keys();
        while (out.hasMoreElements()) {
            name = (String) out.nextElement();
            System.out.println(name + ":" + data.get(name));
        }
    }
}
