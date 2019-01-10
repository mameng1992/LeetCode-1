/**
 * 
 * @author songzhiyong
 *�ں�������
 *��ʵҲ���ǵݹ��˼�룬�������������ֱ��ں�
 */
public class Code_617_MergeTwoBinaryTrees {
	
	public class TreeNode{
		public int val;
		public TreeNode left;
		public TreeNode right;
		public TreeNode(int val){
			this.val = val;
		}
		
	}
	
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null){
        	return null;
        }
        
        if (t1 == null && t2!= null){
        	return t2;
        }
        if (t1 != null && t2== null){
        	return t1;
        }
        
    	TreeNode head = new TreeNode(t1.val + t2.val);
        head.left = mergeTrees(t1.left, t2.left);
        head.right = mergeTrees(t1.right, t2.right);
        return head;
    }	
}
