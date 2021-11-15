package moBanMen;

import java.util.*;

public class Dijastra {
    class Pair{
        Node node;
        double prob;
        public Pair(Node node, double prob){
            this.node = node;
            this.prob = prob;
        }
    }

    class Node{
        int val;
        List<Pair> neighbors;

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Node[] graph = new Node[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new Node(i);
        }
        for(int i = 0; i < edges.length; i++) {
            int[] oneEdge = edges[i];
            double oneProb = succProb[i];
            Node curOne = graph[oneEdge[0]];
            Node curTwo = graph[oneEdge[1]];
            curOne.neighbors.add(new Pair(curTwo, oneProb));
            curTwo.neighbors.add(new Pair(curOne, oneProb));
        }
        PriorityQueue<Pair> heap = new PriorityQueue<>(
                (a,b) -> (((Double)b.prob).compareTo((Double)a.prob))
                //    new Comparator<Pair>() {
                //     public int compare(Pair a, Pair b) {
                //         if(a.prob < b.prob) return 1;
                //         else if(a.prob > b.prob) return -1;
                //         return 0;
                //     }
                // }
        );
        Node startPoint = graph[start];
        Node endPoint = graph[end];
        heap.add(new Pair(startPoint, 1));
        boolean[] visited = new boolean[n];
        double result = -1.0;
        while(!heap.isEmpty()) {
            Pair cur = heap.poll();
            if(cur.node == endPoint) {
                result = Math.max(cur.prob, result);
            }
            visited[cur.node.val] = true;
            for(Pair next : cur.node.neighbors){
                if(!visited[next.node.val]){
                    heap.offer(new Pair(next.node, next.prob * cur.prob));
                }
            }

        }
        return result == -1? 0 : result;

    }
}
