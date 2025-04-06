// ### Instructions for Java JDBC MVC Student Management System  

// 1. **Setup MySQL Database:**  
//    - Install and start MySQL.  
//    - Create a database (e.g., `StudentDB`).  
//    - Create a table:  
//      ```sql
//      CREATE TABLE Student (
//          StudentID INT PRIMARY KEY,
//          Name VARCHAR(100),
//          Department VARCHAR(50),
//          Marks DOUBLE
//      );
//      ```

// 2. **Update Database Credentials:**  
//    - Modify `URL`, `USER`, and `PASSWORD` in the code to match your MySQL database credentials.

// 3. **Add MySQL JDBC Driver:**  
//    - Download and add `mysql-connector-java.jar` to your project's classpath.

// 4. **Compile and Run the Program:**  
//    - Compile: `javac StudentManagementApp.java`  
//    - Run: `java StudentManagementApp`  

// 5. **Menu-Driven Operations:**  
//    - **Add Student:** Enter StudentID, Name, Department, and Marks.  
//    - **View Students:** Displays all students in the table.  
//    - **Update Student:** Modify Name, Department, or Marks using StudentID.  
//    - **Delete Student:** Remove a student using StudentID.  
//    - **Exit:** Quit the program.

// 6. **Transaction Handling:**  
//    - Ensures data integrity by using `conn.setAutoCommit(false)` and `conn.commit()`.  
//    - Rolls back changes in case of errors.

// 7. **Verify Database Changes:**  
//    - Use `SELECT * FROM Student;` in MySQL to confirm modifications.





    
// 1. Sql Code:  
CREATE DATABASE university_db;  
 
USE university_db;  
CREATE TABLE Student (  
StudentID INT PRIMARY KEY AUTO_INCREMENT,  
Name VARCHAR(100),  
Department VARCHAR(50),  
Marks INT  
);  
INSERT INTO Student (Name, Department, Marks) VALUES   
('Manish lalwani', 'Computer Science', 85),  
('Amit Sharma', 'Mechanical Engineering', 78),  
('Ramesh Gupta', 'Civil Engineering', 82),  
('Pooja Verma', 'Electrical Engineering', 90),  
('Vikram Singh', 'Information Technology', 88);  
// 2. Java Code:  
// i.   For Model:  
public class Student {     
private int studentID;     
private String name;     
private String department;     
private int marks;  
public Student(int studentID, String name, String department, int marks) 
{         this.studentID = studentID;         this.name = name;         
this.department = department;         this.marks = marks;  
}  
public int getStudentID() { return studentID; }     
public String getName() { return name; }     public 
String getDepartment() { return department; }     
public int getMarks() { return marks; }  
@Override     public 
String toString() {  
return studentID + " | " + name + " | " + department + " | " + marks;  
}  
}  

// ii.  For Controller:  
import java.sql.*; import 
java.util.ArrayList;  
import java.util.List;  
public class StudentController {  
private static final String URL = "jdbc:mysql://localhost:3306/university_db";     
private static final String USER = "root";  
private static final String PASSWORD = "your_password";  
public Connection connect() throws SQLException {  
return DriverManager.getConnection(URL, USER, PASSWORD);  
}  

public void insertStudent(String name, String department, int marks) {  
String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?,  
?, ?)";  
try (Connection con = connect();  
PreparedStatement pstmt = con.prepareStatement(sql)) {            
pstmt.setString(1, name);             
pstmt.setString(2, department);       
pstmt.setInt(3, marks);              
pstmt.executeUpdate();  
System.out.println("Student inserted successfully.");         
} catch (SQLException e) {          
e.printStackTrace();  
}  
}  
public List<Student> getAllStudents() {         
List<Student> students = new ArrayList<>();       
String sql = "SELECT * FROM Student";  
try (Connection con = connect(); Statement stmt = con.createStatement(); 
ResultSet rs = stmt.executeQuery(sql)) {  
while (rs.next()) {  

students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), 
rs.getString("Department"), rs.getInt("Marks")));  
}  
} catch (SQLException e) {             
e.printStackTrace();  
}  
return students;  
}  
public void updateStudent(int studentID, int newMarks) {  
String sql = "UPDATE Student SET Marks = ? WHERE StudentID = ?";  
try (Connection con = connect(); PreparedStatement pstmt 
= con.prepareStatement(sql)) {             
pstmt.setInt(1, newMarks);              
pstmt.setInt(2, studentID);  
int rowsUpdated = pstmt.executeUpdate();  
if (rowsUpdated > 0) System.out.println("Student updated successfully.");             
else System.out.println("Student not found.");         } 
   catch (SQLException e) {             
e.printStackTrace();  
}  
}  
public void deleteStudent(int studentID) {  
String sql = "DELETE FROM Student WHERE StudentID = ?";         
try (Connection con = connect(); PreparedStatement pstmt = 
con.prepareStatement(sql)) {             pstmt.setInt(1, studentID);             
int rowsDeleted = pstmt.executeUpdate();  
if (rowsDeleted > 0) System.out.println("Student deleted successfully.");             
else System.out.println("Student not found.");         }  
catch (SQLException e) {             
e.printStackTrace();  
}  
}}  
iii.  
For View:   
import java.util.List;  
import java.util.Scanner;  

public class StudentApp {     public static void main(String[] args) {         
Scanner scanner = new Scanner(System.in);  
StudentController controller = new StudentController();    
   int choice;  
do {  
System.out.println("\nStudent Management System:");  
System.out.println("1. Insert Student");  
System.out.println("2. View Students");  
System.out.println("3. Update Student Marks");  
System.out.println("4. Delete Student");  
System.out.println("5. Exit");  
System.out.print("Enter your choice: ");           
   choice = 
scanner.nextInt();  
switch (choice) { 
                case 1:  
System.out.print("Enter Student Name: ");  
scanner.nextLine();  
String name = scanner.nextLine();  
System.out.print("Enter Department: ");  
String department = scanner.nextLine();                     
System.out.print("Enter Marks: ");                  
      int marks = scanner.nextInt();  
controller.insertStudent(name, department, marks);                     
break;  
case 2:  
List<Student> students = controller.getAllStudents();  
System.out.println("\nStudentID | Name | Department | Marks");  
System.out.println("--------------------------------------");                     
for (Student s : students) {                          
System.out.println(s);  
}                     
break;  
case 3:  
System.out.print("Enter StudentID to update: ");                     
int studentID = scanner.nextInt();                      
System.out.print("Enter new Marks: ");                     
int newMarks = scanner.nextInt();                     
controller.updateStudent(studentID, newMarks);                     
break;  
DEPARTMENT OF  
  
COMPUTER SCIENCE & ENGINEERING  
case 4:  
System.out.print("Enter StudentID to delete: ");               
int deleteID = scanner.nextInt();                      
controller.deleteStudent(deleteID);  
break;  
case 5:  
System.out.println("Exiting...");  
break;  
default:  
System.out.println("Invalid choice. Try again.");  
}  
} while (choice != 5);  
scanner.close();  
}  
}
