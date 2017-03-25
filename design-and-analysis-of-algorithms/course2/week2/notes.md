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
### Correctness Claim
  - Theorem [Dijkstra] For every directed graph with nonnegative edge lenght, Dijkstra's algorithm correctly computes all shortest-path distances.
  [i.e. A[v] = L(v) ∀ v ∈ V ]
  - Proof: by induction on the number of iterations. 
    - Inductive step:
      - Inductive Hypothesis: all paths iterations corrects. i.e. for all ∀ v ∈ E, A[v] = L[v] and B[v] is a true shortest-s-v path in G.
      - In current iteration: 
        - we pick an edge(v*, w*) and we add w* to X.
        - we set B[w] = A[v*] ∪ (v*, w*)
        - Also: A[w*] = A[v*] + l(v*, w*)
      - To finishi proof: need to show that every s-w* path has length >= l(v*)+ l(v*, w*) (if so, our path is the shortest)
        - So: Let P = any s -> w* path. Must "cross the frontier"
        - every s->w* path P has to have the form: s ~> y ~> z \~\~> w*
        - by Dijkstra's greedy criterion, A[V*] + l(v*w*) <= A[y] + l(yz) (y ∈ X, z ∉ X)
