
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/*
Name: Alexys Octavio Veloz
Course: CNT 4714 Fall 2023
Assignment title: Project 2 – Multi-threaded programming in Java
Date: October 8, 2023
Class: SyncBuffer
Description: This is the main locking mechanism that locks and unlocks locks and checks that trains go through
*/
public class SyncBuffer {

    private static final Lock firstLock = new ReentrantLock();
    private static final Lock secondLock = new ReentrantLock();
    private static final Lock thirdLock = new ReentrantLock();
    private static final Lock fourthLock = new ReentrantLock();
    private static final Lock fifthLock = new ReentrantLock();
    private static final Lock sixthLock = new ReentrantLock();
    private static final Lock seventhLock = new ReentrantLock();
    private static final Lock eighthLock = new ReentrantLock();




    private static Random generator = new Random();



    public static boolean checkLocks(int lock) throws InterruptedException {
        //if its free, locks it and makes it true. if not, sends false
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
        //to unlock any switches
        switch (lock) {
            case 1:
                firstLock.unlock();
                //firstLocked.signalAll();
                break;
            case 2:
                secondLock.unlock();
                //secondLocked.signalAll();
                break;
            case 3:
                thirdLock.unlock();
                //thirdLocked.signalAll();
                break;
            case 4:
                fourthLock.unlock();
                //fourthLocked.signalAll();
                break;
            case 5:
                fifthLock.unlock();
                //fifthLocked.signalAll();
                break;
            case 6:
                sixthLock.unlock();
                //sixthLocked.signalAll();
                break;
            case 7:
                seventhLock.unlock();
                //seventhLocked.signalAll();
                break;
            case 8:
                eighthLock.unlock();
                //eighthLocked.signalAll();
                break;
        }
    }


    public static void locking(int lock1, int lock2, int lock3, int trainID) throws InterruptedException {

        boolean done = false;
        while (!done) { // keep this running until it gets to its location
            if (checkLocks(lock1)) { // check and aquire first lock
                System.out.printf("Train %d: HOLDS LOCK on Switch #%d \n\n", trainID,lock1);
                try {
                    if (checkLocks(lock2)) { // check and aquire second lock
                        System.out.printf("Train %d: HOLDS LOCK on Switch #%d \n\n", trainID,lock2);;
                        try {
                            if (checkLocks(lock3)) {
                                //send it out! all ready
                                System.out.printf("Train %d HOLDS ALL NEEDED SWITCH LOCKS -- Train movement begins.\n\n\n",trainID);
                                try {
                                    Thread.sleep((generator.nextInt(100)));
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.printf("Train %d: Clear of yard control\n", trainID);
                                System.out.printf("Train %d: Releasing all switch locks.\n", trainID);
                                SyncBuffer.unlock(lock1);
                                System.out.printf("Train %d: Unlocks / Releases lock on switch {%d}\n", trainID, lock1);
                                SyncBuffer.unlock(lock2);
                                System.out.printf("Train %d: Unlocks / Releases lock on switch {%d}\n", trainID, lock2);
                                SyncBuffer.unlock(lock3);
                                System.out.printf("Train %d: Unlocks / Releases lock on switch {%d}\n", trainID, lock3);
                                System.out.printf("Train %d: Has been dispatched and moves down the line out of yard control into CTC\n", trainID);
                                System.out.printf("@ @ @ TRAIN %d: DISPATCHED @ @ @\n\n\n", trainID);
                                done = true;
                            } else {
                                System.out.printf("Train %d: UNABLE TO LOCK third required switch: Switch {%d}.\n",trainID,lock3);
                                System.out.printf("Train %d: Releasing lock on first and second required switches: Switch {%d} and switch {%d}. Train will wait...\n\n\n",trainID,lock1,lock2);
                                unlock(lock2);
                                unlock(lock1);
                                Thread.sleep(generator.nextInt(25));
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //release fist lock and wait
                        System.out.printf("Train %d: UNABLE TO LOCK second required switch: Switch {%d}.\n",trainID,lock2);
                        System.out.printf("Train %d: Releasing lock on first required switch: Switch {%d}. Train will wait...\n\n\n",trainID,lock1);
                        unlock(lock1);
                        Thread.sleep(generator.nextInt(25));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                //tell  it to wait
                System.out.printf("Train %d: UNABLE TO LOCK first required switch: Switch {%d}. Train will wait...\n\n\n",trainID,lock1 );
                Thread.sleep(75);
            }
        }
    }
}
