import java.util.NoSuchElementException;


public class CircularQueue<E> {
		public E [] Q; 
		protected int size = 0; 
		protected int front = 0; 
		protected int rear = 0;
		public CircularQueue() {
		Q = (E[ ]) new Object[1];
		}
		//returns size of queue
		public int size() 
		{
			return size;
		}
		//returns true if empty
		public boolean isEmpty() 
		{ 
			return (size == 0);
		}
		//enqueue an entry and resize in the case of overflow
		public void add(E Entry) 
		{ 
		if ((rear+1)%Q.length == front)
			{
			Q = resize(2*Q.length, Q);
			} // doubling
		rear = (rear+1) % Q.length;
		Q[rear] = Entry;
		size++;
		}
		//dequeue an entry and resize in the case of underflow
		public E poll()
		{
			if (isEmpty())
			{
				throw new NoSuchElementException();
			}
			front = (front+1) % Q.length;
			E item = Q[front];
			Q[front] = null;
			size--;
			if(size > 0 && size == Q.length/4)       
			{
				Q = resize(Q.length/2 , Q); // halving
			}
			return item; 
		}
		//resize circular array, preserving elements and relative front/rear position
		public E[] resize(int length, E[] Q) 
		{
			E[] new_array = (E[ ]) new Object[length];
			int i=front;
			int j=front % length;
			front = j;
			while (i != rear)
			{   
				new_array[j] = Q[i];
				i = (i+1) % (Q.length);
				System.out.println(i);
				j = (j+1) % length;
			}
			new_array[j] = Q[i];
			rear = j;
			return new_array; 
		}
}
