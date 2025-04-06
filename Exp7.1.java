

// 1. **Setup MySQL Database**  
//    - Ensure MySQL is installed and running.  
//    - Create a database and an `Employee` table with columns `EmpID`, `Name`, and `Salary`.

// 2. **Update Database Credentials**  
//    - Replace `your_database`, `your_username`, and `your_password` in the code with actual database credentials.

// 3. **Add MySQL JDBC Driver**  
//    - Download and add `mysql-connector-java.jar` to your project’s classpath.

// 4. **Compile and Run the Program**  
//    - Compile: `javac MySQLConnection.java`  
//    - Run: `java MySQLConnection`

// 5. **Verify Output**  
//    - Ensure that employee records are displayed correctly from the database.




// 1.SQL
CREATE DATABASE company_db;  
USE company_db;  
CREATE TABLE Employee (  
EmpID INT PRIMARY KEY,  
Name VARCHAR(50),  
Salary DECIMAL(10,2)  
);  
INSERT INTO Employee (EmpID, Name, Salary) VALUES   
(1, 'Manish La', 70000.00),  
(2, 'Amit Sharma', 60000.00),  
(3, 'Ramesh Gupta', 55000.00),  
(4, 'Pooja Verma', 62000.00),  
(5, 'Vikram Singh', 58000.00);  





// 2. Java Code: 
   import java.sql.*;  

public class FetchEmployee {  
public static void main(String[] args) {  
String url = "jdbc:mysql://localhost:3306/company_db";
String user = "root";  
String password = "akshat"; 
try {  

Class.forName("com.mysql.cj.jdbc.Driver");  

Connection con = DriverManager.getConnection(url, user, password);  

Statement stmt = con.createStatement();  

ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");  
 
System.out.println("EmpID | Name          | Salary");             
System.out.println("----------------------------------");            
while (rs.next()) {  
System.out.println(rs.getInt("EmpID") + " | " +                                    
rs.getString("Name") + " | " rs.getDouble("Salary"));  
}  

rs.close();             
stmt.close();             
con.close();  
} catch (Exception e) {             
e.printStackTrace();  
}  
}  
} 
