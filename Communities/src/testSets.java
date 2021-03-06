import java.util.Arrays;
import java.util.HashSet;

public class testSets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Integer> set1 = new HashSet<Integer>();
		set1.add(1);
		set1.add(2);
		
		HashSet<Integer> set2 = new HashSet<Integer>();
		set2.add(2);
		set2.add(1);
		
		System.out.println(set1.equals(set2));
		
		HashSet<HashSet<Integer>> overSet = new HashSet<HashSet<Integer>>();
		overSet.add(set2);
		overSet.add(set1);
		
		System.out.println(overSet.size());
		System.out.println(overSet);
		
		HashSet<Integer> newSet = new HashSet<Integer>(Arrays.asList(1,2,3));
		System.out.println(newSet);
	}

}
