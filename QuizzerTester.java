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
/**
 * This class checks the correctness of the an implementation of cs300 Fall 2022 p07 Quizzer
 */

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class QuizzerTester {
  /**
   *
   * Main method
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());// running all tests
    // ListQuizzer a=new ListQuizzer();
    // a.addLast(new MultipleChoiceQuestion("a","f",new String[]{"q","g"},1,5));
    // a.addLast(new MultipleChoiceQuestion("d","r",new String[]{"g","h"},0,7));
    // a.addLast(new MultipleChoiceQuestion("e","y",new String[]{"t","i"},0,6));
    // ListQuizzer b=new ListQuizzer();
    // b.addLast(new MultipleChoiceQuestion("a","f",new String[]{"q","g"},1,5));
    // b.addLast(new MultipleChoiceQuestion("d","r",new String[]{"g","h"},0,7));
    // b.addLast(new MultipleChoiceQuestion("e","y",new String[]{"t","i"},0,6));
    // System.out.println(a.equals(b));
  }

  public static boolean runAllTests() {
    // return true;

    return (
    // checking all tests
    testMultipleChoiceQuestion() && testLinkedNode() && testAddFirst() && testAddLast() && testAdd()
        && testRemoveFirst() && testRemoveLast() && testRemove() && testQuizQuestionsIterator()
        && testInCorrectQuestionsIterator() && testCorrectQuestionsIterator());


  }

  /**
   * This method test and make use of the MultipleChoiceQuestion constructor, an accessor (getter)
   * method, overridden method toString() and equal() method defined in the MultipleChoiceQuestion
   * class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMultipleChoiceQuestion() {
    // scenario 1:make faulty constructor call to make a faulty object-give null or empty title
    {
      MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion("", "question stem",
          new String[] {"option 1", "option 2"}, 1, 1);
      String gettitle = mcq1.getTitle();
      MultipleChoiceQuestion mcq2 = new MultipleChoiceQuestion(null, "question stem",
          new String[] {"option 1", "option 2"}, 1, 1);
      String gettitle2 = mcq2.getTitle();
      if (!gettitle.equals("") || gettitle2 != null)// checking 1st condition
      {
        return false;
      }
    }

    // scenario 2:make faulty constructor call to make a faulty object-give null or empty question
    // stem
    {
      MultipleChoiceQuestion mcq1 =
          new MultipleChoiceQuestion("question 1", "", new String[] {"option 1", "option 2"},
                  1, 1);
      String question = mcq1.getQuestion();
      MultipleChoiceQuestion mcq2 =
          new MultipleChoiceQuestion(null, null, new String[] {"option 1", "option 2"},
                  1, 1);
      String question2 = mcq2.getQuestion();
      if (!question.equals("") || question2 != null) {
        return false;
      }
    }

    // can't check following condition because we assume that no empty references are there in the
    // answers array
    // we also can't check by giving a null instead of an array-no try catch is used
    // scenario 3:make faulty constructor call to make a faulty object-give null or empty array of
    // answers
    {
      // MultipleChoiceQuestion mcq1=new MultipleChoiceQuestion("question 1","",new String[]{},
      // 1,1);
      // String answers=mcq1.getAnswers();
      // MultipleChoiceQuestion mcq1=new MultipleChoiceQuestion("question 1","",null,
      // 1,1);
    }
    // scenario 4:make faulty constructor call to make a faulty object-give out of bounds index for
    // right answer
    {
      // when correctAnswersIndex<1 or it is greater than length
      try {
        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion("question 1", "",
            new String[] {"option 1", "option 2"}, 2, 1);
        return false;
        // only operates if exception is npt thrown-in ideal condition we want an exception to be
        // thrown
      } catch (IndexOutOfBoundsException e) {

      }
    }

    // scenario 5:make faulty constructor call to make a faulty object-give less than 0 valued
    // possible points
    {
      try {
        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion("question 1", "",
            new String[] {"option 1", "option 2"}, 0, -1);
        return false;
      } catch (IllegalArgumentException e) {

      }
    }

    // scenario 6:working constructor
    {
      try {
        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion("question 1", "",
            new String[] {"option 1", "option 2"}, 1, 1);
        String title = mcq1.getTitle();
        String question = mcq1.getQuestion();
        if (!title.equals("question 1") || !question.equals("")) {
          return false;
        }
      } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
        return false;
      }
    }

    // scenario 7:checking if deep copy is made
    {
      MultipleChoiceQuestion mcq1 =
          new MultipleChoiceQuestion("question 1", "", new String[] {"option 1", "option 2"},
                  1, 1);
      MultipleChoiceQuestion copy = mcq1.copy();
      if (mcq1 == copy || !mcq1.equals(copy)) {
        return false;
      }
    }
    // scenario 8:checking getAnswers method
    {
      MultipleChoiceQuestion mcq1 =
          new MultipleChoiceQuestion("question 1", "", new String[] {"option 1", "option 2"},
                  1, 1);
      String collect = mcq1.getAnswers();
      String compare = "1. option 1" + "\n" + "2. option 2";
      if (!compare.equals(collect)) {
        return false;
      }
    }
    // scenario 9:getting correct answer's index,possible points,student's answer,title
    // getter,isCorrect,
    // setCorrectAnswerIndex
    {
      MultipleChoiceQuestion mcq1 =
          new MultipleChoiceQuestion("question 1", "", new String[] {"option 1",
                  "option 2"}, 1, 1);
      int sa = mcq1.getStudentAnswerIndex();
      try {
        mcq1.setStudentAnswerIndex(4);
        return false;
      } catch (IndexOutOfBoundsException e) {

      }
      try {
        mcq1.setStudentAnswerIndex(1);

      }

      catch (IndexOutOfBoundsException e) {
        return false;
      }
      // System.out.println(sa);
      // in this case, the correct index is equal to the index of student's answer
      if (mcq1.getCorrectAnswerIndex() != 1 || mcq1.getPointsPossible() != 1
          || sa == mcq1.getStudentAnswerIndex() || !mcq1.getTitle().equals("question 1")
          || !mcq1.isCorrect()) {
        return false;
      }

      mcq1.setCorrectAnswerIndex(0);// here correct index!=student's answer index
      if (mcq1.isCorrect()) {
        return false;
      }
      mcq1.setTitle("question different");
      if (mcq1.getTitle().equals("question 1")) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method test and make use of the LinkedNode constructor, an accessor (getter) method, and a
   * mutator (setter) method defined in the LinkedNode class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {
    // scenario 1:when data is null-default constructor
    {
      try {
        LinkedNode<MultipleChoiceQuestion> check1 = new LinkedNode<MultipleChoiceQuestion>(null);

        return false;
      } catch (NullPointerException e) {
      }
    }

    // scenario 2:when everything is good-default constructor
    {
      try {
        LinkedNode<MultipleChoiceQuestion> check1 =
            new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 1", "",
                new String[] {"option 1", "option 2"}, 1, 1));
        if (check1.getNext() != null
            || !check1.getData().equals(new MultipleChoiceQuestion("question 1", "",
                new String[] {"option 1", "option 2"}, 1, 1))) {
          return false;// give false if condition is not true and wrong
        }
      } catch (NullPointerException e) {
        return false;
      }

    }
    // scenario 3:when everything is good-parameterized constructor
    {
      try {
        // making nodes
        LinkedNode<MultipleChoiceQuestion> check1 = new LinkedNode<MultipleChoiceQuestion>(
            new MultipleChoiceQuestion("question 1", "", new String[] {"option 1",
                    "option 2"}, 1,
                1),
            new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 2",
                    "",
                new String[] {"option 1", "option 2"}, 1, 2)));
        if (!check1.getData()
            .equals(new MultipleChoiceQuestion("question 1", "",
                new String[] {"option 1", "option 2"}, 1, 1))
            || !check1.getNext().getData().equals(new MultipleChoiceQuestion("question 2",
                "",
                new String[] {"option 1", "option 2"}, 1, 2))) {
          return false;
        }
      } catch (NullPointerException e) {
        return false;
      }
    }
    // scenario 4:when data is null-parameterized constructor
    {
      try {
        // getting node
        LinkedNode<MultipleChoiceQuestion> check1 = new LinkedNode<MultipleChoiceQuestion>(null,
            new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 2",
                    "",
                new String[] {"option 1", "option 2"}, 1, 2)));

        return false;
      } catch (NullPointerException e) {

      }
    }
    // scenario 5:setting a new node-successfully added-but when no next node was there previously
    {
      LinkedNode<MultipleChoiceQuestion> check1 =
          new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 1", "",
              new String[] {"option 1", "option 2"}, 1, 1));
      LinkedNode<MultipleChoiceQuestion> get1 = check1.getNext();
      check1.setNext(new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 2",
          "", new String[] {"option 1", "option 2"}, 1, 2)));
      if (get1 != null
          || !check1.getNext().getData().equals(new MultipleChoiceQuestion("question 2", "",
              new String[] {"option 1", "option 2"}, 1, 2))) {
        return false;
      }
    }
    // scenario 6:setting successfully when next node was previously there
    {
      LinkedNode<MultipleChoiceQuestion> check1 = new LinkedNode<MultipleChoiceQuestion>(
          new MultipleChoiceQuestion("question 1", "", new String[] {"option 1",
                  "option 2"}, 1, 1),
          new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 2", "",
              new String[] {"option 1", "option 2"}, 1, 2)));
      LinkedNode<MultipleChoiceQuestion> get1 = check1.getNext();
      check1.setNext(new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("question 3",
          "set question stream", new String[] {"option 1", "option 2"},
              1, 5)));
      if (get1.getData().equals(check1.getNext().getData())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tester for ListQuizzer.addFirst() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFirst() {
    // TODO:ALSO CHECK IF AFTER ADDING, THE NODES CORRECTLY POINT TO EACH OTHER
    ListQuizzer lq = new ListQuizzer();// making a new linked list
    // scenario 1:when question is null
    {
      try {
        lq.addFirst(null);
        return false;
      } catch (NullPointerException e) {
        if (lq.size() > 0) {
          return false;
        }
      }
    }
    // scenario 2:when question is not null
    {
      try {
        lq.addFirst(new MultipleChoiceQuestion("Question 1", "who are you",
            new String[] {"option 1", "option 2"}, 1, 5));
        if (lq.size() != 1) {
          return false;
        }
      } catch (NullPointerException e) {
        return false;
      }
    }
    return true;
  }

  public static boolean testAddLast() {
    // TODO:ALSO CHECK IF AFTER ADDING, THE NODES CORRECTLY POINT TO EACH OTHER
    ListQuizzer lq = new ListQuizzer();// making a new linked list
    // scenario 1:when question is null
    {
      try {
        lq.addLast(null);
        return false;
      } catch (NullPointerException e) {
        if (lq.size() > 0) {
          return false;
        }
      }
    }
    // scenario 2:when question is not null
    {
      try {
        lq.addLast(new MultipleChoiceQuestion("Question 1", "who are you",
            new String[] {"option 1", "option 2"}, 1, 5));
        if (lq.size() != 1) {
          return false;
        }
      } catch (NullPointerException e) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tester for ListQuizzer.add() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAdd() {
    ListQuizzer lq1 = new ListQuizzer();

    lq1.addFirst(new MultipleChoiceQuestion("qq4", "how", new String[] {"aa", "bb"},
            1, 4));
    lq1.addFirst(new MultipleChoiceQuestion("qq3", "how1", new String[] {"aa", "bb"},
            0, 4));
    lq1.addFirst(new MultipleChoiceQuestion("qq2", "how", new String[] {"aa", "bb",
            "cc"}, 1, 5));
    lq1.addFirst(new MultipleChoiceQuestion("qq1", "how", new String[] {"aa",
            "bb"}, 0, 3));
    // System.out.println(lq1.size());
    // lq1.add(2, new MultipleChoiceQuestion("qq2+","how",new String[]{"aa","bb"},0,3));
    // System.out.println(lq1.size());//should give 4
    // senario 1:when we throw nullpointerexception
    {

      try {
        lq1.add(0, null);
        // System.out.println(lq1.size());
        return false;
      } catch (NullPointerException e) {
        if (lq1.size() != 4) {
          // System.out.println(lq1.size());
          return false;
        }
      }
    }

    // scenario 2:when we throw indexoutofboundsexception
    {
      try {
        lq1.add(5, new MultipleChoiceQuestion("", "", new String[]
                {"aa"}, 0, 3));
      } catch (IndexOutOfBoundsException e) {
        if (lq1.size() != 4) {
          return false;
        }
      }
    }

    // scenario 3:when we add at end-index=size
    {
      try {
        // System.out.println(lq1.size());
        lq1.add(4, new MultipleChoiceQuestion("", "", new String[]
                {"aa"}, 0, 3));
        // System.out.println(lq1.size());//should give 5-gives 6 because size first increased in
        // addLast and then
        // after the if condition
        if (lq1.size() != 5) {
          return false;
        }
      } catch (IndexOutOfBoundsException | NullPointerException e) {
        return false;
      }
    }

    // scenario 4:when we add in middle
    {
      // TODO:CHECK ALSO THE PREVIOUS NODE AND NEXT NODES' POINTERS AFTER THE ADDITION IN MIDDLE
      // right now,
      // the linked list has 5 elements-we try to add at some position
      try {
        // System.out.println(lq1.size());
        // testing also that tail didn't change and nor did the head
        MultipleChoiceQuestion head = lq1.getFirst();
        MultipleChoiceQuestion tail = lq1.getLast();
        lq1.add(1, new MultipleChoiceQuestion("", "", new String[]
                {"aa"}, 0, 3));
        // System.out.println(lq1.size());
        if (lq1.size() != 6 || head != lq1.getFirst() || tail != lq1.getLast()) {


          return false;
        }
      } catch (IndexOutOfBoundsException | NullPointerException e) {

        return false;
      }
    }


    return true;
  }

  /**
   * Tester for ListQuizzer.removeFirst() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveFirst() {
    // scenario 1:when linked list is not empty
    {
      ListQuizzer lq = new ListQuizzer();
      MultipleChoiceQuestion collect =
          new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t"},
                  1, 9);
      lq.addLast(collect);
      lq.addLast(new MultipleChoiceQuestion("qq", "q", new String[] {"s1", "t"},
              0, 9));
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t1"},
              1, 8));
      MultipleChoiceQuestion prev_head = lq.getFirst();

      int size = lq.size();
      try {
        MultipleChoiceQuestion collect2 = lq.removeFirst();
        if (lq.size() == size || lq.getFirst().equals(prev_head) || !collect.equals(collect2)) {
          return false;// in case element removed but size not decremented or the head is till the
                       // same
        }
      } catch (NoSuchElementException e) {
        return false;
      }
    }
    // scenario 2:when linked list is empty
    {
      ListQuizzer lq = new ListQuizzer();// empty linked list
      try {
        lq.removeFirst();
        return false;
      } catch (NoSuchElementException e) {

      }
    }
    return true;
  }

  /**
   * Tester for ListQuizzer.removeLast() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveLast() {
    // scenario 1:when linked list is not empty
    {
      ListQuizzer lq = new ListQuizzer();
      MultipleChoiceQuestion collect =
          new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t1"},
                  1, 8);
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t"}
              , 1, 9));
      lq.addLast(new MultipleChoiceQuestion("qq", "q", new String[] {"s1", "t"}
              , 0, 9));
      lq.addLast(collect);
      MultipleChoiceQuestion prev_tail = lq.getLast();

      int size = lq.size();
      try {
        MultipleChoiceQuestion collect2 = lq.removeLast();
        if (lq.size() == size || lq.getLast().equals(prev_tail) || !collect.equals(collect2)) {
          return false;// in case element removed but size not decremented or the head is till the
                       // same
        }
      } catch (NoSuchElementException e) {
        return false;
      }
    }
    // scenario 2:when linked list is empty
    {
      ListQuizzer lq = new ListQuizzer();// empty linked list
      try {
        lq.removeLast();
        return false;
      } catch (NoSuchElementException e) {

      }
    }
    return true;
  }

  /**
   * Tester for ListQuizzer.remove() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {

    // scenario 1:when index is out of bounds
    {
      ListQuizzer lq = new ListQuizzer();
      MultipleChoiceQuestion collect =
          new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t"},
                  1, 9);
      lq.addLast(collect);
      lq.addLast(new MultipleChoiceQuestion("qq", "q", new String[] {"s1",
              "t"}, 0, 9));
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s",
              "t1"}, 1, 8));
      int size = lq.size();
      try {
        MultipleChoiceQuestion collect2 = lq.remove(3);// removing indexed node
        return false;
      } catch (IndexOutOfBoundsException e) {
        if (lq.size() != size) {
          return false;
        }
      }
    }
    // scenario 2:when we remove at first index
    {
      ListQuizzer lq = new ListQuizzer();
      MultipleChoiceQuestion collect =
          new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t"},
                  1, 9);
      lq.addLast(collect);
      lq.addLast(new MultipleChoiceQuestion("qq", "q", new String[] {"s1",
              "t"}, 0, 9));
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s",
              "t1"}, 1, 8));
      int size = lq.size();
      MultipleChoiceQuestion collect2 = lq.remove(0);
      if (lq.size() != size - 1 || !collect.equals(collect2)) {
        return false;
      }
    }
    // scenario 3:when we remove at last index
    {
      ListQuizzer lq = new ListQuizzer();
      MultipleChoiceQuestion collect =
          new MultipleChoiceQuestion("q", "qq", new String[] {"s", "t1"},
                  1, 8);
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s",
              "t"}, 1, 9));
      lq.addLast(new MultipleChoiceQuestion("qq", "q", new String[] {"s1",
              "t"}, 0, 9));
      lq.addLast(collect);
      int size = lq.size();
      MultipleChoiceQuestion collect2 = lq.remove(size - 1);// removing node at index
      if (lq.size() != size - 1 || !collect.equals(collect2)) {
        return false;
      }
    }


    // scenario 4:when we remove from middle
    {
      // TODO:check next pointers of elements around the removed element
      ListQuizzer lq = new ListQuizzer();
      MultipleChoiceQuestion collect =
          new MultipleChoiceQuestion("qq", "q", new String[] {"s1", "t"},
                  0, 9);
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s",
              "t"}, 1, 9));
      lq.addLast(collect);
      lq.addLast(new MultipleChoiceQuestion("q", "qq", new String[] {"s",
              "t1"}, 1, 8));
      int size = lq.size();
      MultipleChoiceQuestion collect2 = lq.remove(1);// removing node
      // System.out.println(collect.toString());
      // System.out.println(collect2.toString());
      if (lq.size() != size - 1 || !collect2.equals(collect2)) {
        return false;
      }
    }
    return true;
  }
  // public static boolean testQuizQuestionsIterator()
  // {
  // //TODO:complete this method
  // ListQuizzer lq1=new ListQuizzer();
  // LinkedNode<MultipleChoiceQuestion> n1=new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("a","b",new String[]{"g","H"},1,7));
  // LinkedNode<MultipleChoiceQuestion> n2=new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("a","p",new String[]{"j","H"},0,2));
  // LinkedNode<MultipleChoiceQuestion> n3=new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("k","b",new String[]{"o","p"},1,8));
  // n1.setNext(n2);
  // n2.setNext(n3);
  //
  // lq1.addLast(new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("a","b",new String[]{"g","H"},1,7));
  // lq1.addLast(new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("a","p",new String[]{"j","H"},0,2));
  // lq1.addLast(new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("k","b",new String[]{"o","p"},1,8));
  // QuizQuestionsIterator qqi1=new QuizQuestionsIterator(n1);
  //
  //
  //
  //
  // return false;
  // }
  // public static boolean testQuizQuestionsIterator()
  // {
  // ListQuizzer lq1=new ListQuizzer();
  // lq1.addLast(new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("a","b",new String[]{"g","H"},1,7));
  // lq1.addLast(new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("a","p",new String[]{"j","H"},0,2));
  // lq1.addLast(new LinkedNode<MultipleChoiceQuestion>
  // (new MultipleChoiceQuestion("k","b",new String[]{"o","p"},1,8));
  //
  // }

  /**
   * This method checks for the correctness of QuizQuestionsIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testQuizQuestionsIterator() {
    try {
      MultipleChoiceQuestion mcq1=new MultipleChoiceQuestion("q1", "question 1",
              new String[] {"option1 ", "option 2"}, 0, 5);
      LinkedNode<MultipleChoiceQuestion> node1 =
          new LinkedNode<MultipleChoiceQuestion>(mcq1);
      node1.getData().setStudentAnswerIndex(0);
      MultipleChoiceQuestion mcq2=new MultipleChoiceQuestion("q2", "question 2",
              new String[] {"option3 ", "option 4"}, 1, 5);
      LinkedNode<MultipleChoiceQuestion> node2 =
          new LinkedNode<MultipleChoiceQuestion>(mcq2);
      node1.getData().setStudentAnswerIndex(0);
      MultipleChoiceQuestion mcq3=new MultipleChoiceQuestion("q3", "question 3",
              new String[] {"option5 ", "option 6"}, 0, 5);
      LinkedNode<MultipleChoiceQuestion> node3 =
          new LinkedNode<MultipleChoiceQuestion>(mcq3);
      node1.getData().setStudentAnswerIndex(0);
      LinkedNode<MultipleChoiceQuestion> node4 =
          new LinkedNode<MultipleChoiceQuestion>(new MultipleChoiceQuestion("q4", "question 4",
              new String[] {"option7 ", "option 8"}, 1, 5));
      node1.getData().setStudentAnswerIndex(0);
      node1.setNext(node2);
      node2.setNext(node3);
      node3.setNext(node4);// setting nodes in a linked list style


      // scenario 1:when linked list is not empty
      {
        /*
         * ListQuizzer lq1 = new ListQuizzer(); lq1.addLast(new MultipleChoiceQuestion("q1",
         * "question 1", new String[]{"option1 ", "option 2"}, 0, 5)); lq1.addLast(new
         * MultipleChoiceQuestion("q2", "question 2", new String[]{"option3 ", "option 4"}, 1, 5));
         * lq1.addLast(new MultipleChoiceQuestion("q3", "question 3", new String[]{"option5 ",
         * "option 6"}, 0, 5)); lq1.addLast(new MultipleChoiceQuestion("q4", "question 4", new
         * String[]{"option7 ", "option 8"}, 1, 5));
         */
        // making iterator
        Iterator<MultipleChoiceQuestion> qqi = new QuizQuestionsIterator(node1);
        MultipleChoiceQuestion check1 = qqi.next();
        // System.out.println(check1.toString());
        if (!check1.equals(new MultipleChoiceQuestion("q1", "question 1",
            new String[] {"option1 ", "option 2"}, 0, 5))) {
          return false;
        }

        MultipleChoiceQuestion check2 = qqi.next();
        // System.out.println(check2.toString());
        if (!check2.equals(new MultipleChoiceQuestion("q2", "question 2",
            new String[] {"option3 ", "option 4"}, 1, 5))) {
          return false;
        }
        MultipleChoiceQuestion check3 = qqi.next();
        // System.out.println(check3.toString());
        if (!check3.equals(new MultipleChoiceQuestion("q3", "question 3",
            new String[] {"option5 ", "option 6"}, 0, 5))) {
          return false;
        }
        MultipleChoiceQuestion check4 = qqi.next();
        // System.out.println(check4.toString());
        if (!check4.equals(new MultipleChoiceQuestion("q4", "question 4",
            new String[] {"option7 ", "option 8"}, 1, 5))) {
          return false;
        }
        try {
          MultipleChoiceQuestion check5 = qqi.next();
          return false;
        } catch (NoSuchElementException e) {

        }
      }

      // scenario 2:when linked list is empty
      {


        Iterator<MultipleChoiceQuestion> qqi = new QuizQuestionsIterator(null);
        try {
          MultipleChoiceQuestion check1 = qqi.next();

          return false;
        } catch (NoSuchElementException e) {

        }
      }


    } catch (Exception e) {
      return false;
    }

    // test on Nodes
    try {
      MultipleChoiceQuestion mcq1=new MultipleChoiceQuestion("q1", "question 1",
              new String[] {"option1 ", "option 2"}, 0, 5);
      LinkedNode<MultipleChoiceQuestion> node1 =
              new LinkedNode<MultipleChoiceQuestion>(mcq1);
      node1.getData().setStudentAnswerIndex(0);
      MultipleChoiceQuestion mcq2=new MultipleChoiceQuestion("q2", "question 2",
              new String[] {"option3 ", "option 4"}, 1, 5);
      LinkedNode<MultipleChoiceQuestion> node2 =
              new LinkedNode<MultipleChoiceQuestion>(mcq2);
      node2.getData().setStudentAnswerIndex(0);
      MultipleChoiceQuestion mcq3=new MultipleChoiceQuestion("q3", "question 3",
              new String[] {"option5 ", "option 6"}, 0, 5);
      LinkedNode<MultipleChoiceQuestion> node3 =
              new LinkedNode<MultipleChoiceQuestion>(mcq3);
      QuizQuestionsIterator q = new QuizQuestionsIterator(node1);

      if (!q.hasNext() || !q.next().equals(mcq1) || q.hasNext()) {
        return false;
      }
    }
    catch (Exception e){ // if any exception occurs
      return false; // one of the methods didn't work correctly and threw exception
    }

    return true;
  }

  /**
   * This method checks for the correctness of InCorrectQuestionsIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInCorrectQuestionsIterator() {
    // making 4 nodes
    MultipleChoiceQuestion mcq1 =
        new MultipleChoiceQuestion("q1", "question 1", new String[]
                {"option1 ", "option 2"}, 0, 5);
    MultipleChoiceQuestion mcq2 =
        new MultipleChoiceQuestion("q2", "question 2", new String[]
                {"option3 ", "option 4"}, 1, 5);
    MultipleChoiceQuestion mcq3 =
        new MultipleChoiceQuestion("q3", "question 3", new String[]
                {"option5 ", "option 6"}, 0, 5);
    MultipleChoiceQuestion mcq4 =
        new MultipleChoiceQuestion("q4", "question 4", new String[]
                {"option7 ", "option 8"}, 1, 5);
    mcq1.setStudentAnswerIndex(0);
    mcq2.setStudentAnswerIndex(1);
    mcq3.setStudentAnswerIndex(1);
    mcq4.setStudentAnswerIndex(1);
    ListQuizzer lq1 = new ListQuizzer();
    lq1.addLast(mcq1);
    lq1.addLast(mcq2);
    lq1.addLast(mcq3);
    lq1.addLast(mcq4);
    lq1.switchMode(ListingMode.INCORRECT);// setting the incorrect mode
    Iterator<MultipleChoiceQuestion> it = lq1.iterator();
    if (!it.hasNext()) {
      return false;

    }

    try {
      MultipleChoiceQuestion collect = it.next();
      if (!collect.equals(mcq3)) {
        return false;
      }
    }

    catch (Exception e) {
      return false;
    }


    if (it.hasNext()) {
      return false;
    }
    try {
      MultipleChoiceQuestion collect2 = it.next();
      return false;
    } catch (NoSuchElementException e) {

    }
    catch (Exception e){
      return false;
    }

    // test on Nodes
    try {
      LinkedNode<MultipleChoiceQuestion> link1 = new LinkedNode<>(mcq1);
      LinkedNode<MultipleChoiceQuestion> link2 = new LinkedNode<>(mcq2);
      LinkedNode<MultipleChoiceQuestion> link3= new LinkedNode<>(mcq3);
      link1.setNext(link2);
      link2.setNext(link3);
      IncorrectQuestionsIterator wrong = new IncorrectQuestionsIterator(link1);

      if (!wrong.hasNext() || !wrong.next().equals(mcq3) || wrong.hasNext()) {
        return false;
      }
    }
    catch (Exception e){ // if any exception occurs
    return false; // one of the methods didn't work correctly and threw exception
    }


    return true;
  }

  /**
   * This method checks for the correctness of CorrectQuestionsIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */

  public static boolean testCorrectQuestionsIterator() {
    // making 4 nodes
    MultipleChoiceQuestion mcq1 =
        new MultipleChoiceQuestion("q1", "question 1", new String[]
                {"option1 ", "option 2"}, 0, 5);
    // MultipleChoiceQuestion mcq2 = new MultipleChoiceQuestion("q2", "question 2", new
    // String[]{"option3 ", "option 4"}, 1, 5);
    MultipleChoiceQuestion mcq3 =
        new MultipleChoiceQuestion("q3", "question 3", new String[]
                {"option5 ", "option 6"}, 0, 5);
    MultipleChoiceQuestion mcq4 =
        new MultipleChoiceQuestion("q4", "question 4", new String[]
                {"option7 ", "option 8"}, 1, 5);
    mcq1.setStudentAnswerIndex(0);// correct
    // mcq2.setStudentAnswerIndex(1);//correct
    mcq3.setStudentAnswerIndex(1);// incorrect
    mcq4.setStudentAnswerIndex(0);// incorrect
    ListQuizzer lq1 = new ListQuizzer();
    lq1.addLast(mcq1);
    // lq1.addLast(mcq2);
    lq1.addLast(mcq3);
    lq1.addLast(mcq4);
    lq1.switchMode(ListingMode.CORRECT);// setting the correct mode
    Iterator<MultipleChoiceQuestion> it = lq1.iterator();
    if (it.hasNext() == false) {
      return false;

    }

    try {
      MultipleChoiceQuestion collect = it.next();
      // System.out.println(collect);
      if (!collect.equals(mcq1)) {
        return false;
      }
    }

    catch (NoSuchElementException e) {
      return false;
    }

    if (it.hasNext() == true)// check
    {
      return false;
    }
    try {
      MultipleChoiceQuestion collect2 = it.next();
      return false;
    } catch (NoSuchElementException e) {

    }

    // test on Nodes
    try {
      LinkedNode<MultipleChoiceQuestion> link1 = new LinkedNode<>(mcq1);
      LinkedNode<MultipleChoiceQuestion> link2 = new LinkedNode<>(mcq3);
      LinkedNode<MultipleChoiceQuestion> link3= new LinkedNode<>(mcq4);
      link1.setNext(link2);
      link2.setNext(link3);
      CorrectQuestionsIterator correct = new CorrectQuestionsIterator(link1);

      if (!correct.hasNext() || !correct.next().equals(mcq1) || correct.hasNext()) {
        return false;
      }
    }
    catch (Exception e){ // if any exception occurs
      return false; // one of the methods didn't work correctly and threw exception
    }

    return true;
  }
}
