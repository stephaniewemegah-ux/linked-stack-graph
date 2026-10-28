/**
 * Main: demonstrates LinkedStack and Graph, including error cases.
 */
public class Main {

    public static void main(String[] args) {
        demoLinkedStack();
        System.out.println();
        demoGraph();
    }

    static void demoLinkedStack() {
        System.out.println("===== LINKED STACK DEMO =====");
        LinkedStack stack = new LinkedStack();
        System.out.println("New stack empty? " + stack.isEmpty());

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.print("After push 10, 20, 30: ");
        stack.display();

        System.out.println("peek() = " + stack.peek());
        System.out.println("pop()  = " + stack.pop());
        System.out.print("After pop: ");
        stack.display();

        // Practical use: reverse a word with a stack
        String word = "STACK";
        LinkedStack letters = new LinkedStack();
        for (char c : word.toCharArray()) letters.push(c);
        StringBuilder reversed = new StringBuilder();
        while (!letters.isEmpty()) reversed.append((char) letters.pop());
        System.out.println("Reverse \"" + word + "\" using the stack: " + reversed);

        // Error case
        stack.clear();
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println("Error caught: " + e.getMessage());
        }
    }

    static void demoGraph() {
        System.out.println("===== GRAPH DEMO =====");
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);       // vertex 5 is left isolated on purpose
        System.out.println("Adjacency list:");
        g.display();

        System.out.println("BFS from 0: " + g.bfs(0));
        System.out.println("DFS from 0: " + g.dfs(0));
        System.out.println("hasEdge(1, 3) = " + g.hasEdge(1, 3));
        System.out.println("degree(3)     = " + g.degree(3));
        System.out.println("hasPath(0, 4) = " + g.hasPath(0, 4));
        System.out.println("hasPath(0, 5) = " + g.hasPath(0, 5));

        g.removeEdge(3, 4);
        System.out.println("After removeEdge(3, 4), hasPath(0, 4) = " + g.hasPath(0, 4));

        try {
            g.addEdge(0, 9);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error caught: " + e.getMessage());
        }
    }
}
