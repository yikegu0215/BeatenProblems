package moBanMen;

public class TopologicalSort {

    //course schedule; DFS
    enum Status{
        VISITED, VISITING, INITIAL;
    }


    private class V{
        public int val;
        public List<Integer> nexts;
        public Status status;

        public V(int i){
            this.val = i;
            this.nexts = new ArrayList<>();
            this.status = Status.INITIAL;
        }

    }

    private int curLab;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //cc
        V[] array = new V[numCourses];
        for(int i = 0; i < numCourses; i++) {
            array[i] = new V(i);
        }
        for(int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int next = prerequisites[i][0];
            array[pre].nexts.add(next);
        }

        int[] res = new int[numCourses];

        curLab = numCourses - 1;
        for(int i = 0; i < numCourses; i++) {
            if(array[i].status == Status.INITIAL && !topo(res,array[i], array)) {
                return new int[0];
            }
        }

        return res;

    }

    private boolean topo(int[] res, V source, V[] array) {
        if(source.status == Status.VISITING) {
            return false;
        }
        if(source.status == Status.VISITED) {
            return true;
        }

        source.status = Status.VISITING;
        for( Integer next : source.nexts) {
            if(!topo(res, array[next], array)) {
                return false;
            }
        }
        source.status = Status.VISITED;
        res[curLab--] = source.val;
        return true;

    }

    //BFS
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            boolean isPossible = true;
            Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // Create the adjacency list representation of the graph
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0];
                int src = prerequisites[i][1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
                lst.add(dest);
                adjList.put(src, lst);

                // Record in-degree of each vertex
                indegree[dest] += 1;
            }

            // Add all vertices with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int i = 0;
            // Process until the Q becomes empty
            while (!q.isEmpty()) {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // Reduce the in-degree of each neighbor by 1
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--;

                        // If in-degree of a neighbor becomes 0, add it to the Q
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // Check to see if topological sort is possible or not.
            if (i == numCourses) {
                return topologicalOrder;
            }

            return new int[0];
        }
    }
}
