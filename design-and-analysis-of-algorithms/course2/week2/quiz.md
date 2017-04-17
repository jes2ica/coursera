#### 1. Consider a directed graph with distinct and nonnegative edge lengths and a source vertex s. Fix a destination vertex t, and assume that the graph contains at least one s-t path. Which of the following statements are true? [Check all that apply.]
- The shortest s-t path must include the minimum-length edge of G.
- There is a shortest s-t path with no repeated vertices (i.e., a "simple" or "loopless" such path). [x]
- The shortest (i.e., minimum-length) s-t path might have as many as n−1 edges, where n is the number of vertices. [x]
- The shortest s-t path must exclude the maximum-length edge of G. 

#### 2. Consider a directed graph G with a source vertex s, a destination t, and nonnegative edge lengths. Under what conditions is the shortest s-t path guaranteed to be unique?
- When all edge lengths are distinct positive integers.
- When all edges lengths are distinct positive integers and the graph G contains no directed cycles.
- When all edge lengths are distinct powers of 2. [x]
  - Two sums of distinct powers of two cannot be the same (imagine the numbers are written in binary).
- None of the other options are correct.

#### 3. Consider a directed graph G=(V,E) and a source vertex s with the following properties: edges that leave the source vertex s have arbitrary (possibly negative) lengths; all other edge lengths are nonnegative; and there are no edges from any other vertex to the source s. Does Dijkstra's shortest-path algorithm correctly compute shortest-path distances (from s) in this graph?
- Always
  - One approach is to see that the proof of correctness from the videos still works. A slicker solution is to notice that adding a positive constant M to all edges incident to s increases the length of every s-v path by exactly M, and thus preserves the shortest path.
- Never
- Maybe, maybe not (depends on the graph)
- Only if we add the assumption that G contains no directed cycles with negative total weight.

#### 4. Consider a directed graph G and a source vertex s. Suppose G has some negative edge lengths but no negative cycles, meaning G does not have a directed cycle in which the sum of the edge lengths is negative. Suppose you run Dijkstra's algorithm on G (with source s). Which of the following statements are true? [Check all that apply.]
- Dijkstra's algorithm might loop forever.
- Dijkstra's algorithm always terminates, and in some cases the paths it computes will be the correct shortest paths from s to all other vertices. [x]
  - See Question 3.
- Dijkstra's algorithm always terminates, but in some cases the paths it computes will not be the shortest paths from s to all other vertices. [x]
  - Nonnegativity of the edge lengths was used in the correctness proof for Dijkstra's algorithm; with negative edge lengths, the algorithm is no longer correct in general.
- It's impossible to run Dijkstra's algorithm on a graph with negative edge lengths.

#### 5. Consider a directed graph G and a source vertex s. Suppose G contains a negative cycle (a directed cycle in which the sum of the edge lengths is negative) and also a path from s to this cycle. Suppose you run Dijkstra's algorithm on G (with source s). Which of the following statements are true? [Check all that apply.]
- Dijkstra's algorithm might loop forever.
- It's impossible to run Dijkstra's algorithm on a graph with a negative cycle.
- Dijkstra's algorithm always terminates, but in some cases the paths it computes will not be the shortest paths from s to all other vertices. [x]
- Dijkstra's algorithm always terminates, and in some cases the paths it computes will be the correct shortest paths from s to all other vertices.
