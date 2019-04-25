package stack;

import java.util.Stack;

/**
 * @author BlackSugar
 * @date 2019/4/25
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * - push(x) -- Push element x onto stack.
 * - pop() -- Removes the element on top of the stack.
 * - top() -- Get the top element.
 * - getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> downStack;

    /**
     * initialize your data structure here.
     * 实现一个栈，支持找出最小值方法
     * 思路：
     * 1、首先考虑用什么数据结构来实现，这里我打算使用stack，方便实现LIFO的逻辑
     * 2、由于题目要求用常量时间，那我们在每次插入的时候比较，按顺序保存最小值，虽然插入的时间变长，但是获取最小值的确是常量时间O(1)
     * ps：发现大家都使用的stack。。。还以为不能用，改一下
     * 大神思路：只使用一个stack，遇到新最小值的时候，将历史最小值先push进栈，再push新最小值，并保存新最小值，
     * 若pop就是新最小值，则新最小值改为目前的栈顶元素（之前的历史最小值）
     * class MinStack {
     *     int min = Integer.MAX_VALUE;
     *     Stack<Integer> stack = new Stack<Integer>();
     *     public void push(int x) {
     *         if(x <= min){
     *             stack.push(min);
     *             min=x;
     *         }
     *         stack.push(x);
     *     }
     *
     *     public void pop() {
     *         if(stack.pop() == min) min=stack.pop();
     *     }
     *
     *     public int top() {
     *         return stack.peek();
     *     }
     *
     *     public int getMin() {
     *         return min;
     *     }
     * }
     */
    public MinStack() {
        stack = new Stack<>();
        downStack = new Stack<>();
    }

    public void push(int x) {
        if (downStack.empty() || x <= downStack.peek()) {
            downStack.push(x);
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop().equals(downStack.peek())) {
            downStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return downStack.peek();
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(2);
        obj.push(0);
        obj.push(3);
        obj.push(0);
        int param1 = obj.getMin();
        obj.pop();
        int param2 = obj.getMin();
        obj.pop();
        int param3 = obj.getMin();
        obj.pop();
        int param4 = obj.getMin();
        obj.pop();
        int param5 = obj.top();
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(param3);
        System.out.println(param4);
        System.out.println(param5);
    }
}
