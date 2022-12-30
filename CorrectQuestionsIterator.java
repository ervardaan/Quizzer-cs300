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
// Persons: NONE
// Online Sources: NONE
//
////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator to iterate over correctly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 */
public class CorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> next;// refers to a node in the singly linked list of
  // MultipleChoiceQuestion to traverse

  /**
   * Creates a new CorrectQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements
   * 
   * @param startNode pointer to the head of the singly linked list
   */
  public CorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    this.next = startNode;// sets the next node
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered correctly.
   * 
   * @return true if there are more correct MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    /*
     * //implementation 1 // LinkedNode<MultipleChoiceQuestion> curNode=next.getNext(); //
     * while(curNode!=null) // { // if(curNode.getData().isCorrect()) // { // return true; // } //
     * curNode=curNode.getNext(); // } // return false; //implementation 2 return next!=null &&
     * next.getData().isCorrect();
     */
    LinkedNode<MultipleChoiceQuestion> curNode = next;
    while (curNode != null) {
      if (curNode.getData().isCorrect())// checking if next correct answer exists
      {
        return true;
      }
      curNode = curNode.getNext();
    }
    return false;
    // implementation 2
    // return next!=null && !next.getData().isCorrect();
  }

  /**
   * Returns the next correct MultipleChoiceQuestion in this iteration
   * 
   * @return the next correct MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more correct
   *                                questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException

  {
    /*
     * if(next!=null && next.getData().isCorrect()) { MultipleChoiceQuestion collect=
     * next.getData(); while(this.hasNext() && !next.getData().isCorrect())
     * 
     * { next=next.getNext(); } return collect; } else{ throw new
     * NoSuchElementException("no more questions in list"); }
     * 
     */
    if (this.hasNext()) {
      // int index=0;

      // next=next.getNext();


      while (!next.getData().isCorrect()) {
        next = next.getNext();// gets the first incorrect node
        // index++;
      }
      MultipleChoiceQuestion returnValue = next.getData();// getting the value stored in node
                                                          // referenced by next

      next = next.getNext();// setting next to next node


      return returnValue;
    } else {
      throw new NoSuchElementException("no more correct questions in the quiz");
    }

    // if(this.hasNext())
    // {
    // while(!next.getData().isCorrect())
    // {
    // next=next.getNext();//gets the first correct node
    // }
    // MultipleChoiceQuestion returnValue= next.getData();//getting the value stored in node
    // referenced by next
    // next=next.getNext();//setting next to next node
    // return returnValue;
    // }
    // else{
    // throw new NoSuchElementException("no more questions in the quiz");
    // }
    // implementation 2
    // if(this.hasNext())
    // {
    // LinkedNode<MultipleChoiceQuestion> curNode=next.getNext();
    // while(curNode!=null)
    // {
    // if(!curNode.getData().isCorrect())
    // {
    // break;
    // }
    // curNode=curNode.getNext();
    // }
    // return curNode.getData();
    // }
    // else{
    // throw new NoSuchElementException("no more incorrect questions in the quiz");
    // }
  }
}
