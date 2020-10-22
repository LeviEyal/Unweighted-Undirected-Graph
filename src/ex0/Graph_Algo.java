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
        resetTags(0);
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

    private void resetTags(int t) {
        for(node_data n: g.getV())
            n.setTag(t);
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        if(src == dest) return 0;
        node_data a = g.getNode(src);
        node_data b = g.getNode(dest);
        if(a == null || b == null) return -1;

        resetTags(0);
        Queue<node_data> q = new ArrayBlockingQueue<>(g.nodeSize());
        q.add(g.getNode(src));
        while(!q.isEmpty()){
            node_data v = q.remove();
            for(node_data n: v.getNi()){
                if(n.getKey() == dest)
                    return v.getTag()+1;
                if(n.getTag() == 0){
                    q.add(n);
                    n.setTag(v.getTag()+1);
                }
            }
        }
        return -1;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if(src == dest) return null;
        node_data a = g.getNode(src);
        node_data b = g.getNode(dest);
        if(a == null || b == null) return null;

        resetTags(-1);
        Queue<node_data> q = new ArrayBlockingQueue<>(g.nodeSize());
        List<node_data> path = new ArrayList<>();
        q.add(g.getNode(src));
        while(!q.isEmpty()){
            node_data v = q.remove();
            for(node_data n: v.getNi()){
                if(n.getKey() == dest) {
                    n.setTag(v.getKey());
                    int t = n.getKey();
                    while(t != src){
                        path.add(g.getNode(t));
                        t = g.getNode(t).getTag();
                    }
                    Collections.reverse(path);
                    return path;
                }
                if(n.getTag() == -1){
                    q.add(n);
                    n.setTag(v.getKey());
                }
            }
        }
        return null;
    }
}
