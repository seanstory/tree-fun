package com.sstory.tree

class Node {
    List<Node> children
    int val

    Node(int val){
        this.val = val
    }

    String toString(){
        return ""+val
    }
}
