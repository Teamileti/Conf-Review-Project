package maven_conference.Conf_Review;

	import java.sql.*;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.List;
	import java.sql.Date;
	import java.util.SimpleTimeZone;

	public class AuthorDAO {
		 
		 public static ArrayList authorList() {
		        try (Connection con = Database.getConnection()) {
		            Statement stmt=con.createStatement();  
		            ResultSet rs=stmt.executeQuery("select * from autoret"); 
		            ArrayList autor = new ArrayList();
		            while(rs.next()) {
		                Author au = new Author();
		                au.setEmail_id(rs.getString("email_id"));
		                au.setEmri(rs.getString("emri"));
		                au.setMbiemri(rs.getString("mbiemri"));
		                autor.add(au);
		            }
		            System.out.println("Authors added to list!");
		            return autor;
		        } catch (Exception ex) {
		            System.out.println("AuthorDAO-> actorsList() : " + ex.getMessage());
		            return null;
		        }
		    }
		 
		 public static ArrayList<Article> authorArticlesList(String email_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("SELECT * FROM artikulli_autoret, artikulli WHERE artikulli_autoret.aid= artikulli.artikull_id AND artikulli_autoret.eid=?");  
		            stmt.setString(1, email_id);
		            ResultSet rs=stmt.executeQuery(); 
		            ArrayList<Article> artikujt = new ArrayList<Article>();
		            while(rs.next()) {
		                Article ar = new Article();
		                ar.setArtikull_id(rs.getInt("artikull_id"));
		                ar.setTitulli(rs.getString("titulli"));
		                ar.setAbstrakti(rs.getString("abstrakti"));
		                ar.setDoc_name(rs.getString("doc_name"));
		                artikujt.add(ar);
		            }
		            System.out.println("Author Articles added to list!");
		            return artikujt;
		        } catch (Exception ex) {
		            System.out.println("AuthorDAO-> authorArticlesList() : " + ex.getMessage());
		            return null;
		        }
		 }
		 
		 public static String authorName(String email_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from autoret where email_id=?");  
		            stmt.setString(1, email_id);
		            ResultSet rs=stmt.executeQuery();
		            rs.next();
		            return rs.getString("emri");
		        } catch (Exception ex) {
		            System.out.println("ActorDAO-> authorName() : " + ex.getMessage());
		            return null;
		        }
		 }
		 /*
	    // Used to delete actor record
	    public static void delete(int id){
	        try{
	        	Connection conn = Database.getConnection();  
	            PreparedStatement stmt = conn.prepareStatement("delete from aktor where aktor_id = "+id);  
	            stmt.executeUpdate();  
	            System.out.println("Actor deleted successfully");
	        }catch(Exception e){
	        	System.out.println("ActorDAO->delete() : " + e.getMessage());
	        }
	    }
		
	    // Used to save actor record
	    public static void save(Actor a){
	        int result = 0;
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into aktor(emri,datelindja) values(?,?)");
	            stmt.setString(1, a.getEmri());
	            stmt.setDate(2, Date.valueOf(a.getDatelindja()));
	            result = stmt.executeUpdate();
	            System.out.println("Actor saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("ActorDAO->save() : " + e.getMessage());
	        }
	    }
	    
	    // Used to fetch record to update
	    public static Actor edit(int id){
	        Actor a= null;
	        try{
	        	Connection conn = Database.getConnection();
	            Statement stmt=conn.createStatement();  
	            ResultSet rs=stmt.executeQuery("select * from aktor where aktor_id = "+(id));
	            rs.next();
	            a = new Actor();
	            a.setAktor_id(rs.getInt("aktor_id"));
	            a.setEmri(rs.getString("emri"));
	            a.setDatelindja(rs.getString("datelindja"));
	            System.out.println("Actor data updated!");
	            conn.close();
	            return a;
	        }catch(Exception e){
	        	System.out.println("ActorDAO->edit() : " + e.getMessage());
	        	return null;
	        }       
	    }

	    public static boolean editActor(Actor a, int id) {
	        try (Connection con = Database.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("update aktor set emri=?, datelindja=? where aktor_id=?");
	            ps.setString(1, a.getEmri());
	            ps.setDate(2, Date.valueOf(a.getDatelindja()));
	            ps.setInt(3, id);
	            System.out.println("Actor updated!");
	            int count = ps.executeUpdate();
	            return count == 1;
	        } catch (Exception ex) {
	            System.out.println("ActorDAO->editActor() : " + ex.getMessage());
	            return false;
	        }
	    }
	    
	    public static void saveAssignActor(int aktor_id, String citimi, int film_id){
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into film_aktor(fid, aid, citimi) values(?,?,?)");
	            stmt.setInt(1, film_id);
	            stmt.setInt(2, aktor_id);
	            stmt.setString(3, citimi);
	            int result = stmt.executeUpdate();
	            System.out.println("Actor-Movie saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("ActorDAO->saveAssignActor() : " + e.getMessage());
	        }
	    }
		    
	}
	*/

}
