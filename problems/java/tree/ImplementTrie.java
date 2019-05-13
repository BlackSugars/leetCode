package tree;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author BlackSugar
 * @date 2019/5/9
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 * <p>
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class ImplementTrie {
    /**
     * 实现前缀树（字典树）
     * 思路：这种题就看如何选用数据结构，重点在于实现startWith方法
     * 1、首先尝试直接利用list的内置方法，挨个遍历，这里就不写了  (350ms)
     * 2、优化，使用map，以前缀-包含单词的格式插入，但是这样还是有很多重复数据  (220ms)
     * 3、既然说了实现字典树，那我们就来实现字典树，包含一个Trie[128]，意味着所有小写字母的ascii码，可以用迭代或者递归，这里用递归吧。
     * 插入：判断每个对应的位置是否存在节点，如果不存在则新建，存在则使用改节点以及传入字符串的后面部分，当字符串传入完毕之后修改当前trie的isEnd标签为true
     * 查找：从字符串头开始依次往下查找，遇到不存在则为false，如果字符串遍历完毕就判断isEnd标签
     * 查询前缀：从字符串头沿着树往下遍历即可  (90ms)
     * 4、优化，时间还是比较长，感觉是substring开销过大，我们加上一个索引字段表示当前字符  (74ms)
     * 5、再优化，我们只有26个字母，128有点大，采用ascii码计算，与'a'相减 (73ms)
     * 其他思路：
     * Ternary Search Trie Implementation With Java：三值搜索法，这是前缀树最常用的方法
     * I noticed the most recent posted java solution are all about 26-ways tries. It is great but sometimes, a waste of storage space.
     * I posted my TST solution here just in case anyone wants to try another implementation of Trie.
     * After all, TST is one of the most common way to implement Trie.
     * Also, R-way needs you to specify the number of children based on different problems, while in TST there are always 3 of them.
     *
     * class TrieNode {
     *     char val;
     *     TrieNode left, mid, right;
     *     boolean end;
     *
     *     // Initialize your data structure here.
     *     public TrieNode() {
     *         // do nothing
     *     }
     *
     *     public TrieNode(char val) {
     *         this.val = val;
     *     }
     * }
     *
     * public class Trie {
     *     private TrieNode root;
     *
     *     public Trie() {
     *         root = new TrieNode();
     *     }
     *
     *     // Inserts a word into the trie.
     *     public void insert(String word) {
     *         if (word != null && !word.isEmpty()) insert(word, 0, root);
     *     }
     *
     *     private TrieNode insert(String word, int index, TrieNode node) {
     *         char ch = word.charAt(index);
     *         if (node == null) node = new TrieNode(ch);
     *         if (ch < node.val) node.left = insert(word, index, node.left);
     *         else if (ch > node.val) node.right = insert(word, index, node.right);
     *         else if (index < word.length() - 1) node.mid = insert(word, index + 1, node.mid);
     *         else node.end = true;
     *         return node;
     *     }
     *
     *     // Returns if the word is in the trie.
     *     public boolean search(String word) {
     *         if (word == null || word.isEmpty()) return false;
     *         return search(word, 0, root);
     *     }
     *
     *     private boolean search(String word, int index, TrieNode node) {
     *         if (node == null) return false;
     *         char ch = word.charAt(index);
     *         if (ch < node.val) return search(word, index, node.left);
     *         if (ch > node.val) return search(word, index, node.right);
     *         if (index < word.length() - 1) return search(word, index + 1, node.mid);
     *         return node.end;
     *     }
     *
     *     // Returns if there is any word in the trie
     *     // that starts with the given prefix.
     *     public boolean startsWith(String prefix) {
     *         if (prefix == null || prefix.isEmpty()) return false;
     *         return startsWith(prefix, 0, root);
     *     }
     *
     *     private boolean startsWith(String prefix, int index, TrieNode node) {
     *         if (node == null) return false;
     *         char ch = prefix.charAt(index);
     *         if (ch < node.val) return startsWith(prefix, index, node.left);
     *         if (ch > node.val) return startsWith(prefix, index, node.right);
     *         if (index == prefix.length() - 1) return true;
     *         return startsWith(prefix, index + 1, node.mid);
     *     }
     * }
     */
    /*class Trie {
        private Map<String, List<String>> map;

        public Trie() {
            map = new HashMap<>();
        }

        public void insert(String word) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
                if (!map.containsKey(sb.toString())) {
                    List<String> list = new ArrayList<>();
                    list.add(word);
                    map.put(sb.toString(), list);
                } else {
                    List<String> list = map.get(sb.toString());
                    list.add(word);
                }
            }
        }

        public boolean search(String word) {
            List list = map.get(String.valueOf(word.charAt(0)));
            return list != null && list.contains(word);
        }

        public boolean startsWith(String prefix) {
            return map.containsKey(prefix);
        }
    }*/

    class Trie {
        private Trie[] nodes;
        private boolean isEnd;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            nodes = new Trie[26];
            isEnd = false;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            int ascii = word.charAt(0);
            Trie currentTrie = nodes[ascii - 'a'];
            if (currentTrie == null) {
                currentTrie = new Trie();
                nodes[ascii - 'a'] = currentTrie;
                insertHelper(word, currentTrie, 1);
            } else {
                insertHelper(word, currentTrie, 1);
            }
        }

        private void insertHelper(String word, Trie trie, int index) {
            if (word.length() <= index) {
                trie.isEnd = true;
                return;
            }
            int ascii = word.charAt(index);
            Trie currentTrie = trie.nodes[ascii - 'a'];
            if (currentTrie == null) {
                currentTrie = new Trie();
                trie.nodes[ascii - 'a'] = currentTrie;
                insertHelper(word, currentTrie, index + 1);
            } else {
                insertHelper(word, currentTrie, index + 1);
            }
        }


        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie currentTrie = nodes[word.charAt(0) - 'a'];
            if (currentTrie != null) {
                return searchHelper(word, currentTrie, 1);
            }
            return false;
        }

        private boolean searchHelper(String word, Trie trie, int index) {
            if (word.length() <= index) {
                return trie.isEnd;
            }
            Trie currentTrie = trie.nodes[word.charAt(index) - 'a'];
            if (currentTrie != null) {
                return searchHelper(word, currentTrie, index + 1);
            } else {
                return false;
            }
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie currentTrie = nodes[prefix.charAt(0) - 'a'];
            if (currentTrie != null) {
                return startsWithHelper(prefix, currentTrie, 1);
            }
            return false;
        }

        private boolean startsWithHelper(String word, Trie trie, int index) {
            if (word.length() <= index) {
                return true;
            }
            Trie currentTrie = trie.nodes[word.charAt(index) - 'a'];
            if (currentTrie != null) {
                return startsWithHelper(word, currentTrie, index + 1);
            } else {
                return false;
            }
        }

    }

    public static void main(String[] args) {
        Trie obj = new ImplementTrie().new Trie();
        obj.insert("abbz");
        obj.insert("baa");
        boolean param2 = obj.search("abbc");
        boolean param3 = obj.startsWith("ab");
        System.out.println(param2 + ", " + param3);
    }
}
