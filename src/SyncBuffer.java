
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class SyncBuffer {

    private static final Lock firstLock = new ReentrantLock();
    private static final Lock secondLock = new ReentrantLock();
    private static final Lock thirdLock = new ReentrantLock();
    private static final Lock fourthLock = new ReentrantLock();
    private static final Lock fifthLock = new ReentrantLock();
    private static final Lock sixthLock = new ReentrantLock();
    private static final Lock seventhLock = new ReentrantLock();
    private static final Lock eighthLock = new ReentrantLock();


    private static final Condition firstLocked = firstLock.newCondition();
    private static final Condition secondLocked = secondLock.newCondition();
    private static final Condition thirdLocked = thirdLock.newCondition();
    private static final Condition fourthLocked = fourthLock.newCondition();
    private static final Condition fifthLocked = fifthLock.newCondition();
    private static final Condition sixthLocked = sixthLock.newCondition();
    private static final Condition seventhLocked = seventhLock.newCondition();
    private static final Condition eighthLocked = eighthLock.newCondition();

    private static Random generator = new Random();



    private static boolean occupied = false;
    /*
public void set(int value){
    accessLock.lock();
    try{
        while ( occupied )
        {
            System.out.printf( "Lock %d is currently occupied!\n", value  );
            canGo.await();// wait until buffer is empty
        } // end while

        occupied = true;
    }
    catch (Exception e){ e.printStackTrace();}
    finally {
        accessLock.unlock();
    }




    public static boolean checkGo(int value) {
        boolean flag = false;
        try {
            /*while ( occupied )
            {
                System.out.printf( "Lock %d is currently occupied!\n", value  );
                canGo.await();// wait until buffer is empty
            }*/ // end while
            //accessLock.lock();
            //canGo.signal();
            //occupied = true;
            //flag = true;
        //} catch (Exception e) {
            //e.printStackTrace();
        //} finally {
            //accessLock.unlock();
        //}
        //return flag;
    //}


    public static void release(int value) {

    }

    /*
    public int get(){
        accessLock.lock();
        try{}
        catch (Exception e) {e.printStackTrace();}
        finally {
            accessLock.unlock();
        }
    return 1;
    }

     */
    public static boolean checkLocks(int lock) throws InterruptedException {
        boolean flag = false;
        switch (lock) {
            case 1:
                flag = firstLock.tryLock();
                break;
            case 2:
                flag = secondLock.tryLock();

                break;
            case 3:
                flag = thirdLock.tryLock();

                break;
            case 4:
                flag = fourthLock.tryLock();

                break;
            case 5:
                flag = fifthLock.tryLock();

                break;
            case 6:
                flag = sixthLock.tryLock();

                break;
            case 7:
                flag = seventhLock.tryLock();

                break;
            case 8:
                flag = eighthLock.tryLock();

                break;

        }
        return flag;
    }


    public static void unlock(int lock) {
        switch (lock) {
            case 1:
                firstLock.unlock();
                firstLocked.signalAll();
                break;
            case 2:
                secondLock.unlock();
                secondLocked.signalAll();
                break;
            case 3:
                thirdLock.unlock();
                thirdLocked.signalAll();
                break;
            case 4:
                fourthLock.unlock();
                fourthLocked.signalAll();
                break;
            case 5:
                fifthLock.unlock();
                fifthLocked.signalAll();
                break;
            case 6:
                sixthLock.unlock();
                sixthLocked.signalAll();
                break;
            case 7:
                seventhLock.unlock();
                seventhLocked.signalAll();
                break;
            case 8:
                eighthLock.unlock();
                eighthLocked.signalAll();
                break;
        }
    }
    public static void lock (int lock){
        switch (lock) {
            case 1:
                firstLock.lock();
                break;
            case 2:
                secondLock.lock();
                break;
            case 3:
                thirdLock.lock();
                break;
            case 4:
                fourthLock.lock();
                break;
            case 5:
                fifthLock.lock();
                break;
            case 6:
                sixthLock.lock();
                break;
            case 7:
                seventhLock.lock();
                break;
            case 8:
                eighthLock.lock();
                break;
        }
    }

}