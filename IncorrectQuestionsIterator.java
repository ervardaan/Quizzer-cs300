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
/**
 * Implements an iterator to iterate over incorrectly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IncorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> next;// refers to a node in the singly linked list of
  // MultipleChoiceQuestion

  /**
   * Creates a new InCorrectQuestionsIterator to start iterating through a singly linked list
   * storing MultipleChoiceQuestion elements
   * 
   * @param startNode pointer to the head of the singly linked list
   */
  public IncorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    this.next = startNode;// sets the next node
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered incorrectly.
   * 
   * @return true if there are more incorrect MultipleChoiceQuestion(s) in this iteration
   */
  public boolean hasNext() {
    // implementation 1
    LinkedNode<MultipleChoiceQuestion> curNode = next;
    while (curNode != null) {
      if (!curNode.getData().isCorrect())// checking if more incorrect answers are there and find
                                         // the first one
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
   * Returns the next incorrect MultipleChoiceQuestion in this iteration
   * 
   * @return the next incorrect MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more incorrect
   *                                questions in this iteration
   */
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (this.hasNext()) {
      int index = 0;
      while (next.getData().isCorrect()) {
        next = next.getNext();// gets the first incorrect node
        index++;
      }
      MultipleChoiceQuestion returnValue = next.getData();// getting the value stored in node
                                                          // referenced by next

      next = next.getNext();// setting next to next node


      return returnValue;
    } else {
      throw new NoSuchElementException("no more incorrect questions in the quiz");
    }
    // implementation 2:
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
