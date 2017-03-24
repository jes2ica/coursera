## Dijkstra's Shortest Path Algorithm
### Single-Source Shortest Paths
- Input: directed graph G = (V,E). (m = |E|, n = |V|)
  - each edge has nonnegative length l(e)
  - source vertex s
- Output: for each v ∈ V, compute L(v) := length of a shortest s-v pathing.
- Assumptions:
  - [for convenience] ∀ v ∈ V, ∃ an s-> v path
  - [important] l(e) >= 0, ∀ v ∈ V 
  
### Why Another Shortest-Path Algorithm
- Question: doesn't BFS already compute shortest paths in linear time?
  - Answer: yes, if l(e)=1 for every edge e.
- Question: why not just replace each by directed path of l(e) unit lengh edge
  - Answer: blows up graph too much
  - Solution: Dijkstra's shortest algorithm

### Dijkstra's Algorithm
- Initiaze:
  - X = [s] [vertices processed so far]
  - A[s] = 0 [computed shortest path distances]
  - B[s] = empty path [computed shortest path] -> this array only to help explaination
- Main Loop
  - while X != V: `// need to grow x by one node`
  - among all edges (v, w) ∈ E with v ∈ X, w ∉ X, pick the one that minimizeds A[v] + l(vw) `// Dijkstra's greedy criterion` 
    - call it (v*, w*)
    - A[v]: already computed = earlier iteration
  - add w\* to X
  - set A[w\*] := A[v\*] + l(v\*w\*)
  - set B[w\*] := B[v\*] ∪ (v*, w*)
  
### Example
  - Refer to written notes

### Non-Example
  - Question: why not reduct computing shortest paths with negative edge lenghts to the same problem with nonnegative edge lengths? (by adding large constatnt to edge(lenghths))
    - Problem: doesn't preserve shortest paths!
    - Also: Dijkstra's algorithm incorrect on this graph

  
  
  
  

