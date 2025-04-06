// ### Instructions to Run the Java CRUD Program:

// 1. **Setup MySQL Database**
//    - Ensure MySQL is installed and running.
//    - Create a database and a `Product` table with columns `ProductID`, `ProductName`, `Price`, and `Quantity`.

// 2. **Update Database Credentials**
//    - Replace `your_database`, `your_username`, and `your_password` in the code with actual database credentials.

// 3. **Add MySQL JDBC Driver**
//    - Download and add `mysql-connector-java.jar` to your project’s classpath.

// 4. **Compile and Run the Program**
//    - Compile: `javac ProductCRUD.java`
//    - Run: `java ProductCRUD`

// 5. **Menu-Driven Operations**
//    - Select options to **Create**, **Read**, **Update**, or **Delete** products.
//    - Input values as prompted.

// 6. **Transaction Handling**
//    - Transactions ensure data integrity.
//    - If an error occurs, changes are rolled back.

// 7. **Verify Output**
//    - Ensure product records are correctly manipulated in the database.




// 1. Sql Code:  
CREATE DATABASE store_db;  
USE store_db;  
CREATE TABLE Product (  
ProductID INT PRIMARY KEY AUTO_INCREMENT,  
ProductName VARCHAR(100),  
Price DECIMAL(10,2),  
Quantity INT  
);  
INSERT INTO Product (ProductName, Price, Quantity) VALUES   
('Laptop', 50000.00, 10),  
('Smartphone', 20000.00, 20),  
('Tablet', 15000.00, 15),  
('Headphones', 3000.00, 25),  
('Smartwatch', 8000.00, 18);  
// 2. Java Code:  
import java.sql.*; 
import java.util.Scanner;  
public class ProductCRUD {  
static final String URL = "jdbc:mysql://localhost:3306/store_db"; // Database URL     
static final String USER = "root"; // MySQL username  
static final String PASSWORD = "your_password"; // Your MySQL password  
public static void main(String[] args) {         
try {  
Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver  
Connection con = DriverManager.getConnection(URL, USER, PASSWORD);  
Scanner scanner = new Scanner(System.in);             
int choice;  
do {  
System.out.println("\nProduct CRUD Operations:");  
System.out.println("1. Insert Product");  

System.out.println("2. View Products");  
System.out.println("3. Update Product");  
System.out.println("4. Delete Product");  
System.out.println("5. Exit");  
System.out.print("Enter your choice: ");  
choice = scanner.nextInt();  
switch (choice) {                     
case 1:  
      insertProduct(con, scanner);  
break;  
case 2:  
      insertProduct(con, scanner);  
break;    
case 3:            
viewProducts(con);  
break;                     
updateProduct(con, scanner);                         
break;                    
   case 4:  
deleteProduct(con, scanner);  
break;                     
case 5:  
System.out.println("Exiting...");                         
break;                    
   default:  
System.out.println("Invalid choice. Try again.");  
}  
} while (choice != 5);  
scanner.close();             
con.close();         } catch 
(Exception e) {             
e.printStackTrace();  
}  
}  
private static void insertProduct(Connection con, Scanner scanner) throws SQLException 
{  
System.out.print("Enter Product Name: ");  
scanner.nextLine();  
String name = scanner.nextLine();         
System.out.print("Enter Price: ");       

double price = scanner.nextDouble();         
System.out.print("Enter Quantity: ");  
int quantity = scanner.nextInt();  
String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES 
(“Monitor”, 10000,50)";  
PreparedStatement pstmt = con.prepareStatement(sql);         
pstmt.setString(1, name);          
pstmt.setDouble(2, price);         
pstmt.setInt(3, quantity);       
pstmt.executeUpdate();  
System.out.println("Product inserted successfully.");  
}  
private static void viewProducts(Connection con) throws SQLException {  
String sql = "SELECT * FROM Product";  
Statement stmt = con.createStatement();  
ResultSet rs = stmt.executeQuery(sql);  
System.out.println("\nProductID | Product Name  | Price  | Quantity");  
System.out.println("---------------------------------------------");         
while (rs.next()) {  
System.out.println(rs.getInt("ProductID") + " | " +                                
rs.getString("ProductName") + " | " +                                
rs.getDouble("Price") + " | " +  rs.getInt("Quantity"));  
}  
}  
private static void updateProduct(Connection con, Scanner scanner) throws SQLException 
{  
System.out.print("Enter ProductID to update: ");         
int productId = scanner.nextInt();         
System.out.print("Enter new Price: ");      
double newPrice = scanner.nextDouble();         
System.out.print("Enter new Quantity: ");      
int newQuantity = scanner.nextInt();  
String sql = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";  

PreparedStatement pstmt = con.prepareStatement(sql);         
pstmt.setDouble(1, newPrice);         pstmt.setInt(2, 
newQuantity);         pstmt.setInt(3, productId);  
int rowsAffected = pstmt.executeUpdate();  
if (rowsAffected > 0) {  
System.out.println("Product updated successfully.");         
} else {  
System.out.println("Product not found.");  
}  
}  

private static void deleteProduct(Connection con, Scanner scanner) throws SQLException 
{  
System.out.print("Enter ProductID to delete: ");         
int productId = scanner.nextInt();  
String sql = "DELETE FROM Product WHERE ProductID = ?";  
PreparedStatement pstmt = con.prepareStatement(sql);         
pstmt.setInt(1, productId);  
int rowsAffected = pstmt.executeUpdate();  
if (rowsAffected > 0) {  
System.out.println("Product deleted successfully.");  
} else {  
System.out.println("Product not found.");  
}  
}  
} 
