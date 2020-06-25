package maven_conference.Conf_Review;

	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;

	public class ArticleDAO {
		 
		 public static ArrayList articleList() {
		        try (Connection con = Database.getConnection()) {
		            Statement stmt=con.createStatement();  
		            ResultSet rs=stmt.executeQuery("select * from artikulli"); 
		            ArrayList artikull = new ArrayList();
		            while(rs.next()) {
		                Article a = new Article();
		                a.setArtikull_id(rs.getInt("artikull_id"));
		                a.setTitulli(rs.getString("titulli"));
		                a.setAbstrakti(rs.getString("abstrakti"));
		                a.setDoc_name(rs.getString("doc_name"));
		                artikull.add(a);
		            }
		            System.out.println("Articles added to list!");
		            return artikull;
		        } catch (Exception ex) {
		            System.out.println("ArticleDAO-> articleList() : " + ex.getMessage());
		            return null;
		        }
		    }
		 /*
		 public static ArrayList articleAuthorList(int artikull_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from artikulli_autoret,autoret where artikulli_autoret.eid= autoret.email_id and artikulli_autoret.aid=?");  
		            stmt.setInt(1, artikull_id);
		            ResultSet rs=stmt.executeQuery(); 
		            ArrayList authors = new ArrayList();
		            while(rs.next()) {
		                Author a = new Author();
		                a.setAktor_id(rs.getInt("aktor_id"));
		                a.setDatelindja(rs.getString("datelindja"));
		                a.setEmri(rs.getString("emri"));
		                actors.add(a);
		            }
		            System.out.println("Movie Actors added to list!");
		            return actors;
		        } catch (Exception ex) {
		            System.out.println("MovieDAO-> movieActorsList() : " + ex.getMessage());
		            return null;
		        }
		 }
		 */
		 /*
		 
		 public static String articleTitle(int artikull_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from artikulli where artikull_id=?");  
		            stmt.setInt(1, artikull_id);
		            ResultSet rs=stmt.executeQuery();
		            rs.next();
		            return rs.getString("titulli");
		        } catch (Exception ex) {
		            System.out.println("ArticleDAO-> articleAuthorList() : " + ex.getMessage());
		            return null;
		        }
		 } */
		 
		 public static Article articleData(int artikull_id) {
			 Article a = new Article();
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from artikulli where artikull_id=?");  
		            stmt.setInt(1, artikull_id);
		            ResultSet rs=stmt.executeQuery();
		            while (rs.next()) {
		            	
		            	a.setArtikull_id(rs.getInt("artikull_id"));
		                a.setTitulli(rs.getString("titulli"));
		                a.setAbstrakti(rs.getString("abstrakti"));
		                a.setDoc_name(rs.getString("doc_name"));   
		            }
		            System.out.println("Article data captured!");
		            return a;
		        } catch (Exception ex) {
		            System.out.println("ArticleDAO-> articleData() : " + ex.getMessage());
		            return null;
		        }
		 } 
		 
		 public static ArrayList articleShyrtuesitList(int artikull_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from shqyrtues_artikulli, shqyrtuesi where shqyrtuesi.email = shqyrtues_artikulli.sh_email and shqyrtues_artikulli.art_id=?");  
		            stmt.setInt(1, artikull_id);
		            ResultSet rs=stmt.executeQuery(); 
		            ArrayList<Reviewer> shqyrtuesit = new ArrayList<Reviewer>();
		            while(rs.next()) {
		                Reviewer re = new Reviewer();
		                re.setEmail(rs.getString("email"));
		                re.setEmri(rs.getString("emri"));
		                re.setMbiemri(rs.getString("mbiemri"));
		                re.setTel(rs.getString("tel"));
		                re.setInsitucioni(rs.getString("institucioni"));
		                re.setTemat_interes(rs.getString("temat_interes"));
		                shqyrtuesit.add(re);
		            }
		            System.out.println("Article Reviewers added to list!");
		            return shqyrtuesit;
		        } catch (Exception ex) {
		            System.out.println("Article-> articleReviewerList() : " + ex.getMessage());
		            return null;
		        }
		 }
		 
		 
		 // Used to save movie record
	    public static void save(Article ar){
	        int result = 0;
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into artikulli(titulli,abstrakti,doc_name) values(?,?,?)");
	            stmt.setString(1, ar.getTitulli());
	            stmt.setString(2, ar.getAbstrakti());
	            stmt.setString(3, ar.getDoc_name());
	            result = stmt.executeUpdate();
	            System.out.println("Article saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("ArticleDAO->save() : " + e.getMessage());
	        }
	    }
	    
	    // Used to fetch record to update
	    public static Article edit(int id){
	        Article ar = null;
	        try{
	        	Connection conn = Database.getConnection();
	            Statement stmt=conn.createStatement();  
	            ResultSet rs=stmt.executeQuery("select * from artikulli where artikull_id = "+(id));
	            rs.next();
	            ar = new Article();
	            ar.setArtikull_id(rs.getInt("artikull_id"));
	            ar.setTitulli(rs.getString("titulli"));
	            ar.setAbstrakti(rs.getString("abstrakti"));
	            ar.setDoc_name(rs.getString("doc_name"));
	            System.out.println("Article data updated!");
	            conn.close();
	            return ar;
	        }catch(Exception e){
	        	System.out.println("ArticleDAO->edit() : " + e.getMessage());
	        	return null;
	        }       
	    }
	    
	    // Used to delete movie record
	    public static void delete(int id){
	        try{
	        	Connection conn = Database.getConnection();  
	            PreparedStatement stmt = conn.prepareStatement("delete from artikulli where artikull_id = "+id);  
	            stmt.executeUpdate();  
	            System.out.println("Article deleted successfully");
	        }catch(Exception e){
	        	System.out.println("ArticleDAO->delete() : " + e.getMessage());
	        }
	    }

	    public static boolean editArticle(Article ar, int id) {
	        try (Connection con = Database.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("update artikulli set titulli=?, abstrakti=?, doc_name=? where artikull_id=?");
	            ps.setString(1, ar.getTitulli());
	            ps.setString(2, ar.getAbstrakti());
	            ps.setString(3, ar.getDoc_name());
	            ps.setInt(4, id);
	            System.out.println("Article updated!");
	            int count = ps.executeUpdate();
	            return count == 1;
	        } catch (Exception ex) {
	            System.out.println("ArticleDAO->editArticle() : " + ex.getMessage());
	            return false;
	        }
	    }
	}

