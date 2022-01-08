import java.awt.*;

public class Thread2 implements Runnable {
    private BinaryHeapQueue Lex;
    private int width;
    private int height;

    /**
     * Thread 2 constructure to take queue and looping condition value
     * @param LexQueue  queue has a Lex compare method
     * @param width     image width value
     * @param height    image height value
     */
    public Thread2(BinaryHeapQueue LexQueue,int width,int height){
        Lex=LexQueue;
        this.width=width;
        this.height=height;
    }

    /**
     * run method to using thread4 LEX queue elemnts polling and print color values
     */
    @Override
    public void run() {
        int i=0;
        while(i<width*height) {
            synchronized (Lex) {
                if (Lex.size() > 0) {
                    Color c = new Color((Integer) Lex.poll());
                    System.out.printf("Thread 2-PQLEX: [%d,%d,%d]\n", c.getRed(), c.getGreen(), c.getBlue());
                    ++i;
                    //Thread.yield();  i didn't sure for accesing thread1
                }
                else {
                    try {
                        Lex.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
