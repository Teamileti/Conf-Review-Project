package maven_conference.Conf_Review;

import java.sql.*;

public class Prova {
	   public static void edit(String email){
	        Reviewer re= null;
	        try{
	        	Connection conn = Database.getConnection();
	            Statement stmt=conn.createStatement();  
	            ResultSet rs=stmt.executeQuery("select * from shqyrtuesi where email = "+(email));
	            rs.next();
	            re = new Reviewer();
	            re.setEmail(rs.getString("email"));
	            re.setEmri(rs.getString("emri"));
	            re.setMbiemri(rs.getString("mbiemri"));
	            re.setTel(rs.getString("tel"));
	            re.setInsitucioni(rs.getString("institucioni"));
	            re.setTemat_interes(rs.getString("temat_interes"));
	            System.out.println("Reviewer data updated!");
	            conn.close();
	           // return re;
	            System.out.println(re);
	        }catch(Exception e){
	        	System.out.println("ReviewerDAO->edit() : " + e.getMessage());
	        	//return null;
	        }       
	    }

	
}
