package LinkedList.Skiplist;
import java.util.*;


class Skiplist {

    public class Node{
        int val;
        Node next;
        Node above;
        Node below;
        Node prev;

        Node(int val){
            this.val=val;
            this.next=null;
            this.prev=null;
            this.above=null;
            this.below=null;

        }
    }

    Node head;
    Node tail;
    int level;

    Random rand;

    public Skiplist() {
        this.head=new Node(-(int)1e9);
        this.tail=new Node((int)1e9);
        this.level=0;
        this.head.next=this.tail;
        this.tail.prev=this.head;
        rand=new Random();
    }
    
    public boolean search(int target) {
        Node node=find(target);
        return node.val==target;
    }

    public Node find(int tar){
        Node temp=this.head;
        Node retPtr=null;
        while(temp!=null){
            while(temp.next!=null && temp.next.val<=tar){
                temp=temp.next;
            }
            retPtr=temp;
            temp=temp.below;
        }
        return retPtr;

    }
    
    public void add(int num) {
        Node node=find(num);
      /// if(node.val==num) return; -> need multiple entrie in the leetcode q https://leetcode.com/problems/design-skiplist/

        Node newNode=new Node(num);

        Node nextNode=node.next;
        newNode.next=nextNode;
        nextNode.prev=newNode;

        node.next=newNode;
        newNode.prev=node;

        Node ptr=newNode;

        Node temp=newNode;

        int currLevel=0;

        while(rand.nextBoolean()==true){
            checkforNewLevel(currLevel);

            while(temp.above==null){
                temp=temp.prev;
            }

            temp=temp.above;

            while(temp.next!=null && temp.next.val<=num){
                temp=temp.next;
            }

            Node newlevelNode=new Node(num);

            Node newlevelnext=temp.next;

            temp.next=newlevelNode;
            newlevelNode.prev=temp;

            newlevelNode.next=newlevelnext;
            newlevelnext.prev=newlevelNode;

            newlevelNode.below=ptr;
            ptr.above=newlevelNode;

            ptr=newlevelNode;

            temp=newlevelNode;

            currLevel++;

        }
    }

    public void checkforNewLevel(int currLevel){
        if(currLevel>=this.level){
            addNewLevel();
        }

        return;
    }

    public void addNewLevel(){
        Node newHead=new Node(-(int)1e9);
        Node newTail=new Node((int)1e9);

        newHead.next=newTail;
        newTail.prev=newHead;
        
        Node oldHead=this.head;
        Node oldTail=this.tail;

        newHead.below=oldHead;
        newTail.below=oldTail;

        oldHead.above=newHead;
        oldTail.above=newTail;

        this.head=newHead;
        this.tail=newTail;

        this.level++;
    }
    
    public boolean erase(int num) {
        Node node=find(num);
        if(node.val!=num) return false;

        while(node!=null){
            Node nextNode=node.next;
            Node prevNode=node.prev;

            prevNode.next=nextNode;
            nextNode.prev=prevNode;


            Node aboveNode=node.above;

            node.above=null; 
            node.below=null;

           

            node=aboveNode;

        }

        return true;
    }
}



