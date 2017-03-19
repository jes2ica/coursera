## Graph Search
### A few motivations
- check if a network is connected (can get to anywhere from anywhere else)
- driving direction
- formulate a plan (e.g. how to fill in a sudoku puzzle)
  - ``nodes``: a partially completed puzzle 
  - ``arcs``: filling in one new square
- compute the "pieces" / "components" of a graph
  - clustering, structure of the graph, etc.
  
### Generic Graph Search
### Goals
- find everything findable
  - from a given start vertex
- don't explore anything twice
- O(m+n) time

### Generic Algorithm 
``Given graph G, vertex S``
- initially s explored, all other vertices unexplored
- while possible:
  - choose an edge (u, v) with u explord and v explored
  - mark v explored
- Claim: at the end of the algorithm, v explored <=> G has a path from s to v (G undirected or directed)
- Proof: 
  - <=> easy induction a number of iterations (you check).    
  - <=> by contradiction, supppose G has a path from s to v: but v unexplored at end of the algorithms. Then there exists edge (u, w) with u explored and w uneplored. (will never happen!)

### BFS vs. DFS
- Note: has to choose among the possibly many "frontier" edges?
#### Breadth-First Search
- explore nodes in "layers"
- can compute shortest paths
- can compute connected components of and undirected graph
- O(m+n) time using a queue (FIFO) m - number of edges, n - number of nodes 
#### Depth-First Search
- explore aggressibly like a maze, backtrack only when neccessary
- compute topological ordering of directed acyclic graph
- compute connected components in difrected graphs
- O(m+n) time using a stack (LIFO)

### The Code
#### BFS(graph G, start vertex s)
[all nodes initally unexplored]
- mark s as explored
- let Q=queue data strcture, initialized with s
- while Q is not empty:
  - remove the first node of Q, call it v
  - for each edge (v, w):
    - if w unplored
      - mark w as explored
      - add w to Q (at the end)
- Claim 1: at the end of BFS, v explored <=> G has a path from s to v
  - Reason: special case of the generic algorithm (if you mark w as explored, there's a path between v and w)
- Claim 2: running time of main while loop = O(ns + ms), where ns = # of nodes recheable from s,  ms = # of edges reacheable from s. 
  - Reason: For each vertex, we check at most 1 time; for each edge, we check at most 2 times.
- *Application: Shortest Paths*
  - Goal: compute dist(v), the fewest # of edges on a path from s to v
  - Extra code: 
    - initiate dist(v): 0 (if v = s), unlimited (if v != s)
    - when considering edge (v, w):
      - if w unexplored, then set dist(w) = dist(v) + 1
  - Claim: at termination, dist(v) = i <=> v in the ith layer <=> shortest s-v path has i edges
  - Proof idea: every layer i node w is added to Q by a layer (i-1) node v via the edge (v, w)
- *Application: Undirected Connectivity*
  - Let G = (V,E) be an undirected graph
  - Connected components = the "pieces" of G.
  - Formal definition: equivalence classes of the relation u ~ v <=> there's a u - v path in G 
    - [check: ~ is a equivalence relation]
  - Goal: compute all connected components
  - Why: 
    - check if network is disconnected
    - graph visualization
    - clustering
  - To compute all components:
    - all nodes unexplored [assume labelled 1 to n]
    - for i = 1 to n
      - if i not yet explored
        - BFS(G, i) -> discovers precisely i's connected components
  - Note: finds every connected components.
  - Running time: O(m+n) -> o(1) per node, o(1) per edge in each BFS
#### DFS
- mimic BFS code, use a stack instead of a queue
- Recursive version: 
  - DFS (graph G, start vertex s)
    - mark s as explored
    - for every edge (s, v):
      - if v unexplored
        - DFS(G, v)
- Claim #1: at the end of the algorithm, v marked as explored <=> there is a path from s to v in G.
  - Reason: particular instantiatation generic search procedure
- Claim #2: running time is o(ns + ms)
  - Reason: look at each node in connected component of s at most once, each edge at most twice.
- *Application: Topological Sort*
  - Definition: A topological ordering of a directed graph G is a labelling f of G's nodes such that: 
    - the f(v)'s are the set {1,2,...,n}
    - (u,v) belongs to G => f(u) < f(v)
  - Motivation: sequences tasks while respecting all precedence constrains
    - undergraduate courses
  - Note: 
    - G has directed cycle => no topological ordering
    - no directed cycle => can compute topological ordering in o(m+n) time.
  - Straightforward Solution
    - Note: every directed acyclic graph has a sink vertex
    - Reason: if not, can keep falling outgoing arcs to produce a directed cycle. 
- *Application: Strong connected components of directed graphs*
