package medium;

/**
 * @program:
 * @description: 给定n，有多少结构上唯一的BST（二叉搜索树）存储值1 ...  n？
 * 构建一个包含{1,2,3,4,5}的树。首先我们选择1作为根，对于左子树，没有; 对于正确的子树，
 * 我们需要计算从{2,3,4,5}构造出多少可能的树，显然它与{1,2,3,4}的数字相同。
 * 因此，作为根选择的“1”下的树的总数是dp [0] * dp [4] = 14.（假设dp [0] = 1）。类似地，根2具有dp [1] * dp [3] = 5棵树。
 * root 3有dp [2] * dp [2] = 4，root 4有dp [3] * dp [1] = 5，root 5有dp [0] * dp [4] = 14.最后求和完成。
 *
 * 给定序列1 ... n，为了构造序列中的二进制搜索树（BST），我们可以枚举序列中的每个数字i，并使用数字作为根，自然地，子序列1 ...（i-1）在它的左侧将放置在根的左侧分支上，同样右侧子序列（i + 1）... n位于根的右侧分支上。然后我们可以递归地从子序列构造子树。通过上述方法，我们可以确保我们构建的BST都是独特的，因为它们具有独特的根源。
 *
 * 问题是计算唯一BST的数量。为此，我们需要定义两个函数：
 *
 * G(n)：长度为n的序列的唯一BST数。
 *
 * F(i, n), 1 <= i <= n：唯一BST的数量，其中数字i是BST的根，序列范围从1到n。
 *
 * 可以看出，G(n)我们需要计算的实际功能才能解决问题。并且G(n)可以派生自F(i, n)最终将递归引用G(n)。
 *
 * 首先，根据上述定义，我们可以看到唯一BST G(n)的总数是F(i)使用每个数字i作为根的BST的总和。
 * 即
 *
 * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
 * 特别地，在底部情况下，只有一种组合来构造长度为1（仅为根）或0（空树）的序列中的BST。
 * 即
 *
 * G(0)=1, G(1)=1.
 * 给定序列1 ... n，我们从序列中选择一个数字i作为根，然后具有指定根的唯一BST F(i)的数量是其左右子树的BST数量的笛卡尔乘积。例如，F(3, 7)：以数字3为根的唯一BST树的数量。从整个序列[1,2,3,4,5,6,7]构建一个独特的BST，以3为根，也就是说，我们需要从其左子序列构造一个唯一的BST [1] ，2]和右子序列中的另一个BST [ 4,5,6,7 ]，然后将它们组合在一起（即笛卡尔积）。棘手的部分是我们可以将序列[1,2] G(2)中的唯一BST的数量和序列[4,5,6,7] 中的唯一BST的数量视为G(4)。因此，F(3,7) = G(2) * G(4)。
 *
 * 即
 *
 * F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
 * 结合上述两个公式，我们得到了递归公式G(n)。即
 *
 * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
 * @author: Song
 * @create: Created in 2019-02-28 18:48
 * @Modified by:
 **/
public class Code_96_UniqueBinarySearchTrees {
    public static int numTrees(int n) {
        int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        for(int level = 2; level <=n; level++)
            for(int root = 1; root<=level; root++)
                dp[level] += dp[level-root]*dp[root-1];
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }
}