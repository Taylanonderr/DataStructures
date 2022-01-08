import java.awt.*;

public class Thread4 implements Runnable {
    private BinaryHeapQueue Bmx;
    private int width;
    private int height;
    /**
     * Thread 4 constructure to take queue and looping condition value
     * @param BmxQueue  queue has a Bmx compare method
     * @param width     image width value
     * @param height    image height value
     */
    public Thread4(BinaryHeapQueue BmxQueue,int width,int height){
        Bmx=BmxQueue;
        this.width=width;
        this.height=height;
    }

    /**
     * run method to using thread4 BMX queue elemnts polling and print color values
     */
    @Override
    public void run() {
        int i=0;
        while(i<width*height) {
            synchronized (Bmx) {
                if (Bmx.size() > 0) {
                    Color c = new Color((Integer) Bmx.poll());
                    i++;
                    System.out.printf("Thread 4-PQBMX: [%d,%d,%d]\n", c.getRed(), c.getGreen(), c.getBlue());
                    //Thread.yield();  i didn't sure for accesing thread1
                }
                else {

                    try {
                        Bmx.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
