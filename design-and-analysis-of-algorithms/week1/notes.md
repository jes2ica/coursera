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
- O(m+n) time using a queue (FIFO)
#### Depth-First Search
- explore aggressibly like a maze, backtrack only when neccessary
- compute topological ordering of directed acyclic graph
- compute connected components in difrected graphs
- O(m+n) time using a stack (LIFO)

