import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Thread1 implements Runnable {
    private BinaryHeapQueue PQLEX;
    private BinaryHeapQueue PQEUC;
    private BinaryHeapQueue PQBMX;
    private String File_name;

    /**
     *
     * @param PQLEX     Lex queue
     * @param PQEUC     Euc queue
     * @param PQBMX     Bmx queue
     * @param file_name     image file_path which come command line in main
     */
    public Thread1(BinaryHeapQueue PQLEX ,BinaryHeapQueue PQEUC,BinaryHeapQueue PQBMX,String file_name){
        this.PQLEX=PQLEX;
        this.PQEUC=PQEUC;
        this.PQBMX=PQBMX;
        File_name=file_name;
    }


    /**
     * run method to using thread1 start all things in there.
     */
    @Override
    public void run() {
        read_f();
    }

    /**
     * read_f is my first thread.This method read image and offer pixels in queue and keep counter.
     * If counter equals 100, start other threads for poll pixels.Offer methods have notify method to use wait thread to wake up
     *When loop turn image width*height thread 1 is terminate.
     */
    public void read_f(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(File_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];
        int counter=0;

        Thread2 Lex_thread=new Thread2(PQLEX,width,height);
        Thread3 Euc_thread=new Thread3(PQEUC,width,height);
        Thread4 Bmx_thread=new Thread4(PQBMX,width,height);
        Thread thread2 = new Thread(Lex_thread);
        Thread thread3 = new Thread(Euc_thread);
        Thread thread4 = new Thread(Bmx_thread);

        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++) {
                result[row][col] = image.getRGB(col, row);
                Color c = new Color(result[row][col]);
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                System.out.printf("Thread 1: [%d, %d, %d]\n", red, green, blue, counter);
                synchronized (PQLEX) {
                    PQLEX.offer(result[row][col]);
                    PQLEX.notifyAll();
                }
                synchronized (PQEUC) {
                    PQEUC.offer(result[row][col]);
                    PQEUC.notifyAll();
                }
                synchronized (PQBMX) {
                    PQBMX.offer(result[row][col]);
                    PQBMX.notifyAll();
                }
                counter++;
                if (counter == 100) {
                    System.out.println("//... etc inserting all the way to at least the first 100 pixels..");
                    thread2.start();
                    thread3.start();
                    thread4.start();
                }

            }
    }
}
