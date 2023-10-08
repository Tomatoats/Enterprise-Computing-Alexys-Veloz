import java.util.Random;

/*
Name: Alexys Octavio Veloz
Course: CNT 4714 Fall 2023
Assignment title: Project 2 – Multi-threaded programming in Java
Date: October 8, 2023
Class: Train
Description: This class validate's a train's start and end are valid. if so, it sends info to the syncBuffer
*/

class Train implements Runnable {
    private int trainID;
    private int starting;
    private int ending;

    private static Random generator = new Random();
    static int locks[] = new int[3];

    public Train(int id, int start, int end) {
        trainID = id;
        starting = start;
        ending = end;
    }

    @Override
    public void run() {
        int result = 0;
        //check that start and end are possible
        if (find(starting, ending)) {

            //assuming it is okay, check locks
            try {
                SyncBuffer.locking(locks[0], locks[1], locks[2], trainID);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.printf("**********************\nTrain %d is on permanent hold and cannot be dispatched\n******************\n",trainID );
            Thread.yield();
        }
    }


            public boolean find ( int start, int end){
                boolean flag = false;
                for (int i = 0; i < Main.masterYardList.size(); i++) {
                    if (start == (Integer) Main.masterYardList.get(i).getFirst() && end == (Integer) Main.masterYardList.get(i).getLast()) {
                        flag = true;
                        for (int k = 0; k < 3; k++) {
                            locks[k] = (Integer) Main.masterYardList.get(i).get(k + 1);
                        }
                        break;
                    }
                }
                return flag;
            }
        }
