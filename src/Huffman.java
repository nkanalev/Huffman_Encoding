
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Huffman {
	
	TreeNode tree;
	AlphaTable table;
	List<Byte> encode;
	
	public void huffBuild(Map<Byte, Integer> map) 
	{
		this.tree = new TreeNode();
		this.table = new AlphaTable();
		
		this.tree.buildTree(map);
		this.table.buildAlphabet(this.tree);
		
		this.encode = new ArrayList<Byte>();
	}
	
	public void encode(byte bytes[]) 
	{
		List<List<Byte>> binaryCode = new ArrayList<List<Byte>>(bytes.length);
		for (int i=0;i<bytes.length;i++) 
			binaryCode.add(table.aTable.get(bytes[i]));
		
		
		List<Byte> byteCode = new ArrayList<Byte>();
		int byteCount = 0;
		byte number = (byte) 0;
		for (int i=0; i<binaryCode.size(); i++) 
		{
			List<Byte> word = binaryCode.get(i);
			
			for (int j=0; j<word.size(); j++) 
			{
				Byte bit = word.get(j);
				byteCount++;
				
				if (bit == (byte)1)
					number = (byte)(number+1);
					
				
				if (byteCount < 8 && (i!=binaryCode.size()-1 || j!=word.size()-1)) 
					number = (byte)(number<<1);
				
				if (byteCount == 8) 
				{
					byteCode.add(new Byte(number));
					byteCount = 0;
					number = (byte)0;
				}
				
			}
		}
		byteCode.add(number);
		number = (byte)(byteCount);
		byteCode.add(number);
		encode = byteCode;
	}
	
}
