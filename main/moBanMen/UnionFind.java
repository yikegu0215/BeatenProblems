package moBanMen;

public class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            for(int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
        }

        public int find(int x) {
            if(parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px == py) {
                return false;
            }else if(rank[px] > rank[py]) {
                parent[py] = px;
            }else if (rank[py] < rank[px]) {
                parent[px] = py;
            }else{
                parent[py] = px;
                rank[px]++;
            }
            return true;
        }



    public int[] findRedundantConnection(int[][] edges) {
        UnionFind dsu = new UnionFind(edges.length + 1);
        for(int[] edge : edges) {
            if(!dsu.union(edge[0],edge[1])) {
                return edge;
            }
        }
        return new int[2];
    }
}
