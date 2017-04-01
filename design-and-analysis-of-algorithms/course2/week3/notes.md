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

### Heap: Supported Operations
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
  
