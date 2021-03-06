/*
 * Copyright (C), 2015-2017
 * FileName: BST
 * Author:   Administrator
 * Date:     2017/11/8 0008 11:01
 * Description: 二分搜索树
 */

package me.zonglun.BinaryTree;

import java.util.LinkedList;

/**
 * 〈一句话功能简述〉<br>
 * 〈二分搜索树〉
 *
 * @author Administrator
 * @create 2017/11/8 0008
 * @since 1.0.0
 */
public class BST<Key extends Comparable<Key>, Value> {
    // 新建内部类
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        // Node 的构造函数
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    private Node root;  // 根节点
    private int count;   // 树种的节点个数

    // 构造函数, 默认构造一棵空二分搜索树
    BST() {
        root = null;
        count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    // 查看二分搜索树中是否存在键key
    public boolean contain(Key key) {
        return contain(root, key);
    }

    public Value search(Key key) {
        return search(root, key);
    }

    void preOder() {
        preOder(root);
    }

    void inOder() {
        inOder(root);
    }

    void postOder() {
        postOder(root);
    }

    // 对以node为根的二叉搜索树进行层序遍历, 递归算法
    void levelOrder() {
        // 我们使用LinkedList来作为我们的队列
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.remove();
            System.out.print(node.key + " ");
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }
    }


    Key maximum() {
        assert count != 0;
        Node maxNode = maximum(root);
        return maxNode.key;
    }

    Key minimum() {
        assert count != 0;
        Node minNode = minimum(root);
        return minNode.key;
    }

    void removeMin() {
        if (root != null)
            root = removeMin(root);
    }

    void removeMax() {
        if (root != null)
            root = removeMax(root);
    }

    // 从二分搜索树中删除键值为key的节点
    void remove(Key key) {
        root = remove(root, key);
    }

    // 删除掉以node为根的二分搜索树中键值为key的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根

    private Node remove(Node node, Key key) {
        if (node == null)   // 没有剑值为 null 的节点
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { //key == key -> node

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null; // 此时把node 的下一个指引删除，等于删除了Node， 在C++中此为 delete Node；
                count--;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = new Node(minimum(node.left));
            count++;

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            count--;
            return successor;
        }
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 返回以node为根的二分搜索树的最小键值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 返回以node为根的二分搜索树的最大键值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    // 对以node为根的二叉搜索树进行前序遍历, 递归算法
    private void preOder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOder(node.left);
            preOder(node.right);
        }
    }

    // 对以node为根的二叉搜索树进行中序遍历, 递归算法
    private void inOder(Node node) {
        if (node != null) {
            inOder(node.left);
            System.out.print(node.key + " ");
            inOder(node.right);
        }
    }

    // 对以node为根的二叉搜索树进行后序遍历, 递归算法
    private void postOder(Node node) {
        if (node != null) {
            postOder(node.left);
            postOder(node.right);
            System.out.print(node.key + " ");
        }
    }

    // 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
    // 返回插入新节点后的二分搜索树的根
    private Node insert(Node node, Key key, Value value) {
        // 如果对应的Key的值等于 节点的索引，等于即直接替换
        if (node == null) {
            count++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        }
        else{
            node.right = insert(node.right, key, value);
        }
        return node;
    }

    private boolean contain(Node node, Key key) {
        if (node == null)
            return false;
        if (node.key == key) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contain(node.left, key);
        }
        else{
            return contain(node.right, key);
        }
    }

    // 在以node为根的二分搜索树中查找key所对应的value, 递归算法
    // 若value不存在, 则返回NULL
    private Value search(Node node, Key key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        }
        else{
            return search(node.right, key);
        }
    }

}
