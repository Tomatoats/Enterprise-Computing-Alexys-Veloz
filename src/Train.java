import java.util.Random;

class Train implements Runnable{
    private int trainID;
    private int starting;
    private int ending;

     private static Random generator = new Random();
    static int locks[] = new int[3];

    public Train (int id, int start, int end){
        trainID = id;
        starting = start;
        ending = end;
    }
 @Override
    public void run() {
     //System.out.printf("yeah, %d started\n", trainID);
     //check that start and end are possible
     if (find(starting,ending)){
         //System.out.printf("there's a route!\n");
         //System.out.printf("locks global! Locks: %d , %d, %d\n",locks[0],locks[1],locks[2]);
         //assuming it is okay, check first lock is open

         try {
             if (SyncBuffer.checkLocks(locks[0]))
             {
                 System.out.printf("Train %d: HOLDS LOCK on Switch #%d \n", trainID,locks[0]);
                 if (SyncBuffer.checkLocks(locks[1]))
                 {
                     System.out.printf("Train %d: HOLDS LOCK on Switch #%d \n", trainID,locks[1]);;

                     if (SyncBuffer.checkLocks(locks[2]))
                     {
                         System.out.printf("Train # HOLDS ALL NEEDED SWITCH LOCKS -- Train movement begins.\n",trainID);

                         try {
                             Thread.sleep((generator.nextInt(100)));
                         } catch (InterruptedException e) {
                             throw new RuntimeException(e);
                         }
                         //release locks
                         System.out.printf("Train %d: Clear of yard control\n",trainID);
                         System.out.printf("Train %d: Releasing all switch locks.\n", trainID);
                         SyncBuffer.unlock(locks[0]);
                         System.out.printf("Train %d: Unlocks / Releases lock on switch {%d}\n",trainID,locks[0]);
                         SyncBuffer.unlock(locks[1]);
                         System.out.printf("Train %d: Unlocks / Releases lock on switch {%d}\n",trainID,locks[1]);
                         SyncBuffer.unlock(locks[2]);
                         System.out.printf("Train %d: Unlocks / Releases lock on switch {%d}\n",trainID,locks[2]);
                         System.out.printf("Train %d: Has been dispatched and moves down the line out of yard control into CTC\n",trainID);
                         System.out.printf("@ @ @ TRAIN %d: DISPATCHED @ @ @\n", trainID);
                     }
                     else
                     {
                         //release locks
                         System.out.printf("Train %d: UNABLE TO LOCK third required switch: Switch {%d}.\n",trainID,locks[2]);
                         System.out.printf("Train %d: Releasing lock on first and second required switches: Switch {%d} and switch {%d}. Train will wait...\n",trainID,locks[0],locks[1]);
                         SyncBuffer.unlock(locks[0]);
                         SyncBuffer.unlock(locks[1]);
                         try {
                             Thread.sleep((generator.nextInt(50)));
                         } catch (InterruptedException e) {
                             throw new RuntimeException(e);
                         }

                     }
                 }
                 else
                 {
                     //release fist lock and wait
                     System.out.printf("Train %d: UNABLE TO LOCK second required switch: Switch {%d}.\n",trainID,locks[1]);
                     System.out.printf("Train %d: Releasing lock on first required switch: Switch {%d}. Train will wait...\n",trainID,locks[0]);
                     SyncBuffer.unlock(locks[0]);
                     try {
                         Thread.sleep((generator.nextInt(50)));
                     } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                     }
                 }
             }
             else
             {
                 //no locks to release, just wait
                 try {
                     System.out.printf("Train %d: UNABLE TO LOCK first required switch: Switch {%d}. Train will wait...\n ",trainID,locks[0] );
                     Thread.sleep((generator.nextInt(50)));
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             }
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }


     }
        else {
         System.out.printf("********************\nTrain %d is on permanent hold and cannot be dispatched\n******************\n",trainID );
         Thread.yield();
     }
     Thread.yield();
        //assumint it is okay, check second lock is open
        //assuming it is okay, check third lock is open
        //if it is all okay, send the train on
        //if not, let the train wait a bit before retrying


    }
    public boolean find(int start, int end){
        boolean flag = false;
        for (int i = 0; i < Main.masterYardList.size();i++){
            //System.out.printf("Start = %d: End = %d. List %d: first = %d and last = %d\n", start,end,i, (Integer)  Main.masterYardList.get(i).getFirst(),(Integer) Main.masterYardList.get(i).getLast()  );
            if (start == (Integer) Main.masterYardList.get(i).getFirst() && end == (Integer) Main.masterYardList.get(i).getLast()){
                flag = true;
                //System.out.println("found a match!");
                for (int k = 0; k < 3; k++){
                    locks[k] = (Integer) Main.masterYardList.get(i).get(k+1);
                }
                //System.out.printf("Locks Needed: %d , %d , %d\n", locks[0],locks[1],locks[2]);
                break;
            }
        }
        return  flag;
    }
}
