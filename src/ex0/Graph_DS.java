package ex0;

import java.util.Collection;
import java.util.HashMap;

public class Graph_DS implements graph{

    private HashMap<Integer, node_data> v;
    private int edges = 0;
    private int nodeSize = 0;
    private int mc = 0;

    public Graph_DS() {
        v = new HashMap<>();
    }

    public Graph_DS(graph other) {
        edges = other.edgeSize();
        nodeSize = other.nodeSize();
        v = new HashMap<>();
        for(node_data n : other.getV()){
            v.put(n.getKey(), new NodeData(n));
        }
        for(int i: v.keySet()){
            for (int j: v.keySet()){
                if(other.hasEdge(i,j))
                    connect(i,j);
            }
        }
    }

    @Override
    public node_data getNode(int key) {
        return v.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        return getNode(node1).hasNi(node2);
    }

    @Override
    public void addNode(node_data n) {
        v.putIfAbsent(n.getKey(), n);
        nodeSize++;
        mc++;
    }

    @Override
    public void connect(int node1, int node2) {
        if(!hasEdge(node1,node2) && node1!=node2){
            getNode(node1).addNi(getNode(node2));
            getNode(node2).addNi(getNode(node1));
            edges++;
            mc++;
        }
    }

    @Override
    public Collection<node_data> getV() {
        return v.values();
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        return v.get(node_id).getNi();
    }

    @Override
    public node_data removeNode(int key) {
        if(v.containsKey(key)){
            node_data t = v.get(key);
            Collection<node_data> t2 = t.getNi();
            while(!t.getNi().isEmpty())
                removeEdge(t2.iterator().next().getKey(),key);
            v.remove(key);
            mc++;
            nodeSize--;
            return t;
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if(hasEdge(node1,node2)){
            getNode(node1).removeNode(getNode(node2));
            getNode(node2).removeNode(getNode(node1));
            mc++;
            edges--;
        }
    }

    @Override
    public int nodeSize() {
        return nodeSize;
    }

    @Override
    public int edgeSize() {
        return edges;
    }

    @Override
    public int getMC() {
        return mc;
    }

    @Override
    public String toString() {
        String s = "";
        for(int key: v.keySet()){
            s += key+": "+ v.get(key).getNi()+"\n";
        }
        return s;
    }
}
