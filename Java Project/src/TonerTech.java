
public class TonerTech extends Thread {

    private final LaserPrinterOne LPrinter;
    private final String TID;
    private final ThreadGroup TechThreadGroup;

    public TonerTech(LaserPrinterOne SPrinter, ThreadGroup TechThreadGroup, String TID) {
        super(TechThreadGroup, TID);
        this.LPrinter = SPrinter;
        this.TID = TID; // toner tech name
        this.TechThreadGroup = TechThreadGroup;
    }

    public void run() {

        LPrinter.replaceTonerCartridge(); // only tries to replae toner 3 times
        System.out.println(getName() + " Tried to Refill Toner[ 1 ]: ");

        try {
            sleep((int) (Math.random() * 5000)); // it will wait random amount of time inbetween
        } catch (InterruptedException e) {
        }

        LPrinter.replaceTonerCartridge();
        System.out.println(getName() + " Tried to Refill Toner[ 2 ]: ");

        try {
            sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
        }

        LPrinter.replaceTonerCartridge();
        System.out.println(getName() + " Tried to Refill Toner[ 3 ]: ");

    }
}
