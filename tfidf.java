package tf;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Tf {

    public static void main(String[] args) throws IOException {
        ArrayList<Double> angka = new ArrayList<Double>();
        ArrayList<Double> tfnya = new ArrayList();
        ArrayList hasil = new ArrayList();
        ArrayList hitung = new ArrayList();
        ArrayList word = new ArrayList();
        ArrayList word1 = new ArrayList();
        ArrayList tampungSplit = new ArrayList();
        int z;
        for (z = 0; z <= 1; z++) {
            ArrayList<String> tampung = new ArrayList();
            String b = "";
            String[] count = null;
            if (z == 0) {
                b = "saya adalah anak sistem informasi";
                tampung.add(b);
                count = b.split(" ");
            } else {
                b = "saya adalah anak sistem informasi";
                tampung.add(b);
                count = b.split(" ");
            }

            String[] kata = null;

            //b = text.replaceAll("dan", "").replaceAll("tapi", "").replaceAll("[&,|]", "").replaceAll("dengan", "").replaceAll("yang", "");
            for (int i = 0; i < tampung.size(); i++) {
                kata = tampung.get(i).split(" ");
                for (int j = 0; j < kata.length; j++) {
                    tampungSplit.add(kata[j]);
                }
            }

            for (int i = 0; i < tampungSplit.size(); i++) {

                if (!tampungSplit.get(i).equals("")) {
                    hasil.add(tampungSplit.get(i));
                    hitung.add(tampungSplit.get(i));
                }
            }

            for (int i = 0; i < hasil.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (hasil.get(i).equals(hasil.get(j))) {
                        hitung.remove(hasil.get(j));
                        break;
                    } else {

                    }
                }
            }

            double par = 0;
            double h1 = z + 1;
            //angka.add(h1);
            for (Object key : hitung) {
                if (null == key) {
                    break;
                }
                for (String s : count) {
                    if (key.equals(s)) {
                        par++;
                    }
                }
                angka.add(par);
                par = 0;
            }

            Hashtable<Object, Double> data = new Hashtable<Object, Double>();
            Enumeration out;
            String name = "";
            out = data.keys();
            double tf = 0;

            for (int i = 0; i < hitung.size(); i++) {
                tf = angka.get(i) / tampungSplit.size();
                tfnya.add(tf);
                word.add(hitung.get(i));
                word1.add(hitung.get(i));
            }
            hitung.clear();
            hasil.clear();
        }

        for (int i = 0; i < word.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (word.get(i).equals(word.get(j))) {
                    word1.remove(word.get(j));
                    break;
                } else {

                }
            }
            //System.out.println(word.get(i)+" = "+angka.get(i) +" = "+tfnya.get(i));
        }
        ArrayList<Double> angkaIdf = new ArrayList();
        double par = 0;

        //angka.add(h1);
        for (Object key : word1) {
            if (null == key) {
                break;
            }
            for (Object s : tampungSplit) {
                if (key.equals(s)) {
                    par++;
                }
            }
            angkaIdf.add(par);
            par = 0;
        }

        double log = 0;
        double bagi = 0;
        ArrayList<Double> lognya = new ArrayList<Double>();
        for (int i = 0; i < word1.size(); i++) {
            bagi = angkaIdf.get(i) / z;
            log = Math.log(bagi);
            lognya.add(log);
            //System.out.println(word1.get(i)+"="+angkaIdf.get(i) );
        }

        double tfidf = 0;
        System.out.println("=====================TDIDF=====================");
        for (int i = 0; i < word1.size(); i++) {
            tfidf = tfnya.get(i) * lognya.get(i);
            System.out.println(word1.get(i) + "\t=\t" + tfidf);
        }
        
        
    }
}
