package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph_DS implements graph{

    private HashMap<Integer, node_data> vertices;
    private int edges = 0;
    private int nodeSize = 0;
    private int mc = 0;

    public Graph_DS() {
        vertices = new HashMap<>();
    }

    public Graph_DS(graph other) {
        vertices = new HashMap<>();
        //TODO
    }

    @Override
    public node_data getNode(int key) {
        try {
            return vertices.get(key);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        return getNode(node1).hasNi(node2);
    }

    @Override
    public void addNode(node_data n) {
        if(!vertices.containsValue(n)){
            vertices.put(n.getKey(), n);
            nodeSize++;
            mc++;
        }
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
        return vertices.values();
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        return vertices.get(node_id).getNi();
    }

    @Override
    public node_data removeNode(int key) {
        if(vertices.containsKey(key)){
            node_data t = vertices.get(key);
            LinkedList<node_data> t2 = (LinkedList<node_data>) t.getNi();
            while(!t.getNi().isEmpty())
                removeEdge(t2.getFirst().getKey(),key);
//            vertices.get(key).getNi().clear();
            vertices.remove(key);
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
        for(int key: vertices.keySet()){
            s += key+": "+vertices.get(key).getNi()+"\n";
        }
        return s;
    }
}
