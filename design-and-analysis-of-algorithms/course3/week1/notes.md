### The Bellman-Ford Algorithm
- Single-Source Shortest Paths
  - Input: directed graph: G = (V, E), edge lengths L(e) for each e ∈ E, source vertet s ∈ V. [can assume no parallel edges]
  - Goal: for every destination v ∈ V, compute the length (sum of edge costs) of a shortest s-v path.
- On Dijkstra's Algorithm
  - Good news: O(m log n) running time using heaps (m - edges, n - vertices)
  - Bad news: 
    - not always correct with negative edge lengths [e.g. if edge -> financial transactions]
    - not very distributed (relevant for Internet routing)
    - Solution: The Bellman-Ford Algorithm
- On Negative Cycles
  - Question: how to define shortest pathers with G has a negatice cycle?
    - Solution #1: compute the shortest s-v path, with cycles allowed.
    - Problem: undefinded (or -∞) [will keep traversing negative cycle]
    - Solution #2: compute shortest cycle-free s-v path.
    - Problem: NP-hard(no polynomial algorithm, unless P = NP)
    - Solution #3: (for now) assume input graph has no negative cycles.
    - Later: will show how to quickly check this condition.
  - Quiz: Suppose the input graph G has no negative cycles. which of the following is true? [n = # of vertices, m = # of edges]
    - for every v, there is a shortest s-v path with <= n-1 edges.
- Input: directed graph G = (V,E), edge costs L(e) [possibly negative], source vertex s ∈ V.
- Goal: either
  - for all destinations v ∈ V, compute the length of a shortest s-v path.
  - output a negatice cycle
- Optimal Substructure (Informal)
  - Intuition: exploit sequential nature of paths. subpath of a shortest path should itself be shortest.
  - Issue: not clear how to define "smaller", "larger" subproblems.
  - Key idea: artificially restrict the number of edges in a path.
    - subproblem size <=> number of permitted edges
- Lemma: Let G=(V,E) be a directed graph with edge lengths L(e) and source vertex s. [G might or might not have a negative cycle]
  - For every v ∈ V, i ∈ {1,2,3....}, let P = shortest s-v path with at most i edges (cycles are permitted)
    - Case 1: if P has <= (i-1) edges, it is a shortest s-v path with <= (i-1) edges
      - by obvious contradiction
    - Case 2: if P has i edges with last hop (w,v), then P is a shortest s-v path with <= (i-1) edges
      - if Q (from s to w, <= i-1 edges) is shorter than P'
      - then Q + (w,v) [from s to v, <= i edges] is shorter than P'+(w,v) = P
      - which contradicts the optimality of P
      - QED!
 - Quiz: How many candidates are there for an optimal solution to a subproblem involving the destination v?
  - 1 + in-degree(v) [1 from case 1 + 1 from case 2 for each choice of the fianl hop (w, v)]
