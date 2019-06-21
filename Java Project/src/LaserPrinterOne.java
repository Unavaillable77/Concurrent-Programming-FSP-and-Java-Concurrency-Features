
public class LaserPrinterOne implements ServicePrinter {

    private final String printerName;
    private int currentPprLvl;
    private int currentTnrLvl;
    private int documentsPrinted;

    public LaserPrinterOne(String printerName) { // constructor
        this.printerName = printerName;
        this.currentPprLvl = 5; // initial paper level
        this.currentTnrLvl = 5; // initial toner level
        this.documentsPrinted = 0; // no documents printed at start
    }

    public synchronized void printDocument(Document document) { // student print document method

        while ((currentPprLvl < document.getNumberOfPages()) || (currentTnrLvl < document.getNumberOfPages())) { // if paper or toner level is not enough it will wait
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("------------------- Thread  interrupted -------------------");
            }
        }

        for (int i = 1; i <= document.getNumberOfPages(); i++) { // it will print page by page and decrease paper and toner accordingly 
            this.currentPprLvl--;
            this.currentTnrLvl--;
            printState("printDocument(): " + document.getUserID() + "." + document.getDocumentName() + ".Page(" + i + "/" + document.getNumberOfPages());
        }
        this.documentsPrinted++;

        notifyAll();
        printState("Status(): "); // prints the paper status after each print
    }

    public synchronized void replaceTonerCartridge() { // toner tech replaces toner method

        if (!(currentTnrLvl < ServicePrinter.Minimum_Toner_Level)) { // if toner is not under 10 it will wait 5 seconds and try to replace anyway
            try {
                wait(5000);
            } catch (InterruptedException e) {
                System.out.println("------------------- Thread  interrupted -------------------");
            }
        }

        if ((currentTnrLvl < ServicePrinter.Minimum_Toner_Level)) { // only replaces if toner level is under 10
            this.currentTnrLvl = ServicePrinter.Full_Toner_Level;
            printState("Refilled Toner: ");
        }

        notifyAll();
    }

    public synchronized void refillPaper() { // paper tech replaces paper method

//        while (!(currentPprLvl < (ServicePrinter.Full_Paper_Tray - ServicePrinter.SheetsPerPack))) {
        if (currentPprLvl > (ServicePrinter.Full_Paper_Tray - ServicePrinter.SheetsPerPack)) { // if paper level is higher than 200 it will wait 5 secnds and try replace anyway
            try {
                wait(5000);
            } catch (InterruptedException e) {
                System.out.println("------------------- Thread  interrupted -------------------");
            }
        }

        while (this.currentPprLvl < (ServicePrinter.Full_Paper_Tray - ServicePrinter.SheetsPerPack)) { // it will replace as many packs as it can before the printer its full without wasting paper
            this.currentPprLvl = this.currentPprLvl + ServicePrinter.SheetsPerPack;
            printState("Refilled Paper: ");
        }

        notifyAll();
    }

    public void printState(String operation) {
        System.out.println("                                LaserPrinterOne." + operation + this);
    }

    public String toString() {
        return new String("[ PrinterID: " + printerName
                + " , Paper Level: " + currentPprLvl
                + " , Toner Level: " + currentTnrLvl
                + " , Documents Printed: " + documentsPrinted + " ]");
    }
}
