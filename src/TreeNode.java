import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeNode {
	
	public Node root;

	public void buildTree(Map<Byte, Integer> freqs) {
		if (freqs.isEmpty()) {
			root = null;
		} else
		if (freqs.size() == 1) {
			for (Map.Entry<Byte, Integer> entry : freqs.entrySet()) 
			{
				Node leftChild = new Node();
				leftChild.count = entry.getValue();
				leftChild.value = entry.getKey();
				root = new Node();
				root.left = leftChild;
				root.count = leftChild.value;
			}	
		}
		else
		{
			List<Node> trees = new ArrayList<Node>(freqs.size());
			for (Map.Entry<Byte, Integer> entry : freqs.entrySet())
			{

				Node temp = new Node();
				temp.count = entry.getValue();
				temp.value = entry.getKey();
				trees.add(temp);
			}
			root = treeBuilder(trees);
		}
	}
	
	//find the index of the minimal count
	  public int min(List<Node> lnodes)
	  {
		  int min = 0,i;
		  for(i=1;i<lnodes.size();i++)
		  {
			  if(lnodes.get(min).count>=lnodes.get(i).count)
				  min = i;
		  }
		  return min;
	  }
	  //A helper method that connects 
	  public Node treeBuilder (List<Node> lnodes)
	  {
		  while(lnodes.size()!=1)
		  {
			 int min1;
			 min1 = min(lnodes);
			 Node m1 = new Node();
			 m1 = lnodes.remove(min1);
			
			 int min2;
			 min2 = min(lnodes);
			 Node m2 = new Node();
			 m2 = lnodes.remove(min2);
			
			 Node res = new Node();
			 res.count = m1.count + m2.count;
			 res.left = m1;
			 res.right = m2;
			 lnodes.add(res); 
			
		 }
		  
		  return lnodes.get(0);
		  
	  }
}
