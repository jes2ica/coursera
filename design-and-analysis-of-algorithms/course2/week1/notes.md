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
  - <=> by contradiction, supppose G has a path from s to v: but v unexplored at end of the algorithms. Then ∃ edge (u, w) with u explored and w uneplored. (will never happen!)

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
#### Time Complexity ([CLRS](https://www.youtube.com/watch?v=aKADDvTI_3c))
 - O(V+E) linear time
 - visit each vertex once in DFS alone -> O(V)
 - DFS visit(v) called once per vertex v -> pay |Adj[v]| -> O(sum{Adj[v]}) = O(E)
  - O(2E) for undirected graph and O(E) for directed graph

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
    - (u,v) ∈ G => f(u) < f(v)
  - Motivation: sequences tasks while respecting all precedence constrains
    - undergraduate courses
  - Note: 
    - G has directed cycle => no topological ordering
    - no directed cycle => can compute topological ordering in o(m+n) time.
  - Straightforward Solution
    - Note: every directed acyclic graph has a sink vertex
    - Reason: if not, can keep falling outgoing arcs to produce a directed cycle. 
    - To compute topological ordering: 
      - let v be a sink vertex of G
      - set f(v) = n
      - recurse on G-{v}
    - Why does it work? 
      - when v is assigned to the position i (sink vertex), all outgoing arcs already deleted => all lead to later verices in ordering.
  - Topological Sort via DFS (Slick)
    - DFS-Loop(graph G)
      - mark all nodes unexplored
      - current_label = n
        - to keep track of ordering
      - for each vertex v ∈ G:
        - if v not yet explored // in some previous DFS call
          - DFS(G, v)
    - DFS(graph G, start vertex s)
      - mark s explored
      - for every edge (s, v):
        - if v not yet explored
          - DFS(G, v)
      - set f(s) = current_label
      - current_label--
    - Running time: O(m+n)
    - Reason: o(1) per node, o(1) per edge in each BFS
    - Correctness: need to show that if (u,v) is an edge, then f(u) < f(v)
      - Case 1: u visited by DFS before v => recursive call coresponding to v finishes before that of u (since DFS)
      - Case 2: v visited before u => v's recursive call finishes before u's even starts => f(v) > f(u)
- *Application: Strongly Connected Components*
![SCC](http://www.clipular.com/c/5192857490292736.png?k=wRWeMwd2KQBgiZG3rbbiuX5PUrs)

  - Formal Definition: the strongly connected components (SCCS) of a directed graph G are the equivalence classes of the relation
    - u ~ v <=> there is a path u->v and a path v->u in G
    - You check: ~ is a equivalence relation
  - Why Depth-First Search?
    - Fast!
  - Kosaraju's Two-Pass Algorithm
    - Theorem: can compute SCCS in O(m+n) time
    - Algorithm: (given directed graph G)
      - let G(rev) = G with all arcs traversed
      - run DFS-Loop on G(rev) -> goal: compute "maginal ordering" of nodes
        - let f(v) = "finishing time" of each v ∈ V
      - run DFS-Loop on G -> goal: discover the SCCS one-by-one
        - processing nodes in decreasing order of finishing times
        - [SCCS = nodes with the same "leader"]
   - DFS-Loop
    - DFS-Loop(graph G)
      - global variable t = 0
        - \# of nodes processed so far
        - for finishing times in 1st pass
      - global variable s = NULL 
        - current source vertex aka leader
        - for leaders in 2nd pass
      - assume nodes labelled 1 to n
        - for i = n down to 1
          - if i not yet explored
            - s := i
            - DFS(G, i)
    - DFS(graph G, node i)
      - mark i as explored (for rest of DFS-Loop)
      - set leader(i) := node s
      - for each arc (i,j) ∈ G:
        - if j not yet explored
          - DFS(G, j)
      - t++
      - set f(i) := t (i's finishing time)
    - Running Time: 2DFS = O(m+n)
  - Claim: the SCCS of a directed graph induce an acyclic "meta-graph"
    - meta nodes = the SCCS C1,..., Ck of G
    - there is arc C-> C^ <=> there is a arc (i, j) ∈ G with i ∈ C, j ∈ C^
  - Why acyclic?
     - a cycle of SCCS would collapse into one.
  - Key Lemma
    - Lemma: consider two "adjacent" SCCS in G: 
      - Let f(v) = finishing times of DFS-Loo in G(rev)
      - Then max (v) (v ∈ C1) < max f(v) (v ∈ C2) 
    - Corollary: maximum f-value of G must lie in a "sink SCC" (no outgoing arcs)
  - Correctness Intuition
    - See notes for formal proof 
    - By corollary: 2nd pass of DFS-Loop begins somewhere in a sink SCC C*
      - => first call to DFS discovers C* and nothing else!
      - => rest of DFS-Loop like recursing on G with C* detected [starts in a sink node of G-C*]
      - => successive calls to DFS(G, i) "peal off" the SCCS one by one [in reverse topological order of the "meta-graph" of SCCS]
  - Proof of Key Lemma
    - in G(rev): still SCCS (of G(rev))
    - let v = 1st node of C1 union C2 by 1st pass of DFS-Loop (on G(rev))
    - Case1[v ∈ C1]: all of C1 explored before C2 ever reached
      - reason: no paths rom C1 to C2 (since meta-graph is acyclic)
      - => all f-values in C1 less than all f-values in C2
    - Case2[v ∈ C2]: DFS(G(rev), v) won't finish until all of C1 union C2 completely explored => f(v) > f(w) for all w ∈ C1
    - Largest finishing time has to ben in C2, sometimes it's bigger than everything, sometimes it's bigger than the biggest in C1.


### The Web Graph
- vertics = web pages
- (directed) edges = hyperlinks
- Question: what does the web graph look like? (assume you've already "crawled" it)
  - size: ~200 million nodes, ~1 billion edges
  - Reference: broder et al www 2000 ([Alta Vista](https://en.wikipedia.org/wiki/AltaVista)) [paper](http://snap.stanford.edu/class/cs224w-readings/broder00bowtie.pdf)
    - computed the SCCS of the web graph. (pre map-reduce/Hadoop)
    - The Bow Tie 
    - Main Findings
      - all 4 parts (giant, in, out, tubes + tendrils) have roughly the same size
      - within core, very well connected (has the "small world" property)[(milgram)[https://en.wikipedia.org/wiki/Milgram_experiment]]
      - outside, suprisingly poorly connected
  - Modern Web Search
    - temporal aspects - how is the web graph evolving over time?
    - information aspects - how does new information propagate throughout the web for blogosphere, or twitter etc.
    - finer-grained structure - how to define and compute "communities" in information and social networks?
    - Recommanded reading: Easley+Kleinberg, "Networks, Crowds + Markets"
