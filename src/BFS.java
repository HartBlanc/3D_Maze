import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BFS
{
	//stops out of index range error in arrays
	public static boolean inBounds(int x,int a,int b)
	{
		if (x <a && x>= b)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static void main(String[] args) throws IOException
	{
		//Open Output file
		File idFile = new File("output.txt");	
		FileWriter output = new FileWriter(idFile);
		//Open Input file
	    FileInputStream fstream = new FileInputStream("input.txt");
		 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		 //Parse N from input
		 String N_string;
		 int N = 0;
		 int test_case = 1;
		 while ((N_string = br.readLine()) != null)
		 {
			 N = Integer.parseInt(N_string);
			 //parse array from input
			 char[][][] array = new char[N][N][N];
			 for(int y=0; y<N; y++)
			 {
			 	for(int x=0; x<N; x++)
				{
			 		String line=br.readLine();

			 		for(int z=0; z<N; z++)
					{
			 			array[y][x][z] = line.charAt(z);
					}
				 }
			 }

//		 Queue<int[]> frontier = new LinkedList<int[]>();
//		 Queue<Integer> count_track = new LinkedList<Integer>();

		 //Breadth-First-Search
	     //boolean that keeps track of where has been visited already
		 boolean[][][] visited = new boolean[N][N][N];
		 // CircularQueue structure implemented with an array
		 CircularQueue<int[]> frontier = new CircularQueue<int[]>();
		 //Follows frontier and keeps track of distance
		 CircularQueue<Integer> count_track = new CircularQueue<Integer>();
		 int distance = 1;
		 count_track.add(distance);
		 int[] start_node = {0,0,0};
		 int[] end_node = {N-1,N-1,N-1};
		 frontier.add(start_node);
		 visited[0][0][0] = true;
		 //termination condition if path exists
		 boolean completed = false;
		 //lists for finding adjacent paths
		 int[] y_adjust = {1,-1,0,0,0,0};
		 int[] x_adjust = {0,0,1,-1,0,0};
		 int[] z_adjust = {0,0,0,0,1,-1};
		 //termination condition if path does not exist
		 while (!frontier.isEmpty())
		 {
			 //next node to be searched
			 int[] current = frontier.poll();
			 distance = count_track.poll();

			 //path found
			 if (Arrays.equals(end_node, current))
			 {
				 completed = true;
				 break;
			 }

			 int y = current[0];
			 int x = current[1];
			 int z = current[2];
			 //finds nodes adjacent to current
			 for (int i = 0; i < 6; i++)
			 {
				 int adj_y = y+y_adjust[i];
				 int adj_x = x+x_adjust[i];
				 int adj_z = z+z_adjust[i];

				 if (inBounds(adj_y,N,0) && inBounds(adj_x,N,0) && inBounds(adj_z,N,0))
				 {
					 char adjacent = array[adj_y][adj_x][adj_z];
					 int[] adjacent_entry = {adj_y,adj_x,adj_z};
					 boolean adjacent_visited = visited[adj_y][adj_x][adj_z];
					 //conditions to be a path candidate
					 if (adjacent == '0' && !adjacent_visited)
					 {
						 //add to queue & mark as visited
						 frontier.add(adjacent_entry);
						 count_track.add(distance+1);
						 visited[adj_y][adj_x][adj_z] = true;
					 }
				 }
			 }
		 }
		 //output
		 if (completed == true)
		 {
			 output.write(("#"+test_case)+" "+(distance)+"\n");
		 }
		 else
		 {
			 output.write("#"+test_case+" 0"+"\n");
		 }
		 //next test case
		 test_case++;
		 }
		 //close input & output, finish.
		 br.close();
		 output.close();
	}
}
