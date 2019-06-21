
public class PrintingSystem {

    public static void main(String args[]) {
        final String PRINTERNAME = "5.114"; // printer name

        System.out.println("Create: LaserPrinterOne, Students & Technicians");

        LaserPrinterOne prt1 = new LaserPrinterOne(PRINTERNAME); // creating a shared printer
        
        ThreadGroup StudentThreadGroup = new ThreadGroup("StudentThreadGroup");  // creates the thread groups
        ThreadGroup TechThreadGroup = new ThreadGroup("TechThreadGroup");

        Student s1 = new Student(prt1, StudentThreadGroup, "Roy"); // creates the students
        Student s2 = new Student(prt1, StudentThreadGroup, "Dez");
        Student s3 = new Student(prt1, StudentThreadGroup, "Raul");
        Student s4 = new Student(prt1, StudentThreadGroup, "Premal");

        PaperTech p1 = new PaperTech(prt1, TechThreadGroup, "JJ"); // creates the technicians 
        TonerTech t1 = new TonerTech(prt1, TechThreadGroup, "Dan");
        
        //if( p1.getThreadGroup() == s1.getThreadGroup() ){
        //    System.out.println("same Grp");
        //}
        
        System.out.println("Start Technicians & Students");

        s1.start();
        s2.start();
        s3.start();
        s4.start();

        p1.start();
        t1.start();
        
        System.out.println("Active Count StudentThreadGroup: " + StudentThreadGroup.activeCount() + "\nActive Count TechThreadGroup: " + TechThreadGroup.activeCount());

//         wait for all threads to terminate
        try {
            p1.join();
            t1.join();

            s1.join();
            s2.join();
            s3.join();
            s4.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }

        System.out.println("Technicians & Students have terminated");

    }

}
