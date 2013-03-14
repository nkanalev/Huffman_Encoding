
public class Node 
{
	byte value;
	int count;
	Node left,right;
		
	
	public Node()
	{
		left=right=null;
		count=0;
		value=0;
	}
	
	public boolean isLeaf()
	{
	  return (this.left==null && this.left==null);
	}
}
