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
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is an iterator that moves through MultipleChoiceQuestion(s) in a singly linked list defined
 * by its head
 */
public class QuizQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> next;// stores the next variable we are currently
                                                  // referring to

  /**
   * Creates a new QuizQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements Parameters:
   * 
   * @param startNode pointer to the head of the singly linked list
   */
  public QuizQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    this.next = startNode;// sets the next node
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s).
   * 
   * @return true if there are more MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    // implementation 1

    // if(next.getNext()!=null)
    // {
    // return true;
    // }
    // return false;
    // implementation 2:
    return next != null;// returning if there is a next question
  }

  /**
   * Returns the next MultipleChoiceQuestion in this iteration
   * 
   * @return the next MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more questions
   *                                in this iteration
   */
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!this.hasNext())// checking if there are some questions left
    {
      throw new NoSuchElementException("no more questions in the quiz");
    } else {

      MultipleChoiceQuestion returnValue = next.getData();// getting the value stored in node
                                                          // referenced by next
      next = next.getNext();// setting next to next node
      return returnValue;
    }
  }
}

