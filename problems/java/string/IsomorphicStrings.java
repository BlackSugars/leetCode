package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BlackSugar
 * @date 2019/5/7
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    /**
     * 判断两个字符串是不是同构的
     * 思路：题目意思就是字符需要一一对应：s = "egg", t = "add" ---> e -> a, g -> d
     * 1、利用map保存出现过的映射关系，遇到重复的值判断映射关系是不是相同，再互换st再比较一次 O(2n)--->O(n)
     * 2、因为values不能有重复的，利用map的values()方法去重看个数是否一致O(3n)
     * 3、这种题一般都还可以用ascii码数组代替map，O(n)
     * 大神方法：
     * 1、因为map.put()会返回上一个value，利用这个做比较
     * public boolean isIsomorphic(String s, String t) {
     *     Map m = new HashMap();
     *     for (Integer i=0; i<s.length(); ++i)
     *         if (m.put(s.charAt(i), i) != m.put(t.charAt(i)+"", i))
     *             return false;
     *     return true;
     * }
     * 2、通过lastIndexOf
     * public boolean isIsomorphic(String s, String t) {
     * 	if(s == null || t == null) return false;
     *     if(s == "") return t == "";
     *     if(t == "") return false;
     *
     *     for(int i = 0; i < s.length(); i++){
     *         char c1 = s.charAt(i);
     *         char c2 = t.charAt(i);
     *         if(s.lastIndexOf(c1) != t.lastIndexOf(c2))
     *             return false;
     *     }
     *     return true;
     * }
     * 3、和我们方法三差不多，不同的是用一个boolean数组保存是否有值，减少了一部分比较 (只花了1ms)
     * public boolean isIsomorphic(String s, String t) {
     *         int len = s.length();
     *         if (len == 0)
     *             return true;
     *         int[] map = new int[256];
     *         char[] str1 = s.toCharArray();
     *         char[] str2 = t.toCharArray();
     *
     *         boolean[] appear = new boolean[256];
     *         Arrays.fill(map, -1);
     *         for (int i = 0; i < len; i++) {
     *             if (map[str1[i]] == -1) {
     *                 if (appear[str2[i]])
     *                     return false;
     *                 appear[str2[i]] = true;
     *                 map[str1[i]] = str2[i];
     *             } else {
     *                 if (map[str1[i]] != str2[i])
     *                     return false;
     *             }
     *         }
     *         return true;
     *     }
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        /*Map<Character, Character> map = new HashMap<>(s.length() << 1);
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && !map.get(s.charAt(i)).equals(t.charAt(i))) {
                return false;
            }
            map.put(s.charAt(i), t.charAt(i));
        }
        *//*map.clear();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i)) && !map.get(t.charAt(i)).equals(s.charAt(i))) {
                return false;
            }
            map.put(t.charAt(i), s.charAt(i));
        }
        return true;*//*
        return map.size() == map.values().stream().distinct().count();*/
        int[] sArray = new int[256];
        int[] tArray = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (sArray[s.charAt(i)] != tArray[t.charAt(i)]) {
                return false;
            }
            sArray[s.charAt(i)] = tArray[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings().isIsomorphic("fod", "foo"));
    }
}
