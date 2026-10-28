import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Graph: an undirected, unweighted graph stored as an adjacency list.
 * Vertices are numbered 0 .. vertexCount-1. Each vertex has a list of neighbours.
 * Space: O(V + E). Traversals (BFS and DFS) run in O(V + E).
 * DFS uses our own LinkedStack, so the two structures work together.
 */
public class Graph {

    private final ArrayList<ArrayList<Integer>> adj;
    private final int vertexCount;
    private int edgeCount;

    public Graph(int vertexCount) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Graph needs at least 1 vertex");
        }
        this.vertexCount = vertexCount;
        adj = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public int vertexCount() { return vertexCount; }

    public int edgeCount() { return edgeCount; }

    private void checkVertex(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IndexOutOfBoundsException("No such vertex: " + v);
        }
    }

    /** Is there an edge u-v? O(degree(u)). */
    public boolean hasEdge(int u, int v) {
        checkVertex(u);
        checkVertex(v);
        return adj.get(u).contains(v);
    }

    /** Add undirected edge u-v. O(degree) because of the duplicate check. */
    public void addEdge(int u, int v) {
        checkVertex(u);
        checkVertex(v);
        if (u == v) {
            throw new IllegalArgumentException("Self-loops not allowed");
        }
        if (hasEdge(u, v)) {
            return;                       // ignore duplicates
        }
        adj.get(u).add(v);
        adj.get(v).add(u);
        edgeCount++;
    }

    /** Remove edge u-v if present. O(degree(u) + degree(v)). */
    public void removeEdge(int u, int v) {
        checkVertex(u);
        checkVertex(v);
        if (adj.get(u).remove(Integer.valueOf(v))) {
            adj.get(v).remove(Integer.valueOf(u));
            edgeCount--;
        }
    }

    /** Number of neighbours of v. O(1). */
    public int degree(int v) {
        checkVertex(v);
        return adj.get(v).size();
    }

    /** Breadth-first traversal: visits closest vertices first (uses a queue). O(V + E). */
    public ArrayList<Integer> bfs(int start) {
        checkVertex(start);
        ArrayList<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int u = queue.remove();
            order.add(u);
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return order;
    }

    /** Depth-first traversal: goes as deep as possible first (uses our LinkedStack). O(V + E). */
    public ArrayList<Integer> dfs(int start) {
        checkVertex(start);
        ArrayList<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount];
        LinkedStack stack = new LinkedStack();
        stack.push(start);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) continue;
            visited[u] = true;
            order.add(u);
            // push neighbours in reverse so the lowest-numbered one is explored first
            ArrayList<Integer> nb = adj.get(u);
            for (int i = nb.size() - 1; i >= 0; i--) {
                if (!visited[nb.get(i)]) {
                    stack.push(nb.get(i));
                }
            }
        }
        return order;
    }

    /** Is there any path from u to v? Uses BFS. O(V + E). */
    public boolean hasPath(int u, int v) {
        checkVertex(v);
        return bfs(u).contains(v);
    }

    /** Print the adjacency list. O(V + E). */
    public void display() {
        for (int i = 0; i < vertexCount; i++) {
            System.out.println("  " + i + " -> " + adj.get(i));
        }
        System.out.println("  (V = " + vertexCount + ", E = " + edgeCount + ")");
    }
}
