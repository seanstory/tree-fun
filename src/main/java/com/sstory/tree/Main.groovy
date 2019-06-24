package com.sstory.tree

class Main {

    static List<Node> getLongestInOrder(Node root){
        List<Node> longest = []
        for(Node child : root.children){
            def childLongest = recurse(longest, [root], child, true)
            if(childLongest.size() > longest.size()){
                longest = childLongest
            }
        }
        return longest
    }

    //recursive helper
    private static List<Node> recurse(List<Node> longest, List<Node> curPath, Node curNode, boolean increasing){
        if(curNode == null){
            return longest
        } else {
            if(increasing && isAdjacentIncreasing(curPath[-1], curNode)){
                curPath.add(curNode)
            } else if (!increasing && isAdjacentDecreasing(curPath[-1], curNode)){
                curPath.add(curNode)
            } else { //changing direction
                int lastVal = curPath[-1].val
                List<Node> lastNWithSameValue = []
                for(int i = curPath.size()-1; i>=0; i--){
                    if(curPath[i].val == lastVal){
                        lastNWithSameValue.add(curPath[i])
                    }
                }
                curPath = lastNWithSameValue.reverse()
                if(increasing && isAdjacentDecreasing(curPath[-1], curNode)){ //now decreasing
                    curPath.add(curNode)
                    increasing = false
                } else if(!increasing && isAdjacentIncreasing(curPath[-1], curNode)) { //now increasing
                    curPath.add(curNode)
                    increasing = true
                } else { //not consecutive in any direction - new curPath
                    curPath = [curNode]
                }
            }

            if(curPath.size() > longest.size()){
                longest = curPath // need to copy this?
            }

            for(Node child : curNode.children){
                def childLongest = recurse(longest, new ArrayList<Node>(curPath), child, increasing)
                if(childLongest.size() > longest.size()){
                    longest = childLongest
                }
            }
            return longest
        }
    }

    private static boolean isAdjacentIncreasing(Node first, Node next){
        return first.val == next.val || first.val == next.val - 1
    }

    private static boolean isAdjacentDecreasing(Node first, Node next){
        return first.val == next.val || first.val == next.val + 1
    }
}
