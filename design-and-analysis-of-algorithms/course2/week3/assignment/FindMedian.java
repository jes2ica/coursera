package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by yijiez on 4/8/17.
 */
public class FindMedian {

    private static int median = 0;

    private void findMedian(PriorityQueue minHeap, PriorityQueue maxHeap)  {
        String fileName = "/Users/yijiez/IdeaProjects/algorithm/src/tree/data/Median.txt";
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextInt()) {
                addToHeap(sc.nextInt(), minHeap, maxHeap);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addToHeap(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if (maxHeap.size() + 1 < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        } else if (minHeap.size() + 1 < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
        median += calculateMedian(minHeap, maxHeap);
    }

    private int calculateMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            median = maxHeap.peek();
        } else {
            if (minHeap.size() > maxHeap.size()) {
                median = minHeap.peek();
            } else {
                median = maxHeap.peek();
            }
        }
        return median;
    }

    public static void main(String[] args) {
        FindMedian fm = new FindMedian();
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());

        fm.findMedian(minHeap, maxHeap);
        System.out.println("median: "  + median % 10000);
    }
}
