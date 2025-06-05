// June 5, 2025 – GFG PoTD 2.0

// 🧩 Question: Count the Paths in a DAG
/*
You're given a **Directed Acyclic Graph (DAG)** with V vertices and E edges.
You need to count the number of **distinct paths** from a **source node** (src) to a **destination node** (dest).

The graph is represented using an edge list, where each edge is a pair [u, v] representing a directed edge from u → v.

Example:
Input: edges = [[0,1], [0,3], [2,0], [2,1], [1,3]], V = 4, src = 2, dest = 3
Output: 3
Explanation: Three valid paths from 2 to 3:
- 2 → 1 → 3
- 2 → 0 → 3
- 2 → 0 → 1 → 3
*/


// ✅ Approach:
/*
We follow 3 main steps:
1. **Build the graph** from the given edge list using adjacency list.
2. **Topological sort** the graph using Kahn's Algorithm.
3. **Dynamic Programming on DAG**:
   Traverse the nodes in reverse topological order and use DP to count the number of ways to reach `dest` from each node.

Why reverse topo order?
- Because we solve subproblems for children before parents.
*/

class Solution {
    public int countPaths(int[][] edges, int V, int src, int dest) {
        
        // Step 1: Build the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[V];

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.get(u).add(v);
            indegree[v]++;
        }

        // Step 2: Topological Sort using Kahn’s Algorithm
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);
            for (int neighbor : graph.get(node)) {
                if (--indegree[neighbor] == 0) q.offer(neighbor);
            }
        }

        // Step 3: DP to count paths from dest backwards
        int[] dp = new int[V];
        dp[dest] = 1;

        for (int i = topo.size() - 1; i >= 0; i--) {
            int u = topo.get(i);
            for (int v : graph.get(u)) {
                dp[u] += dp[v];
            }
        }

        return dp[src];
    }
}
