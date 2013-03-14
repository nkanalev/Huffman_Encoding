import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


public class ConstructTableRun implements Runnable
{

	
	Map<Byte, Integer> map;
	byte[] buff;
	int index;
	int size;
	FileInputStream input;
	public ConstructTableRun(Map<Byte, Integer> map, byte[] buff,FileInputStream input,int index, int size)
	{
		this.map = map;
		this.buff = buff;
		this.index = index;
		this.size = size;
		this.input = input;
	}
	
	@Override
	public void run() 
	{

	
		int data;
		try {
			input.skip(index);
			data = input.read();
		
			int i=0;
			while( data!=-1 && i < size) 
			{
				synchronized (buff)
				{
					buff[index+i]=(byte) data;	
				}
				
				data = input.read();
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i;
		byte temp;
		for(i=0;i<size && i+index < buff.length;i++)
		{
			temp = buff[index+i];
			synchronized(map)
			{
				if(map.containsKey(temp))
				{
					map.put(temp,map.get(temp)+1);
				}
				else
					map.put(temp, 1);
			}
		}
		
	}

}
