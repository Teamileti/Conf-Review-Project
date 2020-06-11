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
		 
		/* public static ArrayList articleAuthorList(int artikull_id) {
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
		 
		 public static String movieTitle(int film_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from film where film_id=?");  
		            stmt.setInt(1, film_id);
		            ResultSet rs=stmt.executeQuery();
		            rs.next();
		            return rs.getString("titulli");
		        } catch (Exception ex) {
		            System.out.println("MovieDAO-> movieActorsList() : " + ex.getMessage());
		            return null;
		        }
		 }
		 
		 public static ArrayList movieDirectorsList(int film_id) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from film_regjizor,regjizor where film_regjizor.rid= regjizor.regjizor_id and film_regjizor.fid=?");  
		            stmt.setInt(1, film_id);
		            ResultSet rs=stmt.executeQuery(); 
		            ArrayList directors = new ArrayList();
		            while(rs.next()) {
		                Director d = new Director();
		                d.setDirector_id(rs.getInt("regjizor_id"));
		                d.setDatelindja(rs.getString("datelindja"));
		                d.setEmri(rs.getString("emri"));
		                directors.add(d);
		            }
		            System.out.println("Movie Directors added to list!");
		            return directors;
		        } catch (Exception ex) {
		            System.out.println("MovieDAO-> movieDirectorsList() : " + ex.getMessage());
		            return null;
		        }
		 }
		 
		 // Used to save movie record
	    public static void save(Movie m){
	        int result = 0;
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into film(titulli,viti,gjatesia, skenari, kompania) values(?,?,?,?,?)");
	            stmt.setString(1, m.getTitulli());
	            stmt.setInt(2, m.getViti());
	            stmt.setInt(3, m.getGjatesia());
	            stmt.setString(4, m.getSkenari());
	            stmt.setString(5, m.getKompania());
	            result = stmt.executeUpdate();
	            System.out.println("Movie saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("MovieDAO->save() : " + e.getMessage());
	        }
	    }
	    
	    // Used to fetch record to update
	    public static Movie edit(int id){
	        Movie m = null;
	        try{
	        	Connection conn = Database.getConnection();
	            Statement stmt=conn.createStatement();  
	            ResultSet rs=stmt.executeQuery("select * from film where film_id = "+(id));
	            rs.next();
	            m = new Movie();
	            m.setFilm_id(rs.getInt("film_id"));
	            m.setTitulli(rs.getString("titulli"));
	            m.setViti(rs.getInt("viti"));
	            m.setGjatesia(rs.getInt("gjatesia"));
	            m.setSkenari(rs.getString("skenari"));
	            m.setKompania(rs.getString("kompania"));
	            System.out.println("Movie data updated!");
	            conn.close();
	            return m;
	        }catch(Exception e){
	        	System.out.println("MovieDAO->edit() : " + e.getMessage());
	        	return null;
	        }       
	    }
	    
	    // Used to delete movie record
	    public static void delete(int id){
	        try{
	        	Connection conn = Database.getConnection();  
	            PreparedStatement stmt = conn.prepareStatement("delete from film where film_id = "+id);  
	            stmt.executeUpdate();  
	            System.out.println("Movie deleted successfully");
	        }catch(Exception e){
	        	System.out.println("MovieDAO->delete() : " + e.getMessage());
	        }
	    }

	    public static boolean editMovie(Movie m, int id) {
	        try (Connection con = Database.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("update film set titulli=?, viti=?, gjatesia=?, skenari=?, kompania=? where film_id=?");
	            ps.setString(1, m.getTitulli());
	            ps.setInt(2, m.getViti());
	            ps.setInt(3, m.getGjatesia());
	            ps.setString(4, m.getSkenari());
	            ps.setString(5, m.getKompania());
	            ps.setInt(6, id);
	            System.out.println("Movie updated!");
	            int count = ps.executeUpdate();
	            return count == 1;
	        } catch (Exception ex) {
	            System.out.println("MovieDAO->editMovie() : " + ex.getMessage());
	            return false;
	        }
	    }
	}
	*/

}
