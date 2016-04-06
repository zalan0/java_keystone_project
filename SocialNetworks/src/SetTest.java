import java.util.HashSet;

public class SetTest {

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		System.out.println(set.getClass().getClass());
		Integer a = 1;
		System.out.println(a.getClass());
		Class thisClass = a.getClass();
		System.out.println(thisClass);
		Integer b = 2;
		if(thisClass.equals(new Integer(1).getClass())) System.out.println("works");
	}
	
	public <T> void check(HashSet<T> check) {
//		System.out.println(T.getClass());  // nope
	}
}
