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
 * An instance of this class represents a single node within a singly linked list.
 * 
 * @param <T> a generic class type
 */
public class LinkedNode<T> {
  private T data;// data carried by this linked node
  private LinkedNode<T> next;// reference to the next linked node

  /**
   * Constructs a new node with a specific data and NO next node in the list.
   * 
   * @param data to be stored within this node
   * @throws NullPointerException with a descriptive error message if data is null
   */
  public LinkedNode(T data) {
    if (data == null)// checking if data is not null
    {
      throw new NullPointerException("data  is empty");
    }
    this.data = data;
    // check-maybe not needed
    this.next = null;
  }

  /**
   * Constructs a new node with the provided information.
   * 
   * @param data to be stored within this node
   * @param next node, which comes after this node in a singly linked list
   * @throws NullPointerException with a descriptive error message if data is null
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    if (data == null) {
      throw new NullPointerException("data  is empty");
    }
    this.data = data;
    this.next = next;// becomes the next node
  }

  /**
   * Accessor method for this node's next node reference.
   * 
   * @return next the next reference to the node that comes after this one in the list, or null when
   *         this is the last node in that list
   */
  public LinkedNode<T> getNext() {
    return next;// return the next node
  }

  /**
   * Mutator method for this node's next node reference.
   * 
   * @param next node, which comes after this node in a singly linked list
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;// set new next node
  }

  /**
   * Accessor method for this node's data.
   * 
   * @return the data stored within this node.
   */
  public T getData() {
    return this.data;// return the data of current node
  }

  /**
   * Returns a string representation of this linked node formatted as follows- data.toString() if
   * this node does NOT have a next node in the list data.toString() + "->" if this node has a next
   * node in the list
   * 
   * @return a String representation of this node in the list
   */
  @Override
  public String toString() {
    if (this.getNext() == null) {
      return this.data.toString();
      // toString method used on a data type in data variable-overridden method
      // definition used of that class
    } else {
      return this.data.toString() + "->";// when there is a next node present
    }
  }
}
