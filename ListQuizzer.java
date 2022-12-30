//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07_QUIZZER
// Course: CS 300 Fall 2022
//
// Author: VARDAAN KAPOOR
// Email: VKAPOOR5@WISC.EDU
// Lecturer: (Mouna Kacem, Hobbes LeGault, or Jeff Nyhoff)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class models an iterable singly-linked list data structure which stores elements of type
 * MultipleChoiceQuestion
 */
public class ListQuizzer implements Iterable<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> head;// Head of this singly linked list
  private ListingMode listingMode;// The listing mode of this list quizzer which defines which
                                  // questions are going to
  // be listed while iterating through this list
  private int size;// Total number of MultipleChoiceQuestions stored in this ListQuizzer
  private LinkedNode<MultipleChoiceQuestion> tail;// Tail of this singly linked list

  /**
   * this constructor creates a new empty instance of ListQuizzer which contains zero elements and
   * sets its listing mode to be ListingMode.ALL by default.
   */
  public ListQuizzer() {
    this.listingMode = listingMode.ALL;// ASK THE MEANING OF THIS LINE
    head = null;// setting head and tail to null
    tail = null;
    size = 0;
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the head of this list
   * 
   * @param question some MultipleChoiceQuestion to add to the head of this list
   * @throws NullPointerException with a descriptive error message if newQuestion is null
   */
  public void addFirst(MultipleChoiceQuestion question) throws NullPointerException {
    if (question == null) {
      throw new NullPointerException("question is invalid");
    }
    LinkedNode<MultipleChoiceQuestion> node1 = new LinkedNode<MultipleChoiceQuestion>(question);
    
    // case 1:linked list is null
    if (this.head == null) {
      this.head = node1;
      this.tail = node1;
      this.head.setNext(null);// set next of head ,after inserting the node, to null
    } else {
      LinkedNode<MultipleChoiceQuestion> next = head;// storing head node
      this.head = node1;// changing head node
      this.head.setNext(next);// changing next pointer of the head node
    }
    size++;
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the tail of this list
   * 
   * @param question some MultipleChoiceQuestion to add to the tail of this list
   * @throws NullPointerException with a descriptive error message if newQuestion is null
   */
  public void addLast(MultipleChoiceQuestion question) throws NullPointerException {
    if (question == null) {
      throw new NullPointerException("question is invalid");
    }
    LinkedNode<MultipleChoiceQuestion> node1 = new LinkedNode<MultipleChoiceQuestion>(question);
    if (tail == null) {
      this.tail = node1;
      this.head = node1;// if tail is null, then add at last
    } else {

      this.tail.setNext(node1);// setting given node as last node and as next of the previous tail
      this.tail = node1;// setting node
    }
    tail.setNext(null);// setting the next pointer of the new node to be null

    size++;
  }

  /**
   * Adds a specific MultipleChoiceQuestion to a given position of this list
   * 
   * @param index    position index where to add the newQuestion to this list
   * @param question some MultipleChoiceQuestion to add to this list
   * @throws NullPointerException      with a descriptive error message if newQuestion is null
   * @throws IndexOutOfBoundsException with a descriptive error message if index is OUT of the range
   *                                   0 .. size() inclusive
   */
  public void add(int index, MultipleChoiceQuestion question)
      throws NullPointerException, IndexOutOfBoundsException {
    LinkedNode<MultipleChoiceQuestion> node1 = new LinkedNode<MultipleChoiceQuestion>(question);
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("index is out of bounds");
    }
    if (question == null) {
      throw new NullPointerException("question is invalid");
    }
    if (index == 0) {
      addFirst(question);// adding at first index
    } else if (index == size) {
      addLast(question);
    } else {
      // write a recursive method to traverse the linked list
      // TODO:use this implementation even in removing the index ith element-only run till index-1
      // to get previous
      // index and then get current index and then remove it by also getting the next /succeeding
      // node
      // LinkedNode<MultipleChoiceQuestion> prev_node = traverse(index, head);//gets the node at
      // current index
      // where we will put some other node

      // LinkedNode<MultipleChoiceQuestion> prev_node = traverse(index, head);
      // LinkedNode<MultipleChoiceQuestion> next_node = prev_node.getNext();//previously defined
      // next node
      // prev_node.setNext(node1);//next pointer of prev node points to newly added node
      // node1.setNext(next_node);//next pointer of newly added node points to previously defined
      // next node
      // size++;//we don't add size++ after all conditions because then addLast and addFirst will
      // first increment
      // size in their call and ten again size will be increased
      LinkedNode<MultipleChoiceQuestion> prev_Node = head;
      for (int i = 0; i < index - 1; i++) {
        prev_Node = prev_Node.getNext();// for loop to get till a particular node
      }
      LinkedNode<MultipleChoiceQuestion> next_node = prev_Node.getNext();
      prev_Node.setNext(node1);
      node1.setNext(next_node);
      size++;
    }

  }

  /**
   * private helper method to get te node at a particular index
   * 
   * @param count    the index
   * @param currNode the current node
   * @return the ith index node
   */
  private LinkedNode<MultipleChoiceQuestion> traverse(int count,
      LinkedNode<MultipleChoiceQuestion> currNode) {
    // in ith iteration , we get the i+1 th element
    // base case:when count==0
    if (count == 0) {
      return currNode;
    } else {
      LinkedNode<MultipleChoiceQuestion> nextNode = currNode.getNext();// getting the next node of
                                                                       // the given node
      return traverse(count - 1, currNode);// recursive call with next node being the current node
    }
  }

  /**
   * Calculates the total points of the correctly answered questions of this ListQuizzer
   * 
   * @return the correct score
   */
  public int calculateScore() {
    // TODO:make an iterable appproach to traverse over all the nodes in the singly linked list
    // ListQuizzer
    // non iterable approach
    LinkedNode<MultipleChoiceQuestion> carrier = head;
    int sum = 0;
    while (carrier.getNext() != null)// goes till the second last node
    {
      carrier = carrier.getNext();
      MultipleChoiceQuestion mcq = carrier.getData();
      // if answer is correct, we add the possible marks of it to the sum
      if (mcq.isCorrect()) {// checking if question is correct
        sum += mcq.getPointsPossible();
      }
    }
    // at end of the while loop, we get the last node whose next node is null but we haven't checked
    // it for correctness
    // checking tail node
    if (carrier.getData().isCorrect()) {
      sum += carrier.getData().getPointsPossible();
    }
    return sum;
  }

  /**
   * Calculates the total possible points of this ListQuizzer
   * 
   * @return the total possible score of the quiz
   */
  public int calculateTotalPoints() {
    // TODO:make an iterable appproach to traverse over all the nodes in the singly linked list
    // ListQuizzer
    // non iterable approach
    LinkedNode<MultipleChoiceQuestion> carrier = head;
    int sum = 0;
    while (carrier.getNext() != null)// goes till the second last node
    {
      carrier = carrier.getNext();
      MultipleChoiceQuestion mcq = carrier.getData();
      // we add the possible marks of it to the sum

      sum += mcq.getPointsPossible();

    }
    // at end of the while loop, we get the last node whose next node is null but we haven't added
    // its possible
    // points to the total

    sum += carrier.getData().getPointsPossible();

    return sum;
  }

  /**
   * This method removes all the question from this list. The list should be empty after this method
   * is called.
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    size = 0;// setting size of linked list to be 0
  }

  /**
   * Checks whether this list is empty
   * 
   * @return true if this list is empty and false otherwise
   */
  public boolean isEmpty() {
    // TODO: CHECK THIS IMPLEMENTATION-BECAUSE IT TAKES THE 2 DIFFERENT CASES WHICH MAKES LINKED
    // LIST NOT VALID
    if (head == null || tail == null)// we used or to check some wierd cases whn head is defined but
                                     // tail not defined
    // or when head not defined but tail defined-in these 2 cases also, we should return true-empty
    // linked list

    {
      return true;
    }
    return false;
  }

  /**
   * Gets the size of this list
   * 
   * @return the size of this list
   */
  public int size() {
    return this.size;// return the current size
  }

  /**
   * Returns an iterator to iterate through this list with respect to the listingMode. If the
   * listingMode is ALL, the returned iterator is initialized to the head of this list. If the
   * listingMode is CORRECT, the returned iterator is initialized to the node carrying first
   * correctly answered question If the listingMode is INCORRECT, the returned iterator is
   * initialized to the node carrying first incorrectly answered question
   * 
   * @return an iterator to iterate through this list with respect to the listingMode of this list.
   */
  // TODO:ADD THREE IMPLEMENTING METHODS OF ITERABLE CLASS
  @Override
  public Iterator<MultipleChoiceQuestion> iterator() {
    if (listingMode == listingMode.ALL) {
      return new QuizQuestionsIterator(this.head);// returning the iterator with the head node
    } else if (listingMode == listingMode.CORRECT) {
      LinkedNode<MultipleChoiceQuestion> curNode = this.head;
      while (curNode != null) {
        if (curNode.getData().isCorrect()) {
          break;// getting first correct node
        }
        curNode = curNode.getNext();
      }
      return new CorrectQuestionsIterator(curNode);
    } else {
      LinkedNode<MultipleChoiceQuestion> curNode = this.head;
      while (curNode != null) {
        if (!curNode.getData().isCorrect()) {
          break;// getting first incorrect node
        }
        curNode = curNode.getNext();
      }
      return new IncorrectQuestionsIterator(curNode);// first incorrect node is given as argument
    }
  }

  /**
   * Returns the MultipleChoiceQuestion stored at the given index within this list
   * 
   * @param index index of the MultipleChoiceQuestion to return
   * @return the MultipleChoiceQuestion stored at the given index within this list
   * @throws IndexOutOfBoundsException if index is out of the range 0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("invalid index");
    } else {
      LinkedNode<MultipleChoiceQuestion> collect = traverse(index, head);// get to the getter index
                                                                         // node
      return collect.getData();// get the contents
    }
  }

  /**
   * Gets the MultipleChoiceQuestion at the head of this list
   * 
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getFirst() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("the linked list is empty");
    } else {
      return head.getData();// get the contents of the head node
    }
  }

  /**
   * Gets the MultipleChoiceQuestion at the tail of this list
   * 
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getLast() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("the linked list is empty");
    } else {
      return tail.getData();// get contents of the tail node
    }
  }

  /**
   * removes and returns the MultipleChoiceQuestion at the head of this list
   * 
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeFirst() throws NoSuchElementException {
    if (head == null || tail == null) {
      // TODO:check the or implementation for wrong cases
      throw new NoSuchElementException("the linked list is empty");

    }
    LinkedNode<MultipleChoiceQuestion> gethead = this.head;// get previous head
    if (size == 1)// case when we remove the only present node in the linked list
    {
      this.head = null;
      this.tail = null;

    } else {

      LinkedNode<MultipleChoiceQuestion> nexthead = gethead.getNext();// getting next element which
                                                                      // will become next head
      this.head = nexthead;// removed the previous head

    }
    size--;
    return gethead.getData();// returning the mcq in removed head node
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the tail of this list
   * 
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeLast() throws NoSuchElementException {
    if (head == null || tail == null) {
      // TODO:check the or implementation for wrong cases
      throw new NoSuchElementException("the linked list is empty");

    }
    LinkedNode<MultipleChoiceQuestion> gettail = this.tail;
    if (size == 1)// case when we remove the only present node in the linked list
    {
      this.head = null;
      this.tail = null;

    } else {

      // getting the previous element to the current tail
      LinkedNode<MultipleChoiceQuestion> prev_node = traverse(size - 2, head);
      // set the next pointer of prev_node to null-remove tail
      prev_node.setNext(null);
      // set tail pointer of linked list to prev_node
      this.tail = prev_node;
    }
    size--;
    return gettail.getData();

  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the given index
   * 
   * @param index index - of the MultipleChoiceQuestion to remove
   * @return the removed MultipleChoiceQuestion
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the range
   *                                   0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("invalid index");
    }
    if (index == 0) {
      // TODO: another method
      return removeFirst();
    } else if (index == size - 1) {
      // TODO:another method
      return removeLast();// return and remove the last node
    } else {
      // LinkedNode<MultipleChoiceQuestion> less_remove_node = traverse(index - 1, head);//go to 1
      // less element than the
      // //index of the element to be removed
      // LinkedNode<MultipleChoiceQuestion> remove_node = less_remove_node.getNext();
      // LinkedNode<MultipleChoiceQuestion> more_remove_node = remove_node.getNext();
      // //setting next pointer of 1 index less element to be node which is 1 index more of the node
      // to be removed
      // less_remove_node.setNext(more_remove_node);
      // size--;
      // return remove_node.getData();
      LinkedNode<MultipleChoiceQuestion> prev_Node = head;// store the head node to traverse through
                                                          // all the
      // upcoming nodes
      for (int i = 0; i < index - 1; i++) {
        prev_Node = prev_Node.getNext();
      }
      MultipleChoiceQuestion collect = prev_Node.getNext().getData();
      LinkedNode<MultipleChoiceQuestion> next_node = prev_Node.getNext().getNext();
      prev_Node.setNext(next_node);

      size--;
      return collect;
    }
  }

  public boolean contains(MultipleChoiceQuestion someQuestion) {
    // TODO:make a iterable approach of this method by implementing iterator()
    LinkedNode<MultipleChoiceQuestion> curNode = head;
    while (curNode != null) {
      MultipleChoiceQuestion ask = curNode.getData();
      if (ask.equals(someQuestion)) {
        return true;
      }
      curNode = curNode.getNext();
    }
    return false;
  }

  /**
   * returns a deep copy of this list. This method creates a copy of this list as a new ListQuizzer
   * and adds deep copies of each MultipleChoiceQuestion stored in this list to the deep copy
   * 
   * @return a deep copy of this list.
   */
  public ListQuizzer copy() {
    // TODO:make a iterable approach of this method by implementing iterator()
    ListQuizzer copy = new ListQuizzer();
    // we don't just copy the references,we make totally new seperate objects
    LinkedNode<MultipleChoiceQuestion> curNode = head;
    while (curNode != null) {
      MultipleChoiceQuestion mcq = curNode.getData();
      // LinkedNode<MultipleChoiceQuestion> new_node=new LinkedNode<MultipleChoiceQuestion>(mcq);
      // we have made a new node object-not just a seperate referene pointing to the same object in
      // the heap
      // adding this new node to the new linked list
      copy.addLast(mcq);
      curNode = curNode.getNext();// getting next node
    }
    return copy;
  }
  /*
   * public boolean equals(Object o) { //TODO:make a iterable approach of this method by
   * implementing iterator() //conditions- //same elements //same size //same head and tail //same
   * ordering of nodes if(o instanceof ListQuizzer) { if(((ListQuizzer) o).size()==this.size()) {
   * if(((ListQuizzer) o).getFirst().equals(this.getFirst()) && ((ListQuizzer)
   * o).getLast().equals(this.getLast())) { LinkedNode<MultipleChoiceQuestion> curNode=this.head;
   * LinkedNode<MultipleChoiceQuestion> curNode1=((ListQuizzer) o).head; while(curNode!=null) {
   * MultipleChoiceQuestion mcq=curNode.getData(); MultipleChoiceQuestion mcq1=curNode1.getData();
   * if (!mcq.equals(mcq1)) { return false; } } return true; } else{ return false;} } else{ return
   * false;} } else{ return false;} }
   * 
   */

  /**
   * Sets the listing mode of this list to the one provided as input
   * 
   * @param listingMode listing mode to set
   */
  public void switchMode(ListingMode listingMode) {
    this.listingMode = listingMode;// change mode
  }

  /*
   * public ListQuizzer takeQuiz() { return this.copy(); }
   * 
   */
  /**
   * Loads MultipleChoiceQuestions from a file
   *
   * @author Jeff and Mouna
   *
   * @param file file to read
   * @return the number of added MultipleChoiceQuestions to this list
   * @throws FileNotFoundException if the file is not found
   */
  public int loadQuestions(File file) throws FileNotFoundException {
    int loadedCount = 0;
    // try to read the file
    Scanner reader = null;
    try {
      reader = new Scanner(file);
      // parse the file lines line by line
      while (reader.hasNextLine()) {
        String title = reader.nextLine();
        String question = reader.nextLine();
        int answerCount = reader.nextInt();
        String[] answerList = new String[answerCount];
        int index = 0;
        while (!reader.hasNextInt()) {
          String answer = reader.nextLine();
          answerList[index] = answer;
          index++;
        }

        String line = reader.nextLine();
        Scanner lineScanner = new Scanner(line);
        int indexCorrectAnswer = lineScanner.nextInt();
        lineScanner.close();

        line = reader.nextLine();
        lineScanner = new Scanner(line);
        int points = lineScanner.nextInt();
        lineScanner.close();

        MultipleChoiceQuestion quizQuestion =
            new MultipleChoiceQuestion(title, question, answerList, indexCorrectAnswer, points);

        addLast(quizQuestion);
        loadedCount += 1;
      }
    } finally {
      if (reader != null)
        reader.close();
    }

    return loadedCount;
  }

  /**
   * Allows a user to take this quiz. The quiz should be taken on a deep copy of this ListQuizzer.
   * This method should not make any changes to the contents of this ListQuizzer.
   *
   * @author Jeff and Mouna
   *
   * @return the instance of ListQuizzer taken by the user. It should include the user's responses.
   */
  public ListQuizzer takeQuiz() {

    ListQuizzer copy = this.copy();
    switchMode(ListingMode.ALL);
    Scanner input = new Scanner(System.in);
    // we give a listquizzer object in enhanced for loop to iterate over
    // contents of listquizzer is a linked node with multiple choice question as its content
    for (MultipleChoiceQuestion question : copy) {
      System.out.println(question);
      System.out.print("Enter your answer: ");
      int entry = input.nextInt();
      question.setCorrectAnswerIndex(entry - 1);
      if (question.isCorrect()) {
        System.out.println("Correct!");
      } else {
        System.out.println("Incorrect!");
      }
    }
    int correctPoints = calculateScore();
    int totalPoints = calculateTotalPoints();
    System.out.println("Your Score: " + correctPoints);
    System.out.println("Percentage: " + correctPoints / totalPoints);
    input.close();
    return copy;
  }

  /**
   * Returns true if o is a ListQuizzer which has the exact same contents as this ListQuizzer
   *
   * @author Mouna
   *
   * @return true if o is instanceof ListQuizzer with the exact same contents as this ListQuizzer
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ListQuizzer) {
      ListQuizzer other = (ListQuizzer) o;
      if (this.size() != other.size())
        return false;
      this.switchMode(listingMode.ALL);
      other.switchMode(listingMode.ALL);
      Iterator<MultipleChoiceQuestion> iterator = this.iterator();
      Iterator<MultipleChoiceQuestion> otherIterator = other.iterator();
      while (iterator.hasNext()) {
        if (!iterator.next().equals(otherIterator.next()))
          return false;
      }
      return true;
    }
    return false;
  }
}

