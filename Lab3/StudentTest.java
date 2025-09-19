public class StudentTest{

	public static void main(String args[]){
	
		Student s1= new Student();
		Student s2= new Student("Mutahir", "17/12/2004", "M", "Shahrah Lahore");
		Student s3= new Student(s2);		


		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(s3.toString());

}






}