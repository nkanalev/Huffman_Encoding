import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


public class AlphaTable {
	
	public Map<Byte, List<Byte>> aTable;
	
	public void buildAlphabet(TreeNode tree) 
	{
		List<Byte> word = new ArrayList<Byte>();
		aTable = new HashMap<Byte, List<Byte>>();
		
		if (tree.root == null)
			return;
		
		if (tree.root.isLeaf())
		{
			word.add((byte)0);
			this.aTable.put(tree.root.value, word);
		} 
		else
			buildAlphabet(tree.root, word);
	}
	
	public void buildAlphabet(Node node, List<Byte> lbytes)
	{
		if (node.isLeaf()) 
		{
			List<Byte> codeWord = new ArrayList<Byte>(lbytes.size());
			for (Byte b : lbytes)
				codeWord.add(b);
			
			aTable.put(node.value, codeWord);
			
			if (lbytes.size() > 0) 
				lbytes.remove(lbytes.size()-1);
			
			return;
		}
		if (node.left != null)
		{
			lbytes.add((byte)0);
			buildAlphabet(node.left, lbytes);
		}
		if (node.right != null) 
		{
			lbytes.add((byte)1);
			buildAlphabet(node.right, lbytes);
			if (lbytes.size() > 0) 
				lbytes.remove(lbytes.size()-1);
			
		}
	}
	
}
