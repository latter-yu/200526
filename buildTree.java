import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class buildTree {
    static class TreeNode {
        public char val;
        //根据数据输入结果，树的每个节点都是一个字符
        public TreeNode left;
        public TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }
    //手动处理输入输出格式
    public static void main1(String[] args) {
        //编一个程序，读入用户输入的一串先序遍历字符串，根据此字符串建立一个二叉树
        //例如如下的先序遍历字符串： ABC##DE#G##F### 其中“#”表示的是空格，空格字符代表空树
        // 建立起此二叉树以后，再对二叉树进行中序遍历，输出遍历结果
        //要求输出为：c b e g d f a （打印的每个结果之间需要空格隔开）

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            //由于OJ中的输入数据可能存在多组，一定要使用循环的方式来处理
            String line = scanner.next();
            //读取先序结果后，构建树
            TreeNode root = buildTree(line);
            inOrder(root);
            //打印的每个结果之间需要空格隔开
            //每个结果输出一行
            System.out.println();
        }
    }
    private static void inOrder(TreeNode root) {
        //中序遍历
        if(root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val + " ");
        inOrder(root.right);
    }

    private static int index = 0;//存放当前处理的字符
    //入口
    private static TreeNode buildTree(String line) {
        //辅助方法：使处理多组数据时都是 index = 0;

        //输入数据可能存在多组，每次处理一组新的数据都需要从 0 开始重新计数
        index = 0;
        return createTreePreOrder(line);
    }
    //辅助递归的方法
    //每递归一次，就处理一个节点（从字符串中取出一个指定字符）
    private static TreeNode createTreePreOrder(String line) {
        char ch = line.charAt(index);
        if(ch == '#') {
            return  null;
        }
        //访问操作就是“创造节点”
        TreeNode node = new TreeNode(ch);
        index++;//处理下一个节点
        node.left = createTreePreOrder(line);
        index++;
        node.right = createTreePreOrder(line);
        return node;
    }

    private List<List<Integer>> result = new ArrayList<>();//[
                                                           //]
    //把结果作为一个成员变量来使用
    //递归过程中每层递归方法都可以很方便的操作同一结果
    public List<List<Integer>> levelOrder(TreeNode root) {
        //给你一个二叉树，请你返回其按 层序遍历 得到的节点值（即逐层地，从左到右访问所有节点）.
        //输出：[
        //     [3],
        //     [9,20],
        //     [15,7]
        //     ]
        if (root == null) {
            return result;
        }
        //对树进行先序遍历，递归时需要加上层数（从0开始）
        result.clear();//防止多组数据处理时产生干扰
        levelOrderHelper(root, 0);
        return result;
    }
    private void levelOrderHelper(TreeNode root, int level) {
        if(level == result.size()) {
            //当前level在result中若没有对应的行，加入一行(从0开始算，与ArrayList下标一致)
            //防止下面的get操作出现下标越界
            result.add(new ArrayList<>());// [
                                          //  []
                                          //]
            //首次访问当前行时调用add，后续再次访问就不需要了（画图）
        }
        List<Integer> curRow = result.get(level);//调用新增的[]
        curRow.add((int) root.val);//根据level获取指定行（先序遍历的访问操作）
        if(root.left != null) {
            levelOrderHelper(root.left, level + 1);
        }
        if(root.right != null) {
            levelOrderHelper(root.right, level + 1);
        }
    }

}


