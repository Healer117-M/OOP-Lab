public class Student {

	private String studentId;
	private String studentName;
	private String studentDob;
	private String gender;
	private String address;


		static int ID=1;

		public Student (){
		
		studentId="SP25-BCS-"+ID;
		ID+=1;


}

public Student(String studentName, String studentDob, String gender, String address){
	
	studentId="SP25-BCS-"+ID;
	ID+=1;
	this.studentName=studentName;
	this.studentDob=studentDob;
	this.gender=gender;
	this.address=address;

}
 

public Student(Student n) {
		ID+=1;
    		this.studentId = n.studentId;
   		this.studentName = n.studentName;
    		this.gender = n.gender;
    		this.studentDob = n.studentDob;
    		this.address = n.address;
	}


 public String getId() {

	return studentId;
}
 public String getstudentName(){

	return studentName;
}

public String getstudentDob(){

	return studentDob;


}
public String getgender(){

	return gender;
}
public String getaddress(){

	return address;
}

public void setName(String studentName){

	this.studentName=studentName;
}


public void setGender(String gender){

	this.gender=gender;
}

public void setAddress(String address){

	this.address=address;
}

@Override


public String toString() {
        return String.format("Student {ID='%s',name='%s', gender='%s', date='%s', address='%s'}",studentId, studentName, gender, studentDob, address);
    }




}
