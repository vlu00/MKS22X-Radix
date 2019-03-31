public class MyLinkedList <E> {
  class Node{
    private E data;
    private Node next, prev;

    public Node(E d) {
      data = d;
      prev = null;
      next = null;
    }
    public Node (Node p, E d, Node n) {
      prev = p;
      data = d;
      next = n;
    }

    public void setData(E value) {
      data = value;
    }

    public void setNext(Node name) {
      next = name;
    }

    public void setPrev(Node name) {
      prev = name;
    }

    public E getData() {
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

  private int length;
  private Node start, end;

  public MyLinkedList() {
    length = 0;
    start= null;
    end = null;
  }

  public void clear() {
    length = 0;
    start.setNext(end); //gets rid of nodes in between start and end
    end.setPrev(start);
    start = null;
    end = null;
  }

  public int size() {
    return length;
  }

  private Node getNthNode (int index) {
    Node x = start; //starting with the first node
    for (;index > 0; index--) { //until index is reached
      x = x.next(); //move onto the next node
    }
    return x;
  }

  public boolean add (E value) {
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

  public E removeFront() {
    E old = start.getData(); //return value is the start
    if (size() == 1) { //if there is only one node
      start = null; //list is null
      end = null;
    }
    else {
      Node y = start.next(); //second node
      y.setPrev(null); //becomes first
      start.setNext(null); //seperates start from list
      start = y; //new start is the second nofe
    }
    length--;
    return old;
  }

  public void extend(MyLinkedList<E> other) {
    if (this.size() == 0) {
      int index = 0;
      while (index < other.length) {
        this.add(other.getNthNode(index).getData());
        index++;
      }
      this.length = other.length; //changes size of first list
      other.length = 0; //length of second list is 0
    }
    else {
      this.end.setNext(other.start); //links two lists
      other.start.setPrev(this.end);
      this.end = other.end; //changes end of first list
      this.length = this.length + other.length; //changes size of first list
      other.length = 0; //length of second list is 0
    }
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
