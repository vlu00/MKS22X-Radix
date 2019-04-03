import java.util.*;

public class Radix{
  public static int getMaxLength(int[] data) {
    int greatest = Math.abs(data[0]);
    for (int i = 1; i < data.length; i++) {
      if (Math.abs(data[i]) > greatest) {
        greatest = Math.abs(data[i]);
      }
    }
    String num = "" + greatest;
    return num.length();
  }

  public static int getDigit(int num, int pass) {
    num = (int)(num / Math.pow(10, pass));
    if (num == 0) {
      return 0;
    }
    else {
      return Math.abs(num % 10);
    }
  }

  public static void radixsort(int[] data) {
    //setup buckets
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int m = 0; m < 20; m++) {
      buckets[m] = new MyLinkedList();
    }
    //after first pass temp is used to stored semi-sorted values
    MyLinkedList<Integer> temp = new MyLinkedList();
    for (int i = 0; i < getMaxLength(data); i++) {
      for (int n = 0; n < data.length; n++) {
        int digit;
        int num;
        if (i == 0) {
          digit = getDigit(data[n], i);
          num = data[n];
        }
        else {
          num = temp.removeFront();
          digit = getDigit(num, i);
        }
        if (num > 0) {
          buckets[digit+10].add(num);
        }
        else {
          buckets[9-digit].add(num);
        }
      }
      for (int k = 0; k < 20; k++) {
        if (buckets[k].size() > 0) {
          temp.extend(buckets[k]);
        }
      }
    }
    //changes data
    for (int j = 0; j < data.length; j++) {
      data[j] = temp.removeFront();
    }
  }

  public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Radix.radixsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
     }
     System.out.println();
   }
 }
}
