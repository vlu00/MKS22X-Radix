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
      if (i == 0) {
        for (int n = 0; n < data.length; n++) {
          int digit = getDigit(data[n], i);
          if (data[n] > 0) {
            buckets[digit+10].add(data[n]);
          }
          else {
            buckets[9-digit].add(data[n]);
          }
        }
      }
      else {
        int tempsize = temp.size();
        for (int n = 0; n < tempsize; n++) {
          int num = temp.removeFront();
          int digit = getDigit(num, i);
          if (num > 0) {
            buckets[digit+10].add(num);
          }
          else {
            buckets[9-digit].add(num);
          }
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

  public static void main(String[] args) {
    int[] A = new int[] {1, 20, -308, 16, 401, -4000, 6};
    radixsort(A);
    String B = "200";
    System.out.println(B.length());
    System.out.println(Arrays.toString(A));
  }

}
