package maven_conference.Conf_Review;

	import java.sql.*;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.List;
	import java.sql.Date;
	import java.util.SimpleTimeZone;

	public class AuthorDAO {
		 
		 public static ArrayList<Author> authorList() {
		        try (Connection con = Database.getConnection()) {
		            Statement stmt=con.createStatement();  
		            ResultSet rs=stmt.executeQuery("select * from autoret"); 
		            ArrayList<Author> autor = new ArrayList<Author>();
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
		            System.out.println("AuthorDAO-> authorList() : " + ex.getMessage());
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
	    // Used to delete actor record
	    public static void delete(String id){
	        try{
	        	Connection conn = Database.getConnection();  
	            PreparedStatement stmt = conn.prepareStatement("delete from autoret where email_id = " + (id));  
	            stmt.executeUpdate();  
	            System.out.println("Author deleted successfully");
	        }catch(Exception e){
	        	System.out.println("AuthorDAO->delete() : " + e.getMessage());
	        }
	    }
		
	    // Used to save actor record
	    public static void save(Author a){
	        int result = 0;
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into autoret (email_id,emri,mbiemri) values(?,?,?)");
	            stmt.setString(1, a.getEmail_id());
	            stmt.setString(2, a.getEmri());
	            stmt.setString(3, a.getMbiemri());
	            result = stmt.executeUpdate();
	            System.out.println("Author saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("AuthorDAO->save() : " + e.getMessage());
	        }
	    }
	    
	    // Used to fetch record to update
	    public static Author edit(String id){
	         Author a;
	        try{
	        	Connection conn = Database.getConnection();
	            Statement stmt=conn.createStatement();  
	            ResultSet rs=stmt.executeQuery("select * from autoret where email_id = '"+ id + "';");     
	            rs.next();
	            a = new Author();
	            a.setEmail_id(rs.getString("email_id"));
	            a.setEmri(rs.getString("emri"));
	            a.setMbiemri(rs.getString("mbiemri"));
	            System.out.println("Author data updated!");
	            conn.close();
	            return a;
	        }
	        catch(Exception e){
	        	System.out.println("AuthorDAO->edit() : " + e.getMessage());
	        	return null;
	        }       
	    }

	    public static boolean editAuthor(Author a, String email_id) {
	        try (Connection con = Database.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("update autoret set email_id =?, emri=?, mbiemri =? where email_id = ?");            
	            ps.setString(1, a.getEmail_id());
	            ps.setString(2, a.getEmri());
	            ps.setString(3, a.getMbiemri());
	            ps.setString(4, email_id);
	            System.out.println("Author updated!");
	            int count = ps.executeUpdate();
	            return count == 1;
	        }
	        catch (Exception ex) {
	            System.out.println("AuthorDAO->editAuthor() : " + ex.getMessage());
	            return false;
	        }
	    }
	    
	    
	    public static String saveAuthorContact(int aid, String eid, String kontakt){
	    	String res = " ";
	        try{
	        	
	        	Connection conn = Database.getConnection();
	        	Statement st = conn.createStatement();
	        	ResultSet rs = st.executeQuery("select count(kontakt) from artikulli_autoret where aid = " +(aid));
	        	rs.next();
	            int numri = rs.getInt(1);
	        	if (numri == 0) { //nqs ka tashme nje kontakt per kete artikull atehere afishi mesazhin
	            PreparedStatement stmt = conn.prepareStatement("insert into artikulli_autoret(aid, eid, kontakt) values(?,?,?)");
	            stmt.setInt(1, aid);
	            stmt.setString(2, eid);
	            stmt.setString(3, kontakt);
	            int result = stmt.executeUpdate();
	            res = "Article_Contact saved successfully!";
	            return res;
	        	}
	        	
	        	else {
					return res = "This article already has its contact number!";
	        	}
	        }
	        catch(Exception e){
	        	System.out.println("AuthorDAO->saveAuthorContact() : " + e.getMessage());
	        }  
	        finally {
	            System.out.println("The 'try catch' is finished.");
	          }
			return res;
	    }
	}
	        
	  	




