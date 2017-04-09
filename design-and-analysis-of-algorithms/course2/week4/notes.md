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
  - sort A (o(nlgn) tiome)
  - for each x in A, look for t-x in A via binary search (o(nlog n))
- Amazing:
  - insert elements of A
  - for each x in A, lookup t-x in H
#### Further Immediate Applications
- Historical applications: symbol tables in compliers
- blocking network traffic
- search algorithms (e.g. game tree exploration)
  - use hash table to avoid exploring any configuration (e.g. arragement of chess pieces) more than once
- etc.
#### Implementation
- Setup: universe u (e.g. all IP addresses, all names, all chess board configurations, etc.) [generally really big]
- Goal: want to maintain evolving set s ∈ u [generally, of resonable site]
- Naive solutions:
  - array-based solution [indexed by u] 
    - o(1) operations but o(|u| space)
  - list-based solution
    - o(|s|) space but o(|s| lookup)
- Solution:
- pick n = # of "buckets" with n ~ |s| (for simplicity, assume |s| doesn't vary too much)
- choose a hash function h: u -> {0, 1, 2, ... n-1}
- use array A of length n, store x in A[h(x)] 
  - birthday "paradox"
#### Resolving Collisions
- Collision: distinct x, y ∈ U such that h(x) = h(y)
- Solution #1 seperate chaining
  - keep linked list in each bucket
  - given a key/object x, perform Insert/Delete/Lookup in the list in A[h(x)]
- Solution #2 open addressing (only one object per bucket)
  - hash function now speficies probe sequence h1(x), h2(x),... (keep trying till find open slot)
  - examples: linear probing (look consecutively), doble hashing (use 2 hash functions)
- Comparison
  - care about space: open addressing
  - deletion is trickier with open addressing than chaining
#### What Makes a Good Hash Function
- Note: in hash table with chaining, insert is o(1) [insert new object x at front of list in A[h(x)]]
- o(list_length) for search/delete. [list_length could be anywhere from m/n(equal-length lists) to m(all objects in same bucket) for m objects]
- Point: performance depends on the choice of hash function!
  - analogous situation with open addressing 
- Properties of a "good" hash function
  - should lead to good performance -> i.e. should "spread data out" (gold standard: completely random hashing)
  - should be easy to store / very fast to evaluate
#### Bad Hash Functions
- Example: keys = phone numbers (10-digits), |u| = 10^10, choose n = 10^3
  - terrible hash function: h(x) = 1st 3 digits of x
  - mediocre hash function: h(x) = last 3 digits of x [still vulnerable]
- Example: keys = memory locations (will be multiples of a power of 2)
  - bad hash function: h(x) = x % 1000
    - all odd buckets guaranteed to be empty
#### Quick-and-Dirty Hash Functions
```
Objects -----> integers -----> buckets {0,1,2,...n-1}

    "hash code"   "compression function"
``` 

- hash code: e.g. subroutine to convert strings to integers
- compression function: like the mod n function
- How to choose n= # of buckets
  - choose n to be a prime (within costant factor of # of objects in table)
  - not too close to a power of 2
  - not too close to a power of 10
