
public class PaperTech extends Thread {

    private final LaserPrinterOne LPrinter;
    private final String TID;
    private final ThreadGroup TechThreadGroup;

    public PaperTech(LaserPrinterOne SPrinter, ThreadGroup TechThreadGroup, String TID) {
        super(TechThreadGroup, TID);
        this.LPrinter = SPrinter;
        this.TID = TID; // Tech Name
        this.TechThreadGroup = TechThreadGroup;
    }

    public void run() {

        LPrinter.refillPaper(); // it will try refill paper only 3 times
        System.out.println(getName() + " Tried to Refill Paper[ 1 ]: ");

        try {
            sleep((int) (Math.random() * 5000)); // it waits a random amount of time in between
        } catch (InterruptedException e) {
        }

        LPrinter.refillPaper();
        System.out.println(getName() + " Tried to Refill Paper[ 2 ]: ");

        try {
            sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
        }

        LPrinter.refillPaper();
        System.out.println(getName() + " Tried to Refill Paper[ 3 ]: ");

    }
}
