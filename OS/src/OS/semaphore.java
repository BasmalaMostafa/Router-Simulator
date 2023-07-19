package OS;

public class semaphore {
    public int signal;
    public semaphore(int initial) { signal  = initial ; }


    public synchronized void  take()  {
        this.signal ++ ;
        if (signal  <= 0) {
            notify() ;
        }
    }
    public synchronized void release(Device D) {
        signal--;
        if (signal < 0) {
            try {
                System.out.println(D.getName() + " arrived and waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println(D.getName() + " arrived");
        }

    }
    }
