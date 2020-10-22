package ex0;

import java.util.List;
import java.util.Random;

public class Graph_Ex0_Test {
    static int seed = 3;
    static Random _rnd = new Random(seed);
    static int v_size = 10;
    static int e_size = v_size*3;
    static graph g0 = new Graph_DS(), g1;
    static graph_algorithms ga;

    public static void main(String[] args) {
        test1();
        System.out.println("g0:\n"+g0);
        test2();
        System.out.println("g0:\n"+g0);
//        test3();
//        System.out.println(g0);
    }
    public static void test1() {

        for(int i=0;i<v_size;i++) {
            node_data n = new NodeData();
            g0.addNode(n);
        }
        while(g0.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            g0.connect(a,b);
        }
         System.out.println("finish");
    }
    public static void test2() {
        g0.removeEdge(2,3);
        g0.removeEdge(9,3);
        g0.removeNode(0);
        g0.removeNode(0);
        g0.addNode(new NodeData());
        g0.removeNode(2);
        g0.removeNode(8);
        g0.connect(6,10);
    }
    public static void test3() {
        ga = new Graph_Algo(g0);
        g1 = ga.copy();
        ga.init(g1);
//        g1.addNode(new NodeData());
//        g1.connect(10,9);
//        g0.removeNode(5);
        boolean isConnected = ga.isConnected();
        System.out.println("g0:\n"+g0);
        System.out.println("g1:\n"+g1+"\nis connected: "+isConnected);
//        int dist19 = ga.shortestPathDist(1,9);
        int dist32 = ga.shortestPathDist(3,2);
//        System.out.println("shortest path: 1,9 dist="+dist19);
        System.out.println("shortest path: 3,10 dist="+dist32);
        List<node_data> sp = ga.shortestPath(3,2);
        System.out.println(sp);
    }
    public static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    public static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
}
