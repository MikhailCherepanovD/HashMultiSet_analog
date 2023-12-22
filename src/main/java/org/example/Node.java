package org.example;

public class Node<T> {
    protected T key;
    protected int amount;
    public Node(T key){
        this.key=key;
        amount=1;

    }
}
