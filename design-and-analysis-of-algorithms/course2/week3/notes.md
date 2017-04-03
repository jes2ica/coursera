### Data Structures
- Point: organize data so that it can be accessed quickly and usefully.
- Examples: lists, stacks, queues, heaps search trees, hashtables, bloom filters, union-find, etc.
- Why so many? => different data structures support different sets of operations => suitable for different types of tasks
- Rule of Thumb: choose the "minimal" data structrue staht supports all the operations you need.

#### Taking it to the next level
- level 0 - "what's a data structure"
- level 1 - cocktail party-level literacy
- level 2 - "this problem calls out for a heap"
- level 3 - "I only use data structures that I wrote myself"

### Heap
- a container of objects that have keys 
  - employer records, network edges, events etc.
- Supported Operations
  - INSERT: add a new object  to a heap
    - Running time: O(log n)
  - EXTRACT-MIN: remove an object in heap with a minimum key value. [ties broken arbitrarily - equally well, EXTRACT MAX]
    - Running time: O(log n)
  - HEAPIFY: n batched Inserts in o(n) time (o(n) time)
  - DELETE: (o(log n) time)
#### Applications: Sorting
- Canonical use of heap: fast way to do repeated minimum
- Example: SelectionSort 
  - o(n) linear scans, o(n^2) runtime on array of length n
- HeapSort
  - insert all n array elements into a heap
  - extract-min to pluck at elements in sorted order
  - running time = 2n heap operations = o(nlogn) time
  - optimal for a "comparison-based" sorting algorithm!
#### Application: Event Manager
- "Priority Queue" - synonym for a heap
- Example: simulation (e.g. for a video game)
  - objects = event records (action/update to occur at given time in the future)
  - key = time event scheduled to occur
  - etract-min => yields the next scheduled event
#### Application: Median Maintanence
- I give you: a sequence x1,...xn of numbers, one-by-one.
- You tell me: at each time step i, the median of {x1, ...xi};
- constraint: use o(log i) time at each step i
- solution: maintain heaps H(low): supports EXTRACT_MAX; H(high): supports EXTRACT_MIN
- key idea: maintain invariant thath ~ i/2 smallest (largest) elements in H(low)/H(high)
- you check:
  - can maintain invariant with o(log i) work
  - given invariant, can compute median in o(log i) work
#### Application: Speeding Up Dijkstra
- Dijkstra's Shortest-Path Algorithm
  - naive implementation => runtime = o(nm) 
    - n: # loop iteration
    - m: work per iteration [linear scan through edges for minimum computations]
  - with heaps => run time = o(m log n)
### Implementation Details
- Conceptually: think of a heap as a tree.
  - rooted, binary, as complete as possible
- Heap property: at every node x, key[x] <= all keys of x's children
- Consequence: object at root must have minimum key value
- Insertion
  - step 1: stick k at end of last level.
  - step 2: bubble-up k until heap property is restored (i.e. key of k's parent is <=k)
  - check: 
    - bubbling up process must stop, with heap property restored
    - runtime = o(log n)
- Extract-min
  - Delete root
  - Move last leaf to be new root
  - Iteratively bubble-down until heap property has been restored
  - check:
    - only bubble-down once per level, halt with a heap
    - runtime = o(log n)
### Sorted Arrays
- Supported Operations
  - search: o(log n)
  - select (given order statistic i): o(1)
  - min/max: o(1)
  - predecessor/successor (given pointer to a key): o(1)
  - rank: i.e. # of keys less than or equal to a given value: o(log n)
  - output in sorted order: o(n)
### Balanced Search Trees
- Supported Operations
  - search: o(log n)
  - select (given order statistic i):o(log n)
  - min/max: o(log n)
  - predecessor/successor (given pointer to a key): o(log n)
  - rank: i.e. # of keys less than or equal to a given value: o(log n)
  - output in sorted order: o(n)
  - insert/delete o(log n)
