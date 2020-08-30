# data-structure-and-algorithm
As title.

# Leet code
* https://github.com/azl397985856/leetcode
* https://github.com/MisterBooo/LeetCodeAnimation
* https://github.com/labuladong/fucking-algorithm
* https://labuladong.gitbook.io/algo/
* https://github.com/jicahoo/leetcode-scala

# B树Notes
```
B和B+树的一个最区别点，就是所有的Key数据都会出现在叶子节点，中间节点的Key也会重复出现在叶子节点，但是B树就不会。正是因为
这个原因，所以说，B+树才能把所有叶子节点连接起来，用以扫描所有元素。而B树做不到这一点。
B+树的索引数据至多出现两次，一个可能是在非叶子节点，一个是在叶子节点。B树的索引数据只会出现一次。
B树的查找可以在中间节点终结（如果命中的话）， B+树的查找肯定都要终结在叶子节点，无论是命中还是没命中。
B+树如何做范围查询，先查找到低值，然后，在叶子节点的链表中，依次扫描直到当前值大于高值。
因为结构的不一样而导致的性质的区别:
```
