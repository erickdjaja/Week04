import java.util.ArrayList;
import java.util.Scanner;

class Barang {
    private int id;
    private String nama;
    private int stock;
    private int harga;

    public Barang(int id, String nama, int stock, int harga) {
        this.id = id;
        this.nama = nama;
        this.stock = stock;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getStock() {
        return stock;
    }

    public int getHarga() {
        return harga;
    }

    public void minusStock(int qty) {
        this.stock -= qty;
    }

    public void showData() {
        System.out.println("ID    : " + id);
        System.out.println("Nama  : " + nama);
        System.out.println("Stock : " + stock);
        System.out.println("Harga : " + harga);
        System.out.println("---------------------------------");
    }
}

class Order {
    private int id;
    private int jumlah;
    private Barang barang;
    public static int total = 0;

    public Order(int id, Barang barang, int jumlah) {
        this.id = id;
        this.barang = barang;
        this.jumlah = jumlah;
        total += barang.getHarga() * jumlah;
    }

    public int getId() {
        return id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public Barang getBarang() {
        return barang;
    }
}

public class TokoMultiguna {
    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Order> daftarPesanan = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        int menu;

        while (true) {
            mainMenu();
            menu = in.nextInt();
            in.nextLine();

            switch (menu) {
                case 1:
                    pesanBarang();
                    break;
                case 2:
                    lihatPesanan();
                    break;
                case 0:
                    System.out.println("Terima kasih sudah berbelanja!");
                    return;
                default:
                    System.out.println("Input Tidak Valid");
            }
        }
    }

    static void seedData() {
        daftarBarang.add(new Barang(1, "Pulpen Easy Gel 0.5mm", 120, 2000));
        daftarBarang.add(new Barang(2, "Penggaris 30cm", 30, 5000));
        daftarBarang.add(new Barang(3, "Tipe-x Roller", 30, 7000));
        daftarBarang.add(new Barang(4, "Pensil Mekanik", 50, 5000));
        daftarBarang.add(new Barang(5, "Buku Tulis", 100, 6000));
    }

    static void mainMenu() {
        System.out.println("-----------Menu Toko Multiguna-----------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("0. Keluar");
        System.out.print("Pilihan : ");
    }

    static void pesanBarang() {
        System.out.println("Daftar Barang Toko Multiguna");
        for (Barang b : daftarBarang) {
            b.showData();
        }
        System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
        int id = in.nextInt();
        in.nextLine();
        if (id == 0) return;

        Barang pilih = null;
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                pilih = b;
                break;
            }
        }

        if (pilih == null) {
            System.out.println("ID Barang Tidak Sesuai Pilihan");
            return;
        }

        System.out.print("Masukkan Jumlah : ");
        int jumlah = in.nextInt();
        in.nextLine();

        if (jumlah <= 0 || jumlah > pilih.getStock()) {
            System.out.println("Jumlah Barang Tidak Sesuai Stock");
            return;
        }

        int totalHarga = jumlah * pilih.getHarga();
        System.out.println(jumlah + " @ " + pilih.getNama() + " dengan total harga " + totalHarga);
        System.out.print("Masukkan jumlah uang : ");
        int uang = in.nextInt();
        in.nextLine();

        if (uang < totalHarga) {
            System.out.println("Jumlah uang tidak mencukupi");
            return;
        }

        pilih.minusStock(jumlah);
        Order pesanan = new Order(daftarPesanan.size() + 1, pilih, jumlah);
        daftarPesanan.add(pesanan);
        System.out.println("Berhasil dipesan");
    }

    static void lihatPesanan() {
        System.out.println("Daftar Pesanan Toko Multiguna");
        for (Order o : daftarPesanan) {
            System.out.println("ID     : " + o.getId());
            System.out.println("Nama   : " + o.getBarang().getNama());
            System.out.println("Jumlah : " + o.getJumlah());
            System.out.println("Total  : " + (o.getJumlah() * o.getBarang().getHarga()));
            System.out.println("---------------------------------");
        }
    }
}
