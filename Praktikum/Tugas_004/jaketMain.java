package Tugas_004;
import java.util.ArrayList;
import java.util.Scanner;

public class jaketMain {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int pilihan, i = -1;
        ArrayList<Integer> jml = new ArrayList<>();
        ArrayList<Character> pilihBahan = new ArrayList<>();
        ArrayList<Integer> hargaJaket = new ArrayList<>();
        jaket jkt = new jaket();
        do {
            System.out.println("\nMenu Program: ");
            System.out.println("1. Lihat Jenis Jaket & Harganya\n2. Pembelian Jaket\n3. Lihat detail pembelian\n4. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch(pilihan){
                case 1:{
                    char type = 'A';
                    while(type <= jkt.MAX_JACKET_TYPE){
                        System.out.printf("%s %-9c: Rp %,3d.00/buah\n", "Tipe Bahan", type, jkt.getHargaJaket(type)[0]);
                        type++;
                    }
                    System.out.println("INFO: Setiap pembelian lebih dari 100 buah jaket, akan mendapatkan potongan harga!");
                    break;
                }
                case 2:{
                    System.out.print("Masukkan bahan jaket (A/B/C): ");
                    char inputBahan = scanner.nextLine().toUpperCase().charAt(0);
                    if(!(inputBahan == 'A' || inputBahan == 'B' || inputBahan == 'C')) System.out.println("ERROR: Invalid input!");
                    else{
                        System.out.print("Masukkan jumlah jaket yang ingin dipesan: ");
                        int inputJumlah = scanner.nextInt();
                        if(inputJumlah <= 0) System.out.println("ERROR: Value tidak bisa kurang dari atau sama dengan nol!");
                        else if(pilihBahan.contains(inputBahan)){
                            System.out.printf("WARNING: Anda sudah melakukan pemesanan tipe jaket tersebut (jumlah pemesanan : %d buah). Apakah Anda ingin: \n", jml.get(pilihBahan.indexOf(inputBahan)));
                            System.out.println("1. Rubah pesanan sebelumnya");
                            System.out.println("2. Tambahkan jumlah pada pesanan sebelumnya");
                            System.out.println("3. Tetap pada pesanan sebelumnya");
                            System.out.print("Masukkan pilihan Anda: ");
                            int caseTwoChoice = scanner.nextInt();
                            switch(caseTwoChoice){
                                case 1:{
                                    //modifying new value
                                    jml.set(pilihBahan.indexOf(inputBahan), inputJumlah);
                                    System.out.println("SYSTEM: Jumlah pembelian berhasil dirubah!");
                                    break;
                                }
                                case 2: {
                                    //adding new value to prev. value
                                    jml.set(pilihBahan.indexOf(inputBahan), jml.get(pilihBahan.indexOf(inputBahan)) + inputJumlah);
                                    System.out.println("SYSTEM: Jumlah pembelian telah berhasil ditambahkan!");
                                    break;
                                }
                                case 3: break; //do nothing
                                default: {
                                    System.out.println("ERROR: Invalid Input!");
                                    break;
                                }
                            }
                            //updating the prices
                            if(jml.get(pilihBahan.indexOf(inputBahan)) > 100) hargaJaket.set(pilihBahan.indexOf(inputBahan), jkt.getHargaJaket(inputBahan)[1]);
                            else hargaJaket.set(pilihBahan.indexOf(inputBahan), jkt.getHargaJaket(inputBahan)[0]);
                        }
                        else {
                            i++;
                            jml.add(inputJumlah);
                            pilihBahan.add(inputBahan);
                            if(jml.get(i) > 100) hargaJaket.add(i, jkt.getHargaJaket(pilihBahan.get(i))[1]);
                            else hargaJaket.add(i, jkt.getHargaJaket(pilihBahan.get(i))[0]);
                            System.out.printf("SYSTEM: Berhasil membeli jaket tipe %c\n", pilihBahan.get(i));
                            System.out.print(jml.get(i) > 100 ? "Anda mendapatkan potongan harga karena pembelian lebih dari 100 buah\n" : "");
                            System.out.println("Pesanan anda akan segera diproses\nMasukkan pilihan 3 jika ingin melihat detail pesanan!");
                        }
                    }
                    break;
                }
                case 3:{
                    int j, grandTotal = 0;
                    if(jml.isEmpty() || pilihBahan.isEmpty()) System.out.println("ERROR: Anda belum melakukan pemesanan jaket!\nPilih 2 untuk memesan jaket");
                    else{  
                        System.out.println("=====[DETAIL PEMESANAN JAKET]=====");
                        for(j = 0; j < jml.size(); j++){
                            System.out.printf("Pesanan ke-%d\n", j+1);
                            showDetailPemesanan(pilihBahan.get(j), jml.get(j), hargaJaket.get(j));
                            grandTotal += hargaJaket.get(j) * jml.get(j);
                        }
                        if(j > 1) {
                            System.out.println("=============================================");
                            System.out.printf("%-24s Rp %,3d.00\n", "GRAND TOTAL", grandTotal);
                        }
                    }
                    break;
                }
                default:{
                    System.out.print(pilihan != 4 ? "ERROR: Invalid input!\n" : "");
                    break;
                }
            }
        } while (pilihan != 4);
    }
    public static void showDetailPemesanan(char bahan, int jumlah, int harga){
        System.out.printf("%3s%-20s: %c\n", "", "Jenis Bahan", bahan);
        System.out.printf("%3s%-20s: %d\n", "", "Jumlah Pesanan", jumlah);
        System.out.printf("%3s%-20s: Rp %,3d.00\n", "", "Harga Satuan", harga);
        System.out.printf("%3s%-20s: Rp %,3d.00\n", "", "Total Harga", harga * jumlah);
    }
}