import java.util.EmptyStackException;
import java.util.Stack;

public class StackCount {

    public static void main(String args[]){

        Stack<Integer> stack = new Stack<>();    



        /**R-6.1 Suppose an initially empty stack S has performed a total of 25 push operations,
12 top operations, and 10 pop operations, 3 of which returned null to indicate an
empty stack. What is the current size of S? */

        System.out.println("Stack Size: " +stack.size());
        
        //3 null stack pops
        for (int i=0; i < 3; i++){
            try{
            System.out.println(stack.pop());
            } catch (EmptyStackException e){
                //this is expected.
            };
        }
        //total of 25 push operations
        for (int j=0; j < 25; j++){
            stack.push(j);
        }
         //and... 10 top operations.
        for (int k=0; k < 10; k++){
            stack.lastElement(); // equivalent last element
        } 

                //7 more pops
                for (int i=0; i < 7; i++){
                    try{
                    System.out.println(stack.pop());
                    } catch (EmptyStackException e){
                        //this is expected.
                    };
                }

        System.out.println("Stack Size: " +stack.size());



    }

}


