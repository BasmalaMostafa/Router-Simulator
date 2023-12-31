package OS;

import static java.lang.Thread.sleep;

public class Router {
    public boolean[] connectionArr;
    public int maxNumOfCons;
    public int numOccupied;
    public semaphore obj;

    public Router(int max) {
        this.maxNumOfCons = max;
        connectionArr = new boolean[maxNumOfCons];
        obj=new semaphore(maxNumOfCons);
    }

    public synchronized int occupy(Device D) throws InterruptedException {
        for (int i = 0; i < maxNumOfCons; i++) {
            if (connectionArr[i] == false) {// means It's available for being occupied
                    numOccupied++;
                    D.NumOfDevice=i+1;
                    System.out.println("connection " +D.NumOfDevice+": "+D.getName()+" occupied");
                    connectionArr[i] = true;
                    sleep(1000);
                    break;

            }
        }
        return D.NumOfDevice;
    }
    public void Login(Device D) throws InterruptedException {
        sleep(1000);
        System.out.println("connection " +D.NumOfDevice + ": " + D.getName() +"Log in");
    }
    public void performOnlineAcv(Device D) throws InterruptedException {
        sleep(1000);
        System.out.println("connection " +D.NumOfDevice + ": " + D.getName() +"Performs Online activity");
    }
    public synchronized void logOut(Device D){
        numOccupied--;
        connectionArr[D.NumOfDevice-1] = false;
        notify();
        System.out.println("connection " +D.NumOfDevice + ": " + D.getName() + "Logged Out");
    }
}
