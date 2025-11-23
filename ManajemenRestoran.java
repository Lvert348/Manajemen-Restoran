import java.util.*;
import java.io.*;

// 1. Kelas abstrak MenuItem sebagai kelas dasar
abstract class MenuItem {
    private String nama;
    private double harga;
    private String kategori;
    
    // Constructor
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
    
    // Getter dan Setter (Encapsulation)
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public String getKategori() {
        return kategori;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    // Metode abstrak untuk polymorphism
    public abstract void tampilMenu();
    
    // Metode untuk menyimpan ke file
    public abstract String toFileString();
}

// 2. Kelas turunan Makanan
class Makanan extends MenuItem {
    private String jenisMakanan;
    
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }
    
    public String getJenisMakanan() {
        return jenisMakanan;
    }
    
    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }
    
    @Override
    public void tampilMenu() {
        System.out.printf("%-20s | Rp %-10.2f | Jenis: %-15s | Kategori: %s\n", 
                         getNama(), getHarga(), jenisMakanan, getKategori());
    }
    
    @Override
    public String toFileString() {
        return "MAKANAN," + getNama() + "," + getHarga() + "," + jenisMakanan;
    }
}

// 3. Kelas turunan Minuman
class Minuman extends MenuItem {
    private String jenisMinuman;
    
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }
    
    public String getJenisMinuman() {
        return jenisMinuman;
    }
    
    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }
    
    @Override
    public void tampilMenu() {
        System.out.printf("%-20s | Rp %-10.2f | Jenis: %-15s | Kategori: %s\n", 
                         getNama(), getHarga(), jenisMinuman, getKategori());
    }
    
    @Override
    public String toFileString() {
        return "MINUMAN," + getNama() + "," + getHarga() + "," + jenisMinuman;
    }
}

// 4. Kelas turunan Diskon
class Diskon extends MenuItem {
    private double persenDiskon;
    
    public Diskon(String nama, double persenDiskon) {
        super(nama, 0, "Diskon");
        this.persenDiskon = persenDiskon;
    }
    
    public double getPersenDiskon() {
        return persenDiskon;
    }
    
    public void setPersenDiskon(double persenDiskon) {
        this.persenDiskon = persenDiskon;
    }
    
    @Override
    public void tampilMenu() {
        System.out.printf("%-20s | Diskon: %.0f%% | Kategori: %s\n", 
                         getNama(), persenDiskon, getKategori());
    }
    
    @Override
    public String toFileString() {
        return "DISKON," + getNama() + "," + persenDiskon;
    }
}

// 5. Kelas Menu untuk mengelola semua item
class Menu {
    private ArrayList<MenuItem> daftarMenu;
    
    public Menu() {
        daftarMenu = new ArrayList<>();
    }
    
    // Menambah item ke menu
    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        System.out.println("Item berhasil ditambahkan ke menu!");
    }
    
    // Menampilkan semua menu
    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong!");
            return;
        }
        
        System.out.println("\n========== DAFTAR MENU RESTORAN ==========");
        System.out.println("===========================================");
        
        // Tampilkan Makanan
        System.out.println("MAKANAN:");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i) instanceof Makanan) {
                System.out.print((i + 1) + ". ");
                daftarMenu.get(i).tampilMenu();
            }
        }
        
        // Tampilkan Minuman
        System.out.println("\nMINUMAN:");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i) instanceof Minuman) {
                System.out.print((i + 1) + ". ");
                daftarMenu.get(i).tampilMenu();
            }
        }
        
        // Tampilkan Diskon
        System.out.println("\nDISKON TERSEDIA:");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i) instanceof Diskon) {
                System.out.print((i + 1) + ". ");
                daftarMenu.get(i).tampilMenu();
            }
        }
        
        System.out.println("===========================================\n");
    }
    
    // Mendapatkan item berdasarkan index
    public MenuItem getItem(int index) throws Exception {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new Exception("Item tidak ditemukan dalam menu!");
        }
        return daftarMenu.get(index);
    }
    
    public int getJumlahItem() {
        return daftarMenu.size();
    }
    
    // Simpan menu ke file
    public void simpanKeFile(String namaFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenu) {
                writer.println(item.toFileString());
            }
            System.out.println("Menu berhasil disimpan ke file: " + namaFile);
        } catch (IOException e) {
            System.out.println("Error saat menyimpan menu: " + e.getMessage());
        }
    }
    
    // Muat menu dari file
    public void muatDariFile(String namaFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            daftarMenu.clear();
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                
                if (data[0].equals("MAKANAN")) {
                    daftarMenu.add(new Makanan(data[1], Double.parseDouble(data[2]), data[3]));
                } else if (data[0].equals("MINUMAN")) {
                    daftarMenu.add(new Minuman(data[1], Double.parseDouble(data[2]), data[3]));
                } else if (data[0].equals("DISKON")) {
                    daftarMenu.add(new Diskon(data[1], Double.parseDouble(data[2])));
                }
            }
            System.out.println("Menu berhasil dimuat dari file: " + namaFile);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan. Membuat menu baru.");
        } catch (IOException e) {
            System.out.println("Error saat memuat menu: " + e.getMessage());
        }
    }
}

// 6. Kelas Pesanan
class Pesanan {
    private ArrayList<MenuItem> itemPesanan;
    private ArrayList<Integer> jumlahItem;
    private Diskon diskonDipakai;
    
    public Pesanan() {
        itemPesanan = new ArrayList<>();
        jumlahItem = new ArrayList<>();
        diskonDipakai = null;
    }
    
    // Tambah item ke pesanan
    public void tambahItem(MenuItem item, int jumlah) {
        if (item instanceof Diskon) {
            diskonDipakai = (Diskon) item;
            System.out.println("Diskon berhasil diterapkan!");
        } else {
            itemPesanan.add(item);
            jumlahItem.add(jumlah);
            System.out.println("Item berhasil ditambahkan ke pesanan!");
        }
    }
    
    // Hitung total pesanan
    public double hitungTotal() {
        double total = 0;
        
        for (int i = 0; i < itemPesanan.size(); i++) {
            total += itemPesanan.get(i).getHarga() * jumlahItem.get(i);
        }
        
        // Terapkan diskon jika ada
        if (diskonDipakai != null) {
            total -= (total * diskonDipakai.getPersenDiskon() / 100);
        }
        
        return total;
    }
    
    // Tampilkan struk
    public void tampilkanStruk() {
        if (itemPesanan.isEmpty()) {
            System.out.println("Pesanan kosong!");
            return;
        }
        
        System.out.println("\n========== STRUK PESANAN ==========");
        System.out.println("===================================");
        
        double subtotal = 0;
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int qty = jumlahItem.get(i);
            double hargaTotal = item.getHarga() * qty;
            subtotal += hargaTotal;
            
            System.out.printf("%-20s x%d\n", item.getNama(), qty);
            System.out.printf("  @Rp %.2f = Rp %.2f\n", item.getHarga(), hargaTotal);
        }
        
        System.out.println("-----------------------------------");
        System.out.printf("Subtotal: Rp %.2f\n", subtotal);
        
        if (diskonDipakai != null) {
            double potongan = subtotal * diskonDipakai.getPersenDiskon() / 100;
            System.out.printf("Diskon %s (%.0f%%): -Rp %.2f\n", 
                            diskonDipakai.getNama(), 
                            diskonDipakai.getPersenDiskon(), 
                            potongan);
        }
        
        System.out.println("===================================");
        System.out.printf("TOTAL: Rp %.2f\n", hitungTotal());
        System.out.println("===================================\n");
    }
    
    // Simpan struk ke file
    public void simpanStrukKeFile(String namaFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile, true))) {
            writer.println("\n========== STRUK PESANAN ==========");
            writer.println("Tanggal: " + new Date());
            writer.println("===================================");
            
            double subtotal = 0;
            for (int i = 0; i < itemPesanan.size(); i++) {
                MenuItem item = itemPesanan.get(i);
                int qty = jumlahItem.get(i);
                double hargaTotal = item.getHarga() * qty;
                subtotal += hargaTotal;
                
                writer.printf("%-20s x%d\n", item.getNama(), qty);
                writer.printf("  @Rp %.2f = Rp %.2f\n", item.getHarga(), hargaTotal);
            }
            
            writer.println("-----------------------------------");
            writer.printf("Subtotal: Rp %.2f\n", subtotal);
            
            if (diskonDipakai != null) {
                double potongan = subtotal * diskonDipakai.getPersenDiskon() / 100;
                writer.printf("Diskon %s (%.0f%%): -Rp %.2f\n", 
                            diskonDipakai.getNama(), 
                            diskonDipakai.getPersenDiskon(), 
                            potongan);
            }
            
            writer.println("===================================");
            writer.printf("TOTAL: Rp %.2f\n", hitungTotal());
            writer.println("===================================\n");
            
            System.out.println("Struk berhasil disimpan ke file: " + namaFile);
        } catch (IOException e) {
            System.out.println("Error saat menyimpan struk: " + e.getMessage());
        }
    }
    
    public boolean isEmpty() {
        return itemPesanan.isEmpty();
    }
    
    public void reset() {
        itemPesanan.clear();
        jumlahItem.clear();
        diskonDipakai = null;
    }
}

// 7. Kelas Utama
public class ManajemenRestoran {
    private static Menu menu = new Menu();
    private static Pesanan pesananSekarang = new Pesanan();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Muat menu dari file jika ada
        menu.muatDariFile("menu.txt");
        
        boolean running = true;
        
        while (running) {
            tampilkanMenuUtama();
            
            try {
                System.out.print("Pilih menu: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (pilihan) {
                    case 1:
                        tambahItemBaru();
                        break;
                    case 2:
                        menu.tampilkanSemuaMenu();
                        break;
                    case 3:
                        terimaPesanan();
                        break;
                    case 4:
                        hitungTotalPesanan();
                        break;
                    case 5:
                        tampilkanStruk();
                        break;
                    case 6:
                        menu.simpanKeFile("menu.txt");
                        break;
                    case 7:
                        running = false;
                        System.out.println("Terima kasih telah menggunakan sistem manajemen restoran!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine(); // clear buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static void tampilkanMenuUtama() {
        System.out.println("========================================");
        System.out.println("   SISTEM MANAJEMEN RESTORAN");
        System.out.println("========================================");
        System.out.println("1. Tambah Item Baru ke Menu");
        System.out.println("2. Tampilkan Menu Restoran");
        System.out.println("3. Terima Pesanan Pelanggan");
        System.out.println("4. Hitung Total Pesanan");
        System.out.println("5. Tampilkan & Simpan Struk");
        System.out.println("6. Simpan Menu ke File");
        System.out.println("7. Keluar");
        System.out.println("========================================");
    }
    
    private static void tambahItemBaru() {
        System.out.println("\n=== TAMBAH ITEM BARU ===");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon");
        System.out.print("Pilih jenis item: ");
        
        int jenis = scanner.nextInt();
        scanner.nextLine();
        
        try {
            if (jenis == 1) {
                System.out.print("Nama makanan: ");
                String nama = scanner.nextLine();
                System.out.print("Harga: ");
                double harga = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Jenis makanan (contoh: Nasi, Mie, Ayam): ");
                String jenisMakanan = scanner.nextLine();
                
                menu.tambahItem(new Makanan(nama, harga, jenisMakanan));
                
            } else if (jenis == 2) {
                System.out.print("Nama minuman: ");
                String nama = scanner.nextLine();
                System.out.print("Harga: ");
                double harga = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Jenis minuman (contoh: Dingin, Panas, Soda): ");
                String jenisMinuman = scanner.nextLine();
                
                menu.tambahItem(new Minuman(nama, harga, jenisMinuman));
                
            } else if (jenis == 3) {
                System.out.print("Nama diskon: ");
                String nama = scanner.nextLine();
                System.out.print("Persentase diskon (%): ");
                double persen = scanner.nextDouble();
                scanner.nextLine();
                
                menu.tambahItem(new Diskon(nama, persen));
                
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid!");
            scanner.nextLine();
        }
    }
    
    private static void terimaPesanan() {
        System.out.println("\n=== TERIMA PESANAN ===");
        menu.tampilkanSemuaMenu();
        
        if (menu.getJumlahItem() == 0) {
            return;
        }
        
        try {
            System.out.print("Pilih nomor item (0 untuk selesai): ");
            int pilihan = scanner.nextInt();
            
            if (pilihan == 0) {
                return;
            }
            
            MenuItem item = menu.getItem(pilihan - 1);
            
            if (item instanceof Diskon) {
                pesananSekarang.tambahItem(item, 1);
            } else {
                System.out.print("Jumlah: ");
                int jumlah = scanner.nextInt();
                pesananSekarang.tambahItem(item, jumlah);
            }
            
            System.out.print("Tambah item lagi? (y/n): ");
            scanner.nextLine();
            String lagi = scanner.nextLine();
            
            if (lagi.equalsIgnoreCase("y")) {
                terimaPesanan();
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    private static void hitungTotalPesanan() {
        if (pesananSekarang.isEmpty()) {
            System.out.println("Belum ada pesanan!");
            return;
        }
        
        System.out.println("\n=== TOTAL PESANAN ===");
        System.out.printf("Total yang harus dibayar: Rp %.2f\n", pesananSekarang.hitungTotal());
    }
    
    private static void tampilkanStruk() {
        if (pesananSekarang.isEmpty()) {
            System.out.println("Belum ada pesanan!");
            return;
        }
        
        pesananSekarang.tampilkanStruk();
        pesananSekarang.simpanStrukKeFile("struk.txt");
        
        System.out.print("Mulai pesanan baru? (y/n): ");
        String jawab = scanner.nextLine();
        
        if (jawab.equalsIgnoreCase("y")) {
            pesananSekarang.reset();
            System.out.println("Pesanan direset. Siap menerima pesanan baru!");
        }
    }
}