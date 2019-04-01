public class Radix{

  public static int getMaxLength(int[] data) {
    int greatest = Math.abs(data[0]);
    for (int i = 1; i < data.length; i++) {
      if (Math.abs(data[i]) > greatest) {
        greatest = Math.abs(data[i]);
      }
    }
    int digits = 0;
    while (greatest > 0) {
      digits++;
      greatest = greatest / 10;
    }
    return digits;
  }

  public static void radixsort(int[] data) {
    MyLinkedList[] buckets = new MyLinkedList[20];
    int runs = getMaxLength(data);
    for (int i = 0; i < runs; i++) {
      if (i = 0) {
        for (int n = 0; n < data.length; n++) {
          getDigit(data[i])
          if (data[n] < 0) {

          }
        }
      }
      else {

      }

    }
  }

  public static void main(String[] args) {
    int[] A = new int[] {1, 20, 3, 400, -4000, 6};
    System.out.println(getMaxLength(A));
  }

}
