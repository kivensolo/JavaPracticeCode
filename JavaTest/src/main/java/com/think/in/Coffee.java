//: generics/coffee/Coffee.java
package com.think.in;

public class Coffee {
  private static long counter = 0;
  private long id = counter++;
  public String toString() {
    return getClass().getSimpleName() + " " + id;
  }
} ///:~
