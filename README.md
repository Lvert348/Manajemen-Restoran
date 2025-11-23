# 🍽️ Sistem Manajemen Restoran

Tugas Praktik 3 - Pemrograman Berbasis Objek

## 📋 Deskripsi

Program manajemen restoran berbasis Java yang mengimplementasikan konsep-konsep Object-Oriented Programming (OOP) lengkap dengan fitur manajemen menu, pemesanan, dan struk pembayaran.

## ✨ Fitur

- ✅ Tambah menu (Makanan, Minuman, Diskon)
- ✅ Tampilkan daftar menu
- ✅ Terima pesanan pelanggan
- ✅ Hitung total dengan diskon otomatis
- ✅ Cetak struk pesanan
- ✅ Simpan & muat data dari file
- ✅ Exception handling

## 🎯 Konsep OOP yang Diimplementasikan

### 1. **Abstraksi**
- Kelas abstrak `MenuItem` sebagai blueprint
- Method abstrak `tampilMenu()` dan `toFileString()`

### 2. **Inheritance (Pewarisan)**
- `Makanan` extends `MenuItem`
- `Minuman` extends `MenuItem`
- `Diskon` extends `MenuItem`

### 3. **Encapsulation**
- Semua atribut menggunakan access modifier `private`
- Getter dan Setter untuk akses data

### 4. **Polymorphism**
- Override method `tampilMenu()` di setiap subclass
- Implementasi berbeda sesuai tipe objek

### 5. **Exception Handling**
- Try-catch untuk input validation
- Custom exception untuk item tidak ditemukan
- IOException handling untuk operasi file

### 6. **Collection (ArrayList)**
- `ArrayList<MenuItem>` untuk menyimpan menu
- `ArrayList<MenuItem>` untuk menyimpan pesanan

## 🚀 Cara Menjalankan

### Prerequisites
- Java JDK 8 atau lebih tinggi
- Terminal/Command Prompt

### Langkah-langkah

1. **Clone repository**
   ```bash
   git clone https://github.com/USERNAME/tugas-praktik-3-pbo.git
   cd tugas-praktik-3-pbo
   ```

2. **Compile program**
   ```bash
   javac ManajemenRestoran.java
   ```

3. **Jalankan program**
   ```bash
   java ManajemenRestoran
   ```

## 📖 Cara Penggunaan

### Menu Utama
```
========================================
   SISTEM MANAJEMEN RESTORAN
========================================
1. Tambah Item Baru ke Menu
2. Tampilkan Menu Restoran
3. Terima Pesanan Pelanggan
4. Hitung Total Pesanan
5. Tampilkan & Simpan Struk
6. Simpan Menu ke File
7. Keluar
========================================
```

### Contoh Penggunaan

**1. Tambah Menu Makanan:**
- Pilih menu 1
- Pilih jenis: 1 (Makanan)
- Input nama: `Nasi Goreng`
- Input harga: `25000`
- Input jenis: `Nasi`

**2. Tambah Diskon:**
- Pilih menu 1
- Pilih jenis: 3 (Diskon)
- Input nama: `Diskon Weekend`
- Input persentase: `10`

**3. Buat Pesanan:**
- Pilih menu 3
- Pilih nomor item dari daftar
- Input jumlah
- Tambahkan diskon jika ada
- Pilih 0 untuk selesai

**4. Lihat Struk:**
- Pilih menu 5
- Struk otomatis tersimpan di `struk.txt`

## 📁 Struktur File

```
.
├── ManajemenRestoran.java    # File utama program
├── menu.txt                   # File penyimpanan menu (auto-generated)
├── struk.txt                  # File penyimpanan struk (auto-generated)
└── README.md                  # Dokumentasi
```

## 🔧 Struktur Kelas

```
MenuItem (Abstract)
├── Makanan
├── Minuman
└── Diskon

Menu
└── ArrayList<MenuItem>

Pesanan
├── ArrayList<MenuItem>
└── ArrayList<Integer>

ManajemenRestoran (Main)
```

## 💾 Format File

### menu.txt
```
MAKANAN,Nasi Goreng,25000.0,Nasi
MINUMAN,Es Teh,5000.0,Dingin
DISKON,Diskon Weekend,10.0
```

### struk.txt
```
========== STRUK PESANAN ==========
Tanggal: Sun Nov 23 10:30:45 WIB 2025
===================================
Nasi Goreng         x2
  @Rp 25000.00 = Rp 50000.00
Es Teh              x1
  @Rp 5000.00 = Rp 5000.00
-----------------------------------
Subtotal: Rp 55000.00
Diskon Weekend (10%): -Rp 5500.00
===================================
TOTAL: Rp 49500.00
===================================
```

## 🎓 Indikator Penilaian

- ✅ Implementasi abstraksi dan inheritance
- ✅ Implementasi encapsulation dan polymorphism
- ✅ Exception handling
- ✅ Operasi I/O dan File
- ✅ Struktur keputusan dan pengulangan
- ✅ Array dan String
- ✅ Collection (ArrayList)

## 👨‍💻 Author

**[Nama Lengkap]**
- NIM: [NIM Anda]
- Kelas: [Kelas Anda]
- Mata Kuliah: Pemrograman Berbasis Objek

## 📝 Lisensi

Tugas Praktik 3 - Untuk keperluan akademik

## 🙏 Catatan

Program ini dibuat untuk memenuhi Tugas Praktik 3 mata kuliah Pemrograman Berbasis Objek. Semua konsep OOP telah diimplementasikan sesuai dengan requirement yang diminta.

---

**⭐ Jika repository ini membantu, jangan lupa kasih star ya!**
