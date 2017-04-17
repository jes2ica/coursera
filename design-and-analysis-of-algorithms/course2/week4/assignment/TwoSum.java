package hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by yijiez on 4/13/17.
 */
public class TwoSum {

    private int  findTarget()  {
        String fileName = "/Users/yijiez/IdeaProjects/algorithm/src/hashtable/data/2sum.txt";
        HashSet<Long> hs = new HashSet<Long>();

        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLong()) {
                hs.add(sc.nextLong());
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int sum = 10000;
        int count = 0;
        while (sum-- >= -10000) {
            Iterator<Long> iterator = hs.iterator();
            while (iterator.hasNext()) {
                long cur = iterator.next();
                if (hs.contains(sum - cur) && cur != sum - cur) {
                    count++;
                    System.out.println(sum);
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println("result: " + twoSum.findTarget());
    }
}
