import java.util.*;

public class Radix{
  public static int getMaxLength(int[] data) {
    int greatest = Math.abs(data[0]);
    for (int i = 1; i < data.length; i++) {
      if (Math.abs(data[i]) > greatest) {
        greatest = Math.abs(data[i]);
      }
    }
    int passes = 0;
    while (greatest > 0) {
      passes++;
      greatest = greatest / 10;
    }
    return passes;
  }

  public static int getDigit(int num, int pass) {
    while (pass > 1) {
      num = num / 10;
      pass--;
    }
    return Math.abs(num % 10);
  }

  public static void radixsort(int[] data) {
    //setup buckets
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int m = 0; m < 20; m++) {
      buckets[m] = new MyLinkedList();
    }
    //after first pass temp is used to stored semi-sorted values
    MyLinkedList<Integer> temp = new MyLinkedList();
    int runs = getMaxLength(data);
    for (int i = 0; i < runs; i++) {
      if (i == 0) {
        for (int n = 0; n < data.length; n++) {
          int digit = getDigit(data[n], i);
          if (data[n] < 0) {
            buckets[digit+10].add(data[n]);
          }
          else {
            buckets[9-digit].add(data[n]);
          }
        }
      }
      else {
        for (int n = 0; n < temp.size(); n++) {
          int num = temp.removeFront();
          int digit = getDigit(num, i);
          if (num < 0) {
            buckets[digit+10].add(num);
          }
          else {
            buckets[9-digit].add(num);
          }
        }
      }
      for (int k = 0; k < 20; k++) {
        temp.extend(buckets[k]);
      }
    }
    //changes data
    for (int j = 0; j < data.length; j++) {
      data[j] = temp.removeFront();
    }
  }

  public static void main(String[] args) {
    int[] A = new int[] {1, 20, 3, 400, -4000, 6};
    radixsort(A);
    System.out.println(Arrays.toString(A));
  }

}
