import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Main 
{
	public static void main(String[] args) 
	{
		int j;
		int argFile = 0;
		int argThreads = 0;
		boolean quiet = false;
		for(j=0;j<args.length;j++)
		{
			if(args[j].equals("-f"))
				argFile=j+1;
			if(args[j].equals("-t"))
				argThreads=j+1;
			if(args[j].equals("-q"))
				quiet=true;
				
		}
	String fname = args[argFile];
	
	int numberOfThreads = Integer.valueOf(args[argThreads]);
	
	Thread[] threads = new Thread[numberOfThreads];
	
	Map<Byte,Integer> map = new HashMap<Byte,Integer>();
	
	FileInputStream[] input=new FileInputStream[numberOfThreads];
	try{
	 	
	 File file= new File(fname);
	byte[] buff=new byte[(int)file.length()];
	 
	 int i;
	 int size = buff.length/numberOfThreads+1;
	 long start=Calendar.getInstance().getTimeInMillis();
	 for(i=0;i<numberOfThreads;i++)
	 {
		 input[i] = new FileInputStream(fname);
		 ConstructTableRun runTable = new ConstructTableRun(map, buff, input[i], i*size, size);
		 threads[i] = new Thread(runTable);
		 threads[i].start();
	 } 
	 
	 for(i=0;i<numberOfThreads;i++)
	 {
		 threads[i].join();
	 }

	 
	 long end=Calendar.getInstance().getTimeInMillis();
	 
	 System.out.println("Time:" + (end-start));
	 	 
	
	 Huffman encode = new Huffman();
	 encode.huffBuild(map);
	 encode.encode(buff);
	 List<Byte> arr = encode.encode;
	 		
	 	
	if(!quiet)
	{
		
		for(i=0;i<buff.length;i++)
			System.out.print((char)buff[i]);
		System.out.println();
		 		 
	}
	for ( i=0; i<arr.size();i++)
	 	System.out.print(arr.get(i) + " ");
	
		
	for(i=0;i<numberOfThreads;i++)
	 {
		 input[i].close();
	 }
	}
	catch(Exception e)
	{}
	}
}
