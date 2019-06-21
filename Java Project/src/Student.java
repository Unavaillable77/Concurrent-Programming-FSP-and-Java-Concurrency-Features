
public class Student extends Thread {

    private final LaserPrinterOne LPrinter;
    private final String UID;
    private final ThreadGroup StudentThreadGroup;

    public Student(LaserPrinterOne SPrinter, ThreadGroup StudentThreadGroup, String UID) {
        super(StudentThreadGroup, UID);
        this.LPrinter = SPrinter;
        this.UID = UID; // student name
        this.StudentThreadGroup = StudentThreadGroup;
    }

    public void run() {
        Document CWK1 = new Document(UID, "CWK 01", 15); // all the documents each student has
        Document CWK2 = new Document(UID, "CWK 02", 16);
        Document CWK3 = new Document(UID, "CWK 03", 24);
        Document CWK4 = new Document(UID, "CWK 04", 17);
        Document CWK5 = new Document(UID, "CWK 05", 18);

        LPrinter.printDocument(CWK1); // prints each document 
        System.out.println(getName() + " Printed: " + CWK1.getDocumentName());

        try {
            sleep((int) (Math.random() * 1000)); // waits random amount of seconds inbetween
        } catch (InterruptedException e) {
        }

        LPrinter.printDocument(CWK2);
        System.out.println(getName() + " Printed: " + CWK2.getDocumentName());

        try {
            sleep((int) (Math.random() * 1000));          
        } catch (InterruptedException e) {
        }

        LPrinter.printDocument(CWK3);
        System.out.println(getName() + " Printed: " + CWK3.getDocumentName());

        try {
            sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }

        LPrinter.printDocument(CWK4);
        System.out.println(getName() + " Printed: " + CWK4.getDocumentName());

        try {
            sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }

        LPrinter.printDocument(CWK5);
        System.out.println(getName() + " Printed: " + CWK5.getDocumentName());

    }
}
