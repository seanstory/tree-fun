package com.sstory.tree

import spock.lang.Specification

class MainTest extends Specification {

    def "test tree algorithm"(){
        when: "simple case"
        Node root = new Node(1)
        Node c1 = new Node( 2)
        Node c2 = new Node( 3)

        root.children = [c1, c2]
        def result = Main.getLongestInOrder(root)

        then:
        result == [root, c1]

        when: "more complex"
        Node c3 = new Node(2)
        Node c4 = new Node(3)
        Node c5 = new Node(2)

        c1.children = [c3, c4]
        c3.children = [c5]
        result = Main.getLongestInOrder(root)

        then:
        result.val == [1,2,2,2]


        when: "changing direction"
        Node c6 = new Node(1)
        Node c7 = new Node(0)
        c5.children = [c6]
        c6.children = [c7]
        result = Main.getLongestInOrder(root)

        then:
        result.val == [2,2,2,1,0]


    }
}
