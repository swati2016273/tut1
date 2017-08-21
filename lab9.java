import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class lab9 {

	
	static node[] array;  //we create our heap array which consists of nodes that contain values, and frequencies
	static int size;    //This is the current size of our heap array in part a
	
	static node[] array1;    //This is a our heap array for part b of the question
	static int size1;   //This is te current size of our heap array in part b
	
	static int counter; //This counter will count the number of nodes initially with non zero frequency
	
	public static void main(String[] args) throws IOException
	
	{
		int[] a = new int[256];
		//int[] b = new int[25];
		
		Reader.init(System.in);
		int x = Reader.nextInt();  //These are the dimensions of our 2-d array
		int y = Reader.nextInt();  //x is the width of the array which means number of columns and y is the height which is the number of rows
		int z = x*y;
		
		
		
		while(z-->0)  //We have to take these many number of inputs
		{
			int r = Reader.nextInt();  //This will keep a record of the frequency of the gray code
			a[r] = a[r] + 1;
		}
	
		//till now we have managed to take the input and also store its' frequency in an array
		z=x*y;
		//now we need to create an array of nodes that would hold each of the gray values with its' frequency
		
		create(z);
		create1(z);
	
		node t = new node(0,0);
		for(int i=0;i<a.length;i++)
		{
			if(a[i]!=0)
			{
				//if we find an index with non zero frequency we create a new node and store it in our array and while inserting we could use another function called insert which would make sure that the array is actually a min heap
				t = new node(a[i],i);  //we create a node with the frequency and the value of the gray code
				//System.out.println(t.freq+" "+t.value);
				insert(array,t); //we have now been able to insert a new node while this loop is working
				counter++;  //This would keep  a count of actual number of nodes which have been inserted in the array
				//actually the size already takes care of the number of elements that are there in the heap
				//System.out.println(t.freq + " swati "+ t.value);
				
			}
		}
		
		node f = new node(0,0);
		int k=5;  //This would be the value corresponding to the frequency of each interval
		for(int j=0;j<240;j=j+10)
		{
			int s = 0;
			
			for(int i =j;i<j+10;i++)
			{
				
				s += a[i];
				//along side this we can keep creating nodes 	
			}
			
			//System.out.println(s);
			if(s!=0)
			{
			f = new node(s,k);
			//These nodes have to be continuously inserted in another binary heap
			insert1(array1,f);
			//System.out.println(size1);
			}
			k=k+10;
			
		}
		int e = 0;
		
		int g = 0;
		for(int p=240;p<=255;p++)
		{
			e+=a[p];
		
		}
		
		//for(int n=250;n<=255;n++)
		{
			//g+=a[n];
		}
		
		if(e!=0)
		{
			f = new node(e,245);
			insert1(array1,f);
			//System.out.println(size1);
		}
		
		//print1(array1);
		
		//if(g!=0)
		{
			//f=new node(g,254);
			//insert1(array1,f);
		}
		
		
// 4 4 
		// 109 35 36 40 35 36 55 60 40 45 100 105 35 36 40 45

		
		
		
		//This will print the binary heap that has been created
		//print(array);
		
		node r=new node(0,-1);
		while(size!=1)  //This must produce a huffmann tree
		{
			node p = extract(array);  //This will remove the element with thr least frequency from the binary heap
			node q = extract(array);  //This will remove one more element with the lest frequency from the heap.
			r = new node(p.freq+q.freq,-100,p,q);  //here p and q become the left and right children of the given node and the frequency is the sum of the frequency of the two child nodes
			//System.out.println(r.freq);
			//after creating this resultant node we again insert it into the heap
			insert(array,r);
		}
		//System.out.println(array[0].freq);
		//System.out.println(size);
		//System.out.println(counter);
		//print(array);
		//System.out.println(array[0].right.freq);
		
		if(r.left!=null || r.right!=null)  //if one of the roots is not null only then we would calculate using the recursive formula
		{
			depth(r);
			
			System.out.println((double)Math.round((float)(x*y*8)/sum*10)/10);
			//System.out.println(sum);
		}
		
		if(sum==0)
		{
			System.out.println((double)Math.round((float)(x*y*8)/t.freq*10)/10);
		}
		
		
		
		node r1 = new node(0,-1);
		
		while(size1!=1)  //This must produce a huffmann tree
		{
			node p = extract1(array1);  //This will remove the element with thr least frequency from the binary heap
			node q = extract1(array1);  //This will remove one more element with the lest frequency from the heap.
			r1 = new node(p.freq+q.freq,-100,p,q);  //here p and q become the left and right children of the given node and the frequency is the sum of the frequency of the two child nodes
			//System.out.println(r.freq);
			//after creating this resultant node we again insert it into the heap
			insert1(array1,r1);
		}
		
		if(r1.left!=null || r1.right!=null)  //if one of the roots is not null only then we would calculate using the recursive formula
		{
			depth1(r1);
			
			System.out.println((double)Math.round((float)(x*y*8)/sum1*10)/10);
			//System.out.println(sum);
		}
		
		if(sum1==0)
		{
			System.out.println((double)Math.round((float)(x*y*8)/f.freq*10)/10);
		}
		
		
		
		
		
	}
	
	// first we also need to create a heap
	
	public static void create(int i)  //This function creates a heap for part a
	{
		//here i determines the value of maximum number of nodes that can be there in the min heap
		
		array = new node[i];
		
		size=0;  //initially the size of this node array would be declared to be 0
		
	}
	
	
	public static void create1(int i)  //This function creates a heap for part b
	{
		//here i determines the value of maximum number of nodes that can be there in the min heap
		
		array1 = new node[i];
		size1=0;  //initially the size of this node array would be declared to be 0
		
	}
	
	
	
	public static void insert(node[] array, node newnode)  //This will keep inserting the newnode into the heap while maintaining the heap property at each node
	{
		//at the index size we insert the new node and accroding to the frequency we will maintain the heap property , inserting at size will make sure that it is a complete binary tree
		array[size] = newnode;
		
		int i = size;  //we keep checking from the last node that has been inserted whether the heap property is satisfied or not
		
		while(i>=0 && array[i].freq < array[(i-1)/2].freq)  //till the time i does not reach the root and the value of any of the child nodes is less than its parent we keep on swapping
			
		{
			//depending on the nodes we have to swap the entire nodes and not just the value of their frequency
			node temp = array[i];
			array[i] = array[(i-1)/2];
			array[(i-1)/2] = temp;
			i = (i-1)/2;  //keep updating i as well
		}
		
		size++;  //every time a value gets inserted into the heap the size increases by 1
	}
	
	public static void insert1(node[] array1, node newnode)  //This will keep inserting the newnode into the heap while maintaining the heap property at each node
	{
		//at the index size we insert the new node and accroding to the frequency we will maintain the heap property , inserting at size will make sure that it is a complete binary tree
		array1[size1] = newnode;
		
		int i = size1;  //we keep checking from the last node that has been inserted whether the heap property is satisfied or not
		
		while(i>=0 && array1[i].freq < array1[(i-1)/2].freq)  //till the time i does not reach the root and the value of any of the child nodes is less than its parent we keep on swapping
			
		{
			//depending on the nodes we have to swap the entire nodes and not just the value of their frequency
			node temp = array1[i];
			array1[i] = array1[(i-1)/2];
			array1[(i-1)/2] = temp;
			i = (i-1)/2;  //keep updating i as well
		}
		
		size1++;  //every time a value gets inserted into the heap the size increases by 1
	}
	
	
	
	
	public static node extract(node[] array)  //This function helps us to extract the minimum value from the heap 
	{
		//here to extract would mean dig out a whole node from the min heap. we could however return the frequency in this method
		
		int d = array[0].freq;  //This would be the element with the least frequency in the heap
		int j = array[0].value;  //This would be the value of the gray code with the least frequency. 
		//to remove a complete node we can simply over ride it with the node which is at index size-1
		
		node ans = array[0];  //This is the node that we are removing so the function returns this
		
		array[0] = array[size-1];  //This would now make the above element over ridden
		
		//but now we have to check for the min heap property at each of the nodes so we check with the child nodes.
		int i=0;
		while(2*i+2<size && (array[i].freq > array[2*i+1].freq || array[i].freq > array[2*i+2].freq))    //This can at the most be equal to size-2
			
		
		{
			//now we need to swap since the value in the parent is greater than the value in the child nodes
			
			//we swap with whichever value is less
			
			if(array[2*i+1].freq <= array[2*i+2].freq)
			{
				//if the left child has a smaller or equal value we swap the parent with it
				node temp = array[i];
				array[i] = array[2*i+1];
				array[2*i+1] = temp;
				
				i = 2*i+1;
			}
			
			else
			{
				node temp = array[i];
				array[i] = array[2*i+2];
				array[2*i+2] = temp;
				
				i = 2*i+2;
			}
		
			
		}
		size--;
		return ans;
	}
	
	
	public static node extract1(node[] array1)  //This function helps us to extract the minimum value from the heap 
	{
		//here to extract would mean dig out a whole node from the min heap. we could however return the frequency in this method
		
		int d = array1[0].freq;  //This would be the element with the least frequency in the heap
		int j = array1[0].value;  //This would be the value of the gray code with the least frequency. 
		//to remove a complete node we can simply over ride it with the node which is at index size-1
		
		node ans = array1[0];  //This is the node that we are removing so the function returns this
		
		array1[0] = array1[size1-1];  //This would now make the above element over ridden
		
		//but now we have to check for the min heap property at each of the nodes so we check with the child nodes.
		int i=0;
		while(2*i+2<size1 && (array1[i].freq > array1[2*i+1].freq || array1[i].freq > array1[2*i+2].freq))    //This can at the most be equal to size-2
			
		
		{
			//now we need to swap since the value in the parent is greater than the value in the child nodes
			
			//we swap with whichever value is less
			
			if(array1[2*i+1].freq <= array1[2*i+2].freq)
			{
				//if the left child has a smaller or equal value we swap the parent with it
				node temp = array1[i];
				array1[i] = array1[2*i+1];
				array1[2*i+1] = temp;
				
				i = 2*i+1;
			}
			
			else
			{
				node temp = array1[i];
				array1[i] = array1[2*i+2];
				array1[2*i+2] = temp;
				
				i = 2*i+2;
			}
		
			
		}
		size1--;
		return ans;
	}
	
	public static void print(node[] array)
	{
		for(int i=0;i<size;i++)  //we can only print without a null pointer exception till where ever in the array we have already stored the nodes
		{
			System.out.println(array[i].freq+" "+ array[i].value);
		}
	}
	
	public static void print1(node[] array1)
	{
		for(int i=0;i<size1;i++)  //we can only print without a null pointer exception till where ever in the array we have already stored the nodes
		{
			System.out.println(array1[i].freq+" "+ array1[i].value);
		}
	}
	
	
	
	static int ans1 = 0;
	static int sum1 = 0;
	public static void depth1(node root)
	{
		//This method would give the height of a particular node in the huffman tree
		ans1 = ans1 +1 ;

		
		
		
		if(root.left==null && root.right == null)
		{

			sum1 = sum1 + root.freq * (ans1-1);
			
			
		}
		
		else
		{
			depth1(root.right);
			ans1 = ans1 -1;
			depth1(root.left);
			ans1 = ans1 - 1 ;
		}
		
	}
	
	
	
	
	static int ans = 0;
	static int sum = 0;
	public static void depth(node root)
	{
		//This method would give the height of a particular node in the huffman tree
		ans = ans +1 ;

		
		
		
		if(root.left==null && root.right == null)
		{

			sum = sum + root.freq * (ans-1);
			
			
		}
		
		else
		{
			depth(root.right);
			ans = ans -1;
			depth(root.left);
			ans = ans - 1 ;
		}
		
	}
}





class node
{
	public int freq;  //This will be the frequency of that particular character
	public int value;   //This will be the gray value
	public node left;   //This will be the link to the left child
	public node right;  //This will be the link to the right child
	
	
	node(int f , int val)  //This would be when the current node does not have any children
	{
		freq = f;
		value = val;
		left = null;
		right = null;
	}
	
	node(int f, int val, node l, node r)  //This is the constructor that would be needed to create the huffmann tree 
	{
		left = l;
		right = r;
		freq = f;
		value = val;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public node getLeft() {
		return left;
	}

	public void setLeft(node left) {
		this.left = left;
	}

	public node getRight() {
		return right;
	}

	public void setRight(node right) {
		this.right = right;
	}
	
}

class Reader {
    static BufferedReader Reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize Reader for InputStream */
    static void init(InputStream input) {
        Reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   Reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
