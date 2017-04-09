### Hash Table
#### Supported Operations
- Purpose: maintain a (possibly envolving) set of stuff. (transactions, people associated data, IP address, etc.)
- Insert: add new record
- Delete: delete existing record
- Lookup: check for a particular record
- all operations in o(1) time
  - properly implemented
  - non-pathological data
#### Application: De-Duplication
- Given: a "stream" of objects.
  - linear scan through a huge file
  - objects arriving in real time
- Goal: remove duplicates (i.e. keep track of unique objects)
  - e.g. report unique visitors to website
  - avoid duplcates in search results
- Solution: 
  - when new object x arrives
    - lookup x in hash table h 
    - if not found, insert x into h
#### Application: The 2-Sum Problem
- Input: unsorted array of n integers, target sum t.
- Goal: determine whether or not there are two numbers x,y in A with x + y = t
- Naive solution: O(n^2) time via exhausive search.
- Better: 
  - (1) sort A (o(nlgn) tiome)
  - (2) for each x in A, look for t-x in A via binary search (o(nlog n))
