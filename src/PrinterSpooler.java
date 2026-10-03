import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// คลาสเก็บข้อมูลเอกสาร
class PrintJob {
    String documentName;
    String owner;
    int pages;

    public PrintJob(String documentName, String owner, int pages) {
        this.documentName = documentName;
        this.owner = owner;
        this.pages = pages;
    }

    // แสดงข้อมูลเอกสาร
    public void display() {
        System.out.println("ชื่อเอกสาร : " + documentName);
        System.out.println("ผู้สั่งพิมพ์ : " + owner);
        System.out.println("จำนวนหน้า : " + pages);
    }
}

public class PrinterSpooler {

    // สร้าง Queue สำหรับเก็บงานพิมพ์
    static Queue<PrintJob> printQueue = new LinkedList<>();

    // เพิ่มงานพิมพ์เข้า Queue
    public static void addPrintJob(PrintJob job) {
        printQueue.add(job);
        System.out.println("\nเพิ่มงานพิมพ์เรียบร้อยแล้ว");
        System.out.println("เอกสาร \"" + job.documentName + "\" ถูกเพิ่มเข้า Queue");
    }

    // พิมพ์เอกสารงานแรกใน Queue
    public static void printDocument() {

        if (printQueue.isEmpty()) {
            System.out.println("\nไม่มีเอกสารใน Queue");
            return;
        }

        // นำงานพิมพ์ตัวแรกออกจาก Queue
        PrintJob job = printQueue.poll();

        System.out.println("\nกำลังพิมพ์เอกสาร...");
        job.display();
        System.out.println("สถานะ : พิมพ์เสร็จเรียบร้อย");
    }

    // แสดงรายการงานพิมพ์ที่รออยู่
    public static void showQueue() {

        if (printQueue.isEmpty()) {
            System.out.println("\nไม่มีงานพิมพ์ที่รออยู่");
            return;
        }

        System.out.println("\n===== รายการงานพิมพ์ที่รอ =====");

        int number = 1;

        for (PrintJob job : printQueue) {
            System.out.println("\nลำดับที่ " + number);
            job.display();
            number++;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("   PRINTER SPOOLER SYSTEM");
            System.out.println("==============================");
            System.out.println("1. เพิ่มเอกสารเข้าคิว");
            System.out.println("2. พิมพ์เอกสาร");
            System.out.println("3. แสดงรายการเอกสารในคิว");
            System.out.println("4. จำนวนเอกสารที่รอพิมพ์");
            System.out.println("0. ออกจากโปรแกรม");
            System.out.println("==============================");
            System.out.print("เลือกเมนู : ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("ชื่อเอกสาร : ");
                    String documentName = scanner.nextLine();

                    System.out.print("ชื่อผู้สั่งพิมพ์ : ");
                    String owner = scanner.nextLine();

                    System.out.print("จำนวนหน้า : ");
                    int pages = scanner.nextInt();

                    PrintJob job =
                            new PrintJob(documentName, owner, pages);

                    addPrintJob(job);
                    break;

                case 2:
                    printDocument();
                    break;

                case 3:
                    showQueue();
                    break;

                case 4:
                    System.out.println(
                            "\nจำนวนเอกสารที่รอพิมพ์ = "
                                    + printQueue.size()
                    );
                    break;

                case 0:
                    System.out.println("\nออกจากโปรแกรม");
                    break;

                default:
                    System.out.println("\nกรุณาเลือกเมนู 0-4");
            }

        } while (choice != 0);

        scanner.close();
    }
}