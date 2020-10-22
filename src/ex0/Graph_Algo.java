package ex0;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Graph_Algo implements graph_algorithms{

    private graph g;

    public Graph_Algo(graph g0) {
        this.g = g0;
    }

    @Override
    public void init(graph g) {
        this.g = g;
    }

    @Override
    public graph copy() {
        return new Graph_DS(this.g);
    }

    @Override
    public boolean isConnected() {
        resetTags();
        Queue<node_data> q = new ArrayBlockingQueue<>(g.nodeSize()+10);
        Iterator<node_data> it = g.getV().iterator();
        node_data v = it.next();
        q.add(v);
        while(!q.isEmpty()){
            v = q.remove();
            for(node_data n: v.getNi()){
                if(n.getTag() == 0){
                    q.add(n);
                    n.setTag(1);
                }
            }
        }
        for(node_data n: g.getV())
            if(n.getTag() == 0)
                return false;
        return true;
    }

    private void resetTags() {
        for(node_data n: g.getV())
            n.setTag(0);
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        resetTags();
        Queue<node_data> q = new ArrayBlockingQueue<>(g.nodeSize()+10);
        NodeData t = (NodeData) g.getNode(src);
        t.setDistance(0);
        q.add(g.getNode(src));
        while(!q.isEmpty()){
            node_data v = q.remove();
//            System.out.print(v+" -> ");
            for(node_data n: v.getNi()){
                if(n.getKey() == dest) return 0;
                if(n.getTag() == 0){
                    q.add(n);
                    n.setTag(1);
                }
            }
        }
        return -1;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        resetTags();
        Queue<node_data> q = new ArrayBlockingQueue<>(g.nodeSize()+10);
        List<node_data> path = new ArrayList<>();
//        path.add(g.getNode(src));
        q.add(g.getNode(src));
        int level=0;
        while(!q.isEmpty()){
            node_data v = q.remove();
            path.add(v);
//            System.out.print(v+" -> ");
            for(node_data n: v.getNi()){
                if(n.getKey() == dest) return path;
                if(n.getTag() == 0){
                    q.add(n);
                    n.setTag(1);
                }
            }
        }
        path.clear();
        return path;
    }
}
