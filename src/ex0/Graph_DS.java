package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents an undirected unweighted graph.
 * The implementation based on adjacency list representation -
 * created with HashMap data structure to increase accessing vertices speed.
 * The class implements the interface graph -
 * for more documentation visit this interface.
 */
public class Graph_DS implements graph{

    private final HashMap<Integer, node_data> v;
    private int edges = 0;
    private int nodeSize = 0;
    private int mc = 0;

    public Graph_DS() {
        v = new HashMap<>();
    }

    /**
     * Creates a new graph that is a deep copy of a given other graph.
     * The constructor copy each node of the other graph to the new graph
     * and than seek for any possible edge in the other graph and
     * connect the parallel nodes in the new graph.
     * The method runs in O(n^2) time.
     * @param other graph to copy from
     */
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

    /**
     * return the node_data by the node_id.
     * The method runs in O(1) time.
     * @param key the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return v.get(key);
    }

    /**
     * Checks if two given nodes are connected.
     * The method runs in O(1) time.
     * @param node1 the first node's key
     * @param node2 the second node's key
     * @return true if the two nodes are connected.
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        node_data n = getNode(node1);
        if(n != null)
            return n.hasNi(node2);
        return false;
    }

    /**
     * Adds a given node to the graph.
     * If the node is already in the graph then
     * the method does nothing.
     * The method runs in O(1) time.
     * @param n The node to be added to the graph
     */
    @Override
    public void addNode(node_data n) {
        if(v.putIfAbsent(n.getKey(), n) == null){
            nodeSize++;
            mc++;
        }
    }

    /**
     * Connects two given nodes by their key.
     * If they already connected, the method does nothing.
     * The method runs in O(1) time.
     * @param node1 The first node's key
     * @param node2 The second node's key
     */
    @Override
    public void connect(int node1, int node2) {
        node_data n1 = getNode(node1);
        node_data n2 = getNode(node2);
        if(n1==null || n2==null || node1==node2) return;
        if(!hasEdge(node1,node2)){
            n1.addNi(n2);
            n2.addNi(n1);
            edges++;
            mc++;
        }
    }

    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * The method runs in O(1) time.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return v.values();
    }

    /**
     * Returns a collection representing all the nodes connected to node associated with key.
     * The method runs in O(1) time.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(int key) {
        return v.get(key).getNi();
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
            node_data n1 = getNode(node1);
            node_data n2 = getNode(node2);
            n1.removeNode(n2);
            n2.removeNode(n1);
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
        for(int key: v.keySet())
            s += key + ": " + v.get(key).getNi() + "\n";
        return s;
    }
}
