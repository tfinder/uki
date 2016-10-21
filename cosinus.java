package cosan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Cosinus {

    private static Vector<Integer> tampung_kata1 = new Vector();
    private static Vector<Integer> tampung_kata2 = new Vector();
    private static Vector<String> kata2 = new Vector();
    private static Vector<String> gabungan_kata = new Vector();
    private static Vector<String> newkosa = new Vector();
    private static Vector<String> kata1 = new Vector();
    private static Vector<Integer> id_num = new Vector();
    private static Vector<Double> hasil = new Vector();
    private static Vector<String> kosa = new Vector();

    public static Connection conn;
    public static Statement cn;
    public static ResultSet rs;

    public static boolean cekKataawal(int n, String x) {
        boolean ketemu = false;

        for (int i = 0; i < n; i++) {
            // jika ada yg sama 
            if (kosa.get(i).equalsIgnoreCase(x)) {
                ketemu = true;
                break;
            }
        }
        return ketemu;
    }

    public static boolean cekKataawal2(int n, String x) {
        boolean ketemu = false;

        for (int i = 0; i < n; i++) {
            // jika ada yg sama 
            if (gabungan_kata.get(i).equalsIgnoreCase(x)) {
                ketemu = true;
                break;
            }
        }
        return ketemu;
    }

    public static void hasilcekkata(String teks) {
        String temp = "";
        boolean ketemu = false;
        int a, k, n, m, count = 0, jum;
        int s = 0;

        StringTokenizer st = new StringTokenizer(teks, " ");
        while (st.hasMoreElements()) {
            kosa.add(st.nextToken());
            s++;
        }
        n = s;
        k = 0;
        a = 0;
        for (int i = 0; i < n; i++) {
            if (!cekKataawal(a, kosa.get(i))) {
                newkosa.add(kosa.get(i));
                k++;
            }
            a++;
        }
        m = k; // banyaknya kata yg tlah dieliminasi  

        for (int i = 0; i < m; i++) {
            if (i == count) {
                break;
            }
            jum = 0;
            for (int j = 0; j < n; j++) {
                if (newkosa.get(i).equalsIgnoreCase(kosa.get(j))) {
                    jum += 1;
                }
            }

        }
        for (int i = 0; i < newkosa.size(); i++) {
            gabungan_kata.add(newkosa.get(i));
        }
    }

    public static void kata_pertama() {
        int as = kata1.size();
        int pasti = 0;
        for (int i = 0; i < kata1.size(); i++) {
            int jum = 0;
            for (int j = 0; j < gabungan_kata.size(); j++) {
                if (kata1.get(i).equalsIgnoreCase(gabungan_kata.get(j))) {
                    jum += 1;
                    pasti += 1;

                }
            }
            tampung_kata1.add(jum);
        }
        for (int i = pasti; i < gabungan_kata.size(); i++) {
            tampung_kata1.add(0);
        }

    }

    public static void kata_kedua() {
        int as = kata1.size();
        int pasti = 0;
        for (int i = 0; i < gabungan_kata.size(); i++) {
            int jum = 0;
            for (int j = 0; j < kata2.size(); j++) {
                if (gabungan_kata.get(i).equalsIgnoreCase(kata2.get(j))) {

                    jum += 1;
                    pasti += 1;

                } else {
                }
            }
            tampung_kata2.add(jum);
        }
    }

    public static void jumlah_akhir() {
        double pembilang = 0;
        double jumlah_sementara1 = 0;
        double jumlah_sementara2 = 0;
        for (int i = 0; i < tampung_kata1.size(); i++) {
            double terserah1 = tampung_kata1.get(i);
            double terserah2 = tampung_kata2.get(i);
            double jum = terserah1 * terserah2;
            pembilang = pembilang + jum;
            jum = 0;
            terserah1 = tampung_kata1.get(i) * tampung_kata2.get(i);
            terserah2 = tampung_kata1.get(i) * tampung_kata2.get(i);
            jumlah_sementara1 = jumlah_sementara1 + terserah1;
            jumlah_sementara2 = jumlah_sementara2 + terserah2;
            terserah1 = 0;
            terserah2 = 0;
            //System.out.println("1. "+terserah1+"\t2. "+terserah2);
        }

//        System.out.println("");
        jumlah_sementara1 = Math.sqrt(jumlah_sementara1);
        jumlah_sementara2 = Math.sqrt(jumlah_sementara2);

        double jum_total = jumlah_sementara1 * jumlah_sementara2;
        double hasila = pembilang / jum_total;
        hasil.add(hasila);
    }

    public static void awal(String teks, String teks2) {
        Vector<String> coba = new Vector();
        StringTokenizer ka = new StringTokenizer(teks2, " ");
        while (ka.hasMoreElements()) {
            kata2.add(ka.nextToken());
        }
        String teks3 = teks + " " + teks2;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan Kata yang anda inginkan: ");
        String teks = sc.nextLine();

        //String teks = "singa saya belajar program dengan bahasa java";
        StringTokenizer kata = new StringTokenizer(teks, " ");
        while (kata.hasMoreElements()) {
            kata1.add(kata.nextToken());
        }

        String SQL = "SELECT * FROM LINK ";
        ResultSet rs = database.executeQuery(SQL);

        try {
            while (rs.next()) {
                int ber = Integer.parseInt(rs.getString(1));
                id_num.add(ber);
                if (ber == 100) {
                    break;
                }
                String teks2 = rs.getString(5);
                awal(teks, teks2);

            }
        } catch (SQLException ex) {
            //Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String teks2 = "belajar bahasa inggris ";
        int data_akhir = hasil.size();
        int no = id_num.size();
        double[] data = new double[data_akhir];
        int[] link_id = new int[no];
        for (int i = 0; i < data.length; i++) {
            data[i] = hasil.get(i);
            link_id[i] = id_num.get(i);
        }

        for (int x = 0; x < data_akhir - 1; x++) {
            for (int i = 0; i < data_akhir - 1; i++) {
                if (data[i] < data[i + 1]) {
                    double j = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = j;
                    int g = link_id[i];
                    link_id[i] = link_id[i + 1];
                    link_id[i + 1] = g;
                }
            }
        }
        for (int i = 0; i < link_id.length; i++) {
            SQL = "SELECT * FROM LINK where ID_LINK='" + (link_id[i] + 1) + "';";
            //System.out.println(SQL);
            rs = database.executeQuery(SQL);
            try {
                while (rs.next()) {
                    System.out.println(rs.getString(2));

                }
            } catch (SQLException ex) {
                //Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
