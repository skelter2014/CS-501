import java.util.Iterator;

//import cs501.LinkedBinaryTree.Node;
import cs501.interfaces.Position;

/*p-8.65    Write a program that takes as input a fully parenthesized , 
arithmetic expression and convert it to a binary expression  tree. 
Your program should display the tree in some way and also print the value associated with the root. 

For an additional challenge , allow the leaves to store variables of X1, X2 , X3 and so on, 
which are initially 0 and which can be updated interactively by your program, with the corresponding 
update in the printed value of the root of the expression tree.

https://www.javatpoint.com/convert-infix-to-postfix-notation#:~:text=Rules%20for%20the%20conversion%20from%20infix%20to%20postfix%20expression&text=If%20the%20incoming%20symbol%20is,push%20it%20on%20the%20stack.
Assumptions: 
    1. expressions use variables (a,b,x,y) as operands 
    2. chars [* / + - ] as operators which have L - R associativity
    3, Parentheses are used to determine order of operations but are not part of the postfix final expression
    4. All expressions are valid with invalid chars. - error checking can be added later. Spaces are ignored
    5. Operands are single chars
the order of the postfix expression 

    Example:
        Infix   = (A+B)*(C-D)/E
        Postfix = AB+CD-*E/

                    *
                +        /
              A   B     -   E
                      C  D
   Convert Postfix to Tree
        1. convert to postfix L,R, Root - 
        2. create a leaf node for each operand you find and push onto stack
        3. When you read an operator, create a node - this a a new root of a sub tree
            3.a Pop the top of the stack and add as right child of operator node
            3b. Pop the next node on the stack (now the top) and add as a right child of the operator sub-tree
        4. push the new subtree back onto the stack. - this becomes the new left (node)
*/

public class Assignment_5 {

    private static int COUNT = 10;

    public static void main(String[] args) {

        String expA = "(A+B)*((C-D)/E)";
        String expB = "c/d * ((a+b) / (x+y))";
        String expC = "c+d/e-f+g-(h+i)*j/(k+l)";

        // convert expA
        char[] infixArrayA = expA.toCharArray();
        char[] postFixArrayA = convertToPostFix(infixArrayA);
        // convert expB
        char[] infixArrayB = expB.toCharArray();
        char[] postFixArrayB = convertToPostFix(infixArrayB);
        // convert expc
        char[] infixArrayC = expC.toCharArray();
        char[] postFixArrayC = convertToPostFix(infixArrayC);

        LinkedBinaryTree<Character> treeA = convertToTree(postFixArrayA);
        LinkedBinaryTree<Character> treeB = convertToTree(postFixArrayB);
        LinkedBinaryTree<Character> treeC = convertToTree(postFixArrayC);

        System.out.println("-------------------------------------------------------------------------");
        System.out.print("infix: ");
        System.out.println(infixArrayA );
        System.out.println("root: " + treeA.root.getElement());
        System.out.println("-----------------------------");
        printVerticalTree(treeA.root, 0);

        System.out.println("-------------------------------------------------------------------------");
        System.out.print("infix: ");
        System.out.println(infixArrayB);
        System.out.println("root: " + treeB.root.getElement());
        System.out.println("-----------------------------");
        printVerticalTree(treeB.root, 0);
        System.out.println("-------------------------------------------------------------------------");
        System.out.print("infix: ");
        System.out.println(infixArrayC);
        System.out.println("root: " + treeC.root.getElement());
        System.out.println("-----------------------------");
        printVerticalTree(treeC.root,0);
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * Converts an infix char[] array to postfix.
     */
    public static char[] convertToPostFix(char[] expr) {
        ArrayStack<Character> stack = new ArrayStack<Character>();

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < expr.length; i++) {
            char c = expr[i];
            // ignore spaces
            if (Character.isSpaceChar(c)) {
                continue;
            }
            // Print incoming operands immediately
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else {
                // if stack is empty or has '(' on top
                if (stack.isEmpty() || stack.top() == '(') {
                    stack.push(c);
                }
                // start of parentheses
                else if ('(' == c) {
                    stack.push(c);
                    // End parentheses
                } else if (')' == c) {
                    while (stack.top() != '(') {
                        // print any operators between the two parens
                        result.append(stack.pop());
                    }
                    stack.pop(); // pop the remaining paren
                }
                // incoming operator has higher prededence
                else if (isHigher(c, stack.top())) {
                    stack.push(c);
                }
                // incoming operator has lower precedence.
                else if (isLower(c, stack.top())) {
                    while (!stack.isEmpty() && (isLower(c, stack.top())
                            || isEqual(c, stack.top()))) {
                        result.append(stack.pop());
                    }
                    stack.push(c); // push the lower operator onto the stack
                }
                // incoming operator has same precedence
                else if (isEqual(c, stack.top())) {
                    result.append(stack.pop());
                    stack.push(c);
                }
            }

        }
        // add any remaining operators on the stack to the post-fix expression.
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString().toCharArray();
    }

    /**
     * The LAST operator processed with be the root in postfix notation (L,R,Root)
     */
    public static LinkedBinaryTree<Character> convertToTree(char[] postFix) {

        LinkedBinaryTree.Node<Character> right;
        LinkedBinaryTree.Node<Character> left;
        LinkedBinaryTree.Node<Character> root = null;

        // The expression tree
        LinkedBinaryTree<Character> tree = new LinkedBinaryTree<>();

        // a stack to hold references to nodes of the tree
        ArrayStack<Position<Character>> stack = new ArrayStack<Position<Character>>();

        for (int i = 0; i < postFix.length; i++) {
            char c = postFix[i];

            // push letters or numbers directly to the stack
            if (Character.isLetterOrDigit(c)) {
                LinkedBinaryTree.Node<Character> node = new LinkedBinaryTree.Node<>(c, null, null, null);
                stack.push(node);
            }
            // when it is a operator, create a subtree and put back on stack
            else {
                right = (LinkedBinaryTree.Node<Character>) stack.pop();
                left = (LinkedBinaryTree.Node<Character>) stack.pop();
                root = new LinkedBinaryTree.Node<Character>(c, null, left, right);
                // push the root of the subtree back on the stack
                stack.push(root);
            }
        }

        tree.setRoot(root);
        tree.setSize(tree.numChildren(root));
        return tree;

    }

    /**
     * 
     * @return - Returns an integer signfying whether the incoming operator is
     *         higher,lower or equal in precidence to c1
     *         to the second operator
     *         result = 0 or 1 - c1 and c2 are equal precedence
     *         result >=2. - c1 operator is HIGHER than c2
     *         result < -1 - c1 is LOWER than
     * 
     *         0 1 2 3 4
     *         + - * /
     */
    private static int getPrededence(char c1, char c2) {
        final String operators = "+- */";
        return operators.indexOf(c1) - operators.indexOf(c2);
    }

    // incoming operator index is higher (to the right) of top of stack
    public static boolean isHigher(char c1, char c2) {
        return getPrededence(c1, c2) > 1;
    }

    // incoming operator index is lower (to the left) of top of stack
    public static boolean isLower(char c1, char c2) {
        return getPrededence(c1, c2) < 0;
    }

    // diff between operator indexes is 0 or 1; ex. +- are only 1 index apart
    public static boolean isEqual(char c1, char c2) {
        int precedence = getPrededence(c1, c2);
        return precedence >= 0 && precedence <= 1;
    }

    // Function to print binary tree
    // It does reverse inorder traversal
    static void printVerticalTree(LinkedBinaryTree.Node<Character> root, int space) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        printVerticalTree(root.getRight(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.getElement() + "\n");

        // Process left child
        printVerticalTree(root.getLeft(), space);
    }
}