 class Train implements Runnable{
    private int trainID;
    private int starting;
    private int ending;

    static int locks[] = new int[3];

    public Train (int id, int start, int end){
        trainID = id;
        starting = start;
        ending = end;
    }
 @Override
    public void run() {
     System.out.printf("yeah, %d started\n", trainID);
     //check that start and end are possible
     if (find(starting,ending)){
         System.out.printf("there's a route!\n");
         //System.out.printf("locks global! Locks: %d , %d, %d\n",locks[0],locks[1],locks[2]);
         //assuming it is okay, check first lock is open

     }
        else {
         System.out.printf("Not able to do!\n");
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
                System.out.println("found a match!");
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
