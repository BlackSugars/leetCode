package math;

/**
 * @author BlackSugar
 * @date 2019/5/27
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * Example:
 * <p>
 * Given n = 5, and version = 4 is the first bad version.
 * <p>
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * <p>
 * Then 4 is the first bad version.
 * <p>
 * The isBadVersion API is defined in the parent class VersionControl.
 * boolean isBadVersion(int version);
 */
public class FirstBadVersion extends VersionControl {
    /**
     * 查找第一个坏版本
     * 思路：
     * 1、迭代直到为false，O(n)
     * 2、二分法，可以递归和迭代，这里选了递归，O(logn)
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        /*for (int i=n; i >= 0; i--) {
            if (!isBadVersion(i)) {
                return i + 1;
            }
        }
        return n;*/

        return firstBadVersionHelper(1, n);
    }

    public int firstBadVersionHelper(int start, int end) {
        if (start == end) {
            return end;
        }
        int mid = start + (end - start) / 2;
        if (isBadVersion(mid)) {
            return firstBadVersionHelper(start, mid);
        } else {
            return firstBadVersionHelper(mid + 1, end);
        }
    }

    public static void main(String[] args) {
        System.out.println(new FirstBadVersion().firstBadVersion(1420736637));
    }

}
