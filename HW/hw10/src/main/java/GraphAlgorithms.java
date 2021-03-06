import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Charlie Comeau
 * @userid ccomeau7
 * @GTID 903359699
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex cannot be null!");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Graph does not "
                    + "contain start vertex!");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        List<Vertex<T>> result = new ArrayList<>();
        Queue<Vertex<T>> bfsQueue = new LinkedList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        bfsQueue.add(start);
        while (!bfsQueue.isEmpty()) {
            Vertex<T> remove = ((LinkedList<Vertex<T>>) bfsQueue).removeFirst();
            if (!visitedSet.contains(remove)) {
                result.add(remove);
                visitedSet.add(remove);
                List<VertexDistance<T>> neighborList = adjList.get(remove);
                for (VertexDistance<T> t: neighborList) {
                    if (!visitedSet.contains(t.getVertex())) {
                        bfsQueue.add(t.getVertex());
                    }
                    if (visitedSet.size() == graph.getVertices().size()) {
                        return result;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex cannot be null!");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Graph does not "
                    + "contain start vertex!");
        }

        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        List<Vertex<T>> result = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        List<VertexDistance<T>> neighborList = adjList.get(start);
        result.add(start);
        visitedSet.add(start);
        dfsHelper(adjList, neighborList, graph, result, visitedSet);
        return result;
    }

    /**
     * private recursive helper method for a Depth first search
     * @param adjList map of the adjacency list of the input graph
     * @param neighborList list containing all the vertices a
     *                     vertex is adjacent to
     * @param graph the graph to be search through
     * @param result list containing all the previously visited vertices in
     *               order
     * @param visitedSet a set containing all the vertices previously visited
     * @param <T> generic type of data
     */
    private static <T> void dfsHelper(Map<Vertex<T>, List<VertexDistance<T>>>
                                             adjList,
                                     List<VertexDistance<T>> neighborList,
                                     Graph<T> graph,
                                     List<Vertex<T>> result,
                                     Set<Vertex<T>> visitedSet) {
        for (VertexDistance<T> t: neighborList) {
            if (!visitedSet.contains(t.getVertex())
                    && visitedSet.size() != graph.getVertices().size()) {
                result.add(t.getVertex());
                visitedSet.add(t.getVertex());
                dfsHelper(adjList, adjList.get(t.getVertex()),
                        graph, result, visitedSet);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from {@code start}, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check that not all vertices have been visited.
     * 2) Check that the PQ is not empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from {@code start} to every
     *          other node in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex cannot be null!");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Graph does not "
                    + "contain start vertex!");
        }

        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        for (Vertex<T> t: graph.getVertices()) {
            distanceMap.put(t, Integer.MAX_VALUE);
        }
        pq.add(new VertexDistance<>(start, 0));
        while (!pq.isEmpty()
                && visitedSet.size() != graph.getVertices().size()) {
            VertexDistance<T> remove = pq.remove();
            if (!visitedSet.contains(remove.getVertex())) {
                visitedSet.add(remove.getVertex());
                distanceMap.put(remove.getVertex(), remove.getDistance());
                List<VertexDistance<T>> neighborList =
                        adjList.get(remove.getVertex());
                for (VertexDistance<T> t: neighborList) {
                    if (!visitedSet.contains(t.getVertex())) {
                        pq.add(new VertexDistance<>(t.getVertex(),
                                remove.getDistance() + t.getDistance()));
                    }
                }
            }
        }

        return distanceMap;
    }


    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex cannot be null!");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null!");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Graph does not "
                    + "contain start vertex!");
        }

        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> edgeSetMST = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        for (VertexDistance<T> t: adjList.get(start)) {
            pq.add(new Edge<>(start, t.getVertex(), t.getDistance()));
        }
        visitedSet.add(start);
        while (!pq.isEmpty()
                && visitedSet.size() != graph.getVertices().size()) {
            Edge<T> remove = pq.poll();
            if (!visitedSet.contains(remove.getV())) {
                visitedSet.add(remove.getV());
                edgeSetMST.add(remove);
                edgeSetMST.add(new Edge<>(remove.getV(), remove.getU(),
                        remove.getWeight()));
                for (VertexDistance<T> t: adjList.get(remove.getV())) {
                    if (!visitedSet.contains(t.getVertex())) {
                        pq.add(new Edge<>(remove.getV(), t.getVertex(),
                                t.getDistance()));
                    }
                }
            }
        }

        if (visitedSet.size() != graph.getVertices().size()) {
            return null;
        }

        return edgeSetMST;
    }
}
