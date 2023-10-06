 class Train implements Runnable{
    private int trainID;
    private int starting;
    private int ending;

    public Train (int id, int start, int end){
        trainID = id;
        starting = start;
        ending = end;
    }
 @Override
    public void run() {
        System.out.printf("yeah, %d started\n", trainID);
        Thread.yield();
        //check that start and end are possible
        //assuming it is okay, check first lock is open
        //assumint it is okay, check second lock is open
        //assuming it is okay, check third lock is open
        //if it is all okay, send the train on
        //if not, let the train wait a bit before retrying


    }
}
