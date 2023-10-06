
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class SyncBuffer {

    private Lock accessLock = new ReentrantLock();

public void set(int value){
    accessLock.lock();
    try{

    }
    catch (Exception e){ e.printStackTrace();}
    finally {
        accessLock.unlock();
    }

}

public int get(){
    accessLock.lock();
    try{}
    catch (Exception e) {e.printStackTrace();}
    finally {
        accessLock.unlock();
    }
return 1;
}
}
