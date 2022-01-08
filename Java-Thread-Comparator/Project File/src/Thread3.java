import java.awt.*;

public class Thread3 implements Runnable {
    private BinaryHeapQueue Euc;
    private int width;
    private int height;

    /**
     * Thread 3 constructure to take queue and looping condition value
     * @param EucQueue  queue has a Euc compare method
     * @param width     image width value
     * @param height    image height value
     */
    public Thread3(BinaryHeapQueue EucQueue,int width,int height){
        Euc=EucQueue;
        this.width=width;
        this.height=height;
    }

    /**
     * run method to using thread4 EUC queue elemnts polling and print color values
     */
    @Override
    public void run() {
        int i=0;
        while(i<width*height) {
            synchronized (Euc) {
                if (Euc.size() > 0) {
                    Color c = new Color((Integer) Euc.poll());
                    System.out.printf("Thread 3-PQEUC: [%d,%d,%d]\n", c.getRed(), c.getGreen(), c.getBlue());
                    i++;
                    //Thread.yield();  i didn't sure for accesing thread1
                }
                else {
                    try {
                        Euc.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }
}
