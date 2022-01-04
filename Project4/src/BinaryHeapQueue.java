import java.awt.*;
import java.util.*;

/**
 * I helped from binaryheap priorityQueue in Data Structures and Algorithm book
 */
public class BinaryHeapQueue<E> extends AbstractQueue<E>
        implements Queue<E> {
// Data Fields
    /**
     * The ArrayList to hold the data.
     */
    private E[] theData;
    private int currentSize = 0;      // Number of elements in heap
    private int capacity;
    //private Comparable[] array; // The heap array
    /**
     * An optional reference to a Comparator object.
     */
    Comparator<E> comparator = null;

    // Methods
// Constructor
    public BinaryHeapQueue() {
        theData = (E[]) new Object[100];
        capacity=100;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    public BinaryHeapQueue( Comparator<E> comp) {
        theData = (E[]) new Object[100];
        comparator = comp;
        capacity=100;
    }

    @Override
    public boolean offer(E item) {
        // Add the item to the heap.
        if(currentSize==capacity){
            reallocate();
        }
        theData[currentSize]= item;
        currentSize++;
        // child is newly inserted item.
        int child = currentSize- 1;
        int parent = (child - 1) / 2; // Find child's parent.

        while (parent >= 0 && compare( theData[parent],
                theData[child]) < 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }

    public void reallocate(){
        int i=0;
        capacity*=2;
        E temp[] = (E[]) new Object[capacity];
        while(i<currentSize){
            temp[i]=theData[i];
            i++;
        }
        theData=temp;
    }
    public void swap(int left,int right){
        E a;
        a=  theData[left];
        theData[left]=theData[right];
        theData[right]= a;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        E result = (E) theData[0];
        // If only one item then remove it.
        if (currentSize == 1) {
            currentSize--;
            theData[0]=null;
            return result;
        }
        /* Remove the last item from the ArrayList and place it into
           the first position. */
        set(0, remove(currentSize));
        // The parent starts at the top.
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= currentSize) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild; // Assume leftChild is smaller.
            // See whether rightChild is smaller.
            if (rightChild < currentSize
                    && compare(theData[leftChild],
                    theData[rightChild]) < 0) {
                minChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (compare(theData[parent],
                    theData[minChild]) < 0) {
                swap(parent, minChild);
                parent = minChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return result;
    }

    public void set(int index,E element){
        theData[index]=element;
    }

    public E remove(int index){
        currentSize--;
        index--;
        E temp=theData[index];
        return temp;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        E result = (E) theData[0];
        // If only one item then remove it.
        if (currentSize == 1) {
            currentSize--;
            theData[0]=null;
            return result;
        }
        /* Remove the last item from the ArrayList and place it into
           the first position. */
        //set(0, remove(currentSize));
        // The parent starts at the top.
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= currentSize) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild; // Assume leftChild is smaller.
            // See whether rightChild is smaller.
            if (rightChild < currentSize
                    && compare(theData[leftChild],
                    theData[rightChild]) < 0) {
                minChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (compare(theData[parent],
                    theData[minChild]) < 0) {
                swap(parent, minChild);
                parent = minChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return result;
    }

    /**
     *
     * @param left first pixel to compare
     * @param right second pixel to compare
     * @return bigger smaller or equal value of two pixels
     */
    @SuppressWarnings("unchecked")
    private int compare(E left, E right) {
        if (comparator != null) { // A Comparator is defined.
            return comparator.compare(left, right);
        } else { // Use left's compareTo method.
            return ((Comparable<E>) left).compareTo(right);
        }
    }

}