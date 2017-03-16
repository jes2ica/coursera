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

### Generic Algorithm 
``Given graph G, vertex S``
- initially s explored, all other vertices unexplored
- while possible:
  - choose an edge (u, v) with u explord and 
