package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class getContent {

    public static void main(String[] args) throws IOException {
        Connection con = null;
        PreparedStatement pst = null;
       // Statement st1 = null;
        String url = "jdbc:mysql://localhost:3306/search?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "123";
        int i = 1;
        Document doc;
        Document doc1;
        try {
            con = DriverManager.getConnection(url, user, password);
            // need http protocol
            doc = Jsoup.connect("http://www.uinsby.ac.id").timeout(0).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                String linknya = link.attr("href");
                String textnya = link.text();
                System.out.println("=======================================================");
                System.out.println(linknya);
                System.out.println("=======================================================");
                doc1 = Jsoup.connect(linknya).timeout(0).get();
                Elements links1 = doc1.select("a[href]");
                for (Element link1 : links1) {
                    String linknya1 = link1.attr("href");
                    String textnya1 = link1.text();
                    Elements body = doc1.select("body");
                    String content = body.text();
                    String contentnya = content.replaceAll("'", "");
                    if (linknya1.length()>0) {
                        pst = con.prepareStatement("INSERT INTO LINK (NAMA_LINK,TEXT_LINK,ISI_CONTENT) VALUES ('" + linknya1 + "','" + textnya1 + "','" + contentnya + "');");
                        pst.executeUpdate();
                    }                 
                    System.out.println("\nlink : " + linknya1);
                    //System.out.println(content);
                    System.out.println("text : " + textnya1);
                }

            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(getContent.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(getContent.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

    }
}
