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
- How to choose n = # of buckets
  - choose n to be a prime (within costant factor of # of objects in table)
  - not too close to a power of 2
  - not too close to a power of 10

#### The Load of a Hash Table
- Definition: the load factor of a hash table is α := (# of objects in hash table) / (# of buckets of hash table) 
  - α = o(1) is neccesarry condition for operations to run in constatnt time.
  - with open addressing , need α << 1
- Upshot:
  - for good hash table performance, need to control load
  - need a good hash function (i.e. spreads data evenly across buckets)
- Ideal:
  - use super-clever hash function guarenteed to speed every data set out evenly 
- Problem: DOES NOT EXIST! (for every hash function, there is a panthological data set)
- Reason: fix a hash function h u -> {0,1,2,...,n-1} (assume |u| >> n)
  - ala pigeonhole priciple, ∃ bucket i such that at least |u|/n elements of u hash to i under n
  - if dataset drawn only from these, everything collides!
  
#### Pathological Data in the Real World
  - Reference: [Crosby and Wallach, USENIX 20013](https://www.usenix.org/legacy/event/sec03/tech/full_papers/crosby/crosby.pdf)
  - Main point: can paralyze several real-world systems (e.g. network intrusion detection) by exploiting badly designed hash functions.
    - open source
    - overly simplistic hash function (easy to reverse engineer a pathological data set).
  - Solutions:
    - use a cryptographic hash function (e.g. SHA-2): infeasible to reverse engineer a pathological data set
    - use randomization: design a family H of hash functions such that, ∀ data sets S, "almost all" functions h ∈ H spread S out "pretty evenly" (compare to QuickSort guarantee)

### Universal Hashing
#### Overview
  - Proposed definition of a "good random hash function" - universal family of hash functions
  - Concrete example of simple + practival such functions
  - Justifications of definition: "good functions" lead to "good performance"
#### Definition
  - Let H be a set of hash functions from U to {1,2,...,n-1}, H is universal if and only if: for all x,y ∈ U (with x != y), Pr[x,y collide (h(x) == h(y))] <= 1/n (n = # of buckets). when h is chosen uniformly at random from H (i.e. colissiong probability as small as with "gold standard" of perfectly random hashing)
  - Quiz: Consider a hash family H, where each hash function of H maps elements from a universe U to one of n buckets. Suppose H has the following property: for every bucket i and key k, a 1/n fraction of the hash functions in H map k to i. Is H universal?
    - Yes: Take H = all functions from u to {0,1,2,...,n-1}
    - No: H = the set of n different constant functions
#### Example: Hashing IP Addresses
- Let U = IP addresses (of the form (x1, x2, x3, x4), with each xi ∈ {0,1,2,...255})
- Let n = a prime (e.g. small multiple of # of objects in HT)
- Construction: Define one hash function h(a) per 4-type a=(a1,a2,a3,a4) with each ai ∈ {0,1,2,...,n-1} 
- Define: h(a): IP Address -> buckets
  - by h(a)(x1,x2,x3,x4) = (a1x1 + a2x2 + a3x3 + a4x4) mod n
  - Theorem: This family is universal
    - Assume: x4 != y4, Question: collision probability? (i.e. Prob (h(a) ∈ H) [h(a)(x1,...,x4) = h(a)(y1,...y4)])
    - Note: collision <=> (a1x1 + a2x2 + a3x3 + a4x4) mod n = (a1y1 + a2y2 + a3y3 + a4y4) mod n <=> a4(x4 - y4) mod n = sum{i=1~3}(ai\*(yi-xi)) mod n
    - Next: condition on random choices of a1,a2,a3. (a4 still random)
    - The story so far: with a1,a2,a3 fixed arbitrarily, how many choices of a4 satisfy a4(x4 - y4) mod n = sum{i=1~3}(ai\*(yi-xi)) mod n <=> x,y collide under h(a)
    - Key claim: 
      - left-hand side equally likely to be any of {0,1,2,...,n-1} => implies Prob[h(a)(x) = h(a)(y)] = 1/n
    - Reason: 
      - x4 != y4 (x4 - y4 != o mod n) [addendum: make sure n bigger than maximum value of an ai]
      - n is prime
      - a4 uniform at random
    - "Proof" by example: n = 7, x4 - y4 = 2 or 3 mod n, (a4 = 1,2,...,7)
    - QED!
- Chaining: Constant-Time Guarantee
  - Scenarios: hash table implemented with chaining, hash function h chosen uniformly at random from universal family H.
  - Theorem: Carter - Wegman 1979
    - All operations run in o(1) time (for every data set S)
  - Caveats: 
    - in expectation over random choice of the hash function h
    - assumes |S|=o(n) [i.e., load α = |s|/n = o(1)] -> n = # of buckets
    - assumes takes o(1) time to evaluate hash fn
  - Proof:
    - Will analysze an unsucessful lookup (other operations only faster)
    - SoL let s = data set with |S| = o(n)
    - consider lookup for x ∉ S
  - Running Time: o(1)[compute h(x)] + o(list length in A[h(x)])[traverse list => L] -> a random variable depends on choice of h
- A General Decomposition Principle
  - Identify random variable Y that you really care about.
  - Express Y as sum of indicator random variables: Y = Sum(Xe)[e=1 -> n]
  - Apply liberity of expectation: E[Y] = sum(Pr[Xe = 1]) => "just" need to understand those
- Proof (Part II)
  - Let L = list length in A[h(x)]
  - For y ∈ S (so x != y), define Zy = {1 if h(y) = h(x)} : {0 otherwise}
  - Note: L = sum(Zy) (y ∈ S)
  - So: E[L] = sum{E[Zy]} (y ∈ S) = sum{Pr[h(y) = h(x)]} (y ∈ S)
- Open Addressing
  - Recall: one object per slot, hash function produces a probe sequence for each possible key x.
  - Fact: difficult to analyze rigorously
  - Heuristic assumption: (for a quick & dirty idealized analysis only) all n! probe sequences euqally likely.
  - Observation: under heuristic assumption, expected Insertion time ~ 1/(1-α), where α = load.
  - Proof: A random probe finds an empty slot with probabilty 1 - α.
  - So: Insertion time ~ the number N of coin flips to get "heads", where Pr["heads"] = 1 - α
  - Note: E[N] = 1 + α * E[N] <- (probility of tails * expected # of further coin flips needed)
        (1st coin flips)
  - Solution: E[N] = 1 / (1 - α)
- Linear Probing
  - Note: heuristic assumption completely false.
  - Assume instead:
    - initial probe uniformly random, independent for different keys.
    - Theorem: [Knuth 1962] under above assumption, expected Insertion time is ~ 1 / (1 - α)^2, where α = load.
  - "I first formulated the following derivation in 1962... Ever since that day, the analysis of algorithms has in fact been one of the major themes in my life." - D. E. Knuth, The Art of Computer Programming
#### Bloom Filters
- Operations: fast inserts and lookups
- Comparison to Hash Tables:
  - Pros: move space effecient
  - Cons: 
    - can't store an assoicated object
    - no deletions
    - small false positive probability (i.e. might say x has been inserted even though it hasn't been)
- Applications:
  - Original: early spellcheckers
  - Canonical: list of forbidden passwords
  - Modern: network routers
    - limited memory, need to be super-fast
  - Under the Hood
    - Ingredients: 
      - array of n bits (so n/|S| = # of bits per object in data set S)
      - k hash functions h1, ..., hk (k = small constant)
    - Insert(x): for i = 1,2,...,k set A[hi(x)]=1 (whether or not bit alrady set to 1)
    - Lookup(x): return TRUE <=> A[hi(x)] = 1 for every i = 1,2,...,k
    - Note: no false negatives(if x was inserted, lookup(x) guaranteed to succeed)
    - But: false positive if all k hi(x)'s already set to i by other insertions
  - Heuristic Analysis
    - Intuition: should be a trade-off between space and error (false positive) probability.
    - Assume: [not justified] all hi(x)'s uniformly random and independent (across different i's and x's).
    - Setup: n bits, insert data sets into bloom filter.
    - Note: for each bit of A, the probability it's been set to 1 is (under above assumption): 1-(1-1/n)^k|S| ~ 1 - e^(-k|S|/n) = 1 - e^(-k/b) -> b = # of bits per object
    - Story so far: probability a given bit is 1 is ~ 1 - e^(k/b)
    - So: under assumption, for x ∉ S, false positive probability is <= [1 - e^(-k/b)]^k => error_rate є, where b = # of bits per object.
    - How to set k? For fixed b, є is minimized by setting k ~ (ln 2)\*b ~ 0.693 \* b
    - Plugging back in: є ~ (1/2)^(ln 2)b (exponentially smally in b) or b ~ 1.44 log_2 (1/є)
    - Ex: with b = 8, choose k = 5 or 6, error probability only 2 %
    
    
