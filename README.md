# Java Program to Create a Subclass in a User-Defined Package

## Aim
To create a user-defined package in Java and demonstrate inheritance by creating a subclass within the same package.

## Program

```java
package mypackage;

// Parent Class
class ParentClass {
    public void showParent() {
        System.out.println("This is the Parent Class.");
    }
}

// Child Class
class ChildClass extends ParentClass {
    public void showChild() {
        System.out.println("This is the Child Class (Subclass of ParentClass).");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        ChildClass obj = new ChildClass();

        obj.showParent();
        obj.showChild();
    }
}
