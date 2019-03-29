public class MyLinkedList{
  class Node{
    private Integer data;
    private Node next, prev;

    public Node(Integer d) {
      data = d;
      prev = null;
      next = null;
    }
    public Node (Node p, Integer d, Node n) {
      prev = p;
      data = d;
      next = n;
    }

    public void setData(Integer value) {
      data = value;
    }

    public void setNext(Node name) {
      next = name;
    }

    public void setPrev(Node name) {
      prev = name;
    }

    public Integer getData() {
      return data;
    }

    public Node next() {
      return next;
    }

    public Node prev() {
      return prev;
    }

    public String toString() {
      String s = "";
      s = s + data;
      return s;
    }
  }

  public MyLinkedList() {
    length = 0;
    start= null;
    end = null;
  }

  //need a clear;

  public boolean add (Integer value) {
    if (size() == 0) {
      Node A = new Node (null, value, end);
      start = A; //the start and end are the same
      end = A;
    }
    else {
      Node A = new Node(end, value, null);
      end.setNext(A); //the end changes
      end = A;
    }
    length++;
    return true;
  }

  public String toString() {
    String s = "[";
    Node current = start;
    for (int i = 0; i < size(); i++) {
      if (!(current.equals(null)) && !(current.equals(end))) { //for any node that is not the end
        s = s + current.toString() + ", "; //include a comma afterwards
        current = current.next(); //move onto next node
      }
      else { //for the last node
        s = s + current.toString(); //do not include a comma
        current = current.next();
      }
    }
    return s+"]";
  }

}
