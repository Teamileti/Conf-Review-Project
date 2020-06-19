package maven_conference.Conf_Review;

	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;

	public class ReviewerDao {
		 
		 public static ArrayList reviewerList() {
		        try (Connection con = Database.getConnection()) {
		            Statement stmt=con.createStatement();  
		            ResultSet rs=stmt.executeQuery("select * from shqyrtuesi"); 
		            ArrayList<Reviewer> reviewers = new ArrayList();
		            while(rs.next()) {
		                Reviewer r = new Reviewer();
		                r.setEmail(rs.getString("email"));
		                r.setEmri(rs.getString("emri"));
		                r.setMbiemri(rs.getString("mbiemri"));
		                r.setTel(rs.getString("tel"));
		                r.setInsitucioni(rs.getString("institucioni"));
		                r.setTemat_interes(rs.getString("temat_interes"));
		                reviewers.add(r);
		            }
		            System.out.println("Reviewers added to list!");
		            return reviewers;
		        } catch (Exception ex) {
		            System.out.println("ReviewerDAO-> reviewerList() : " + ex.getMessage());
		            return null;
		        }
		    }
		 
		 public static ArrayList<Article> reviewerArticleList(String email) { //email eshte primary key per shqyrtuesin
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("SELECT * FROM artikulli, shqyrtues_artikulli WHERE artikulli.artikull_id = shqyrtues_artikulli.art_id  AND shqyrtues_artikulli.sh_email=?");  
		            stmt.setString(1, email);
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
		            System.out.println("Reviewer Articles added to list!");
		            return artikujt;
		        } catch (Exception ex) {
		            System.out.println("ReviewerDAO-> reviewerArticleList() : " + ex.getMessage());
		            return null;
		        }
		 }
		 
		 public static String reviewerName(String email) {
			 try (Connection con = Database.getConnection()) {
		            PreparedStatement stmt=con.prepareStatement("select * from shqyrtuesi where email=?");  
		            stmt.setString(1, email);
		            ResultSet rs=stmt.executeQuery();
		            rs.next();
		            return rs.getString("emri");
		        } catch (Exception ex) {
		            System.out.println("ReviewrrDAO-> reviewerName() : " + ex.getMessage());
		            return null;
		        }
		 }
		 
		// Used to delete director record
	    public static void delete(String email){
	        try{
	        	Connection conn = Database.getConnection();  
	            PreparedStatement stmt = conn.prepareStatement("delete from shqyrtuesi where email = "+email);  
	            stmt.executeUpdate();  
	            System.out.println("Reviewer deleted successfully");
	        }catch(Exception e){
	        	System.out.println("ReviewerrDAO->delete() : " + e.getMessage());
	        }
	    }
			
	    // Used to save director record
	    public static void save(Reviewer r){
	        int result = 0;
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into shqyrtuesi(email, emri,mbiemri, tel,institucioni,temat_interes) values(?,?,?,?,?,?)");
	            stmt.setString(1, r.getEmail());
	            stmt.setString(2, r.getEmri());
	            stmt.setString(3, r.getMbiemri());
	            stmt.setString(4, r.getTel());
	            stmt.setString(5, r.getInsitucioni());
	            stmt.setString(6, r.getTemat_interes());
	            result = stmt.executeUpdate();
	            System.out.println("Reviewer saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("ReviewerDao->save() : " + e.getMessage());
	        }
	    }
	    
	    // Used to fetch record to update
	    public static Reviewer edit(String email){
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
	            return re;
	        }catch(Exception e){
	        	System.out.println("ReviewerDAO->edit() : " + e.getMessage());
	        	return null;
	        }       
	    }

	    public static boolean editReviewer(Reviewer re, String email) {
	        try (Connection con = Database.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("update shqyrtuesi set email =?, emri=?, mbiemri=?, tel =?, institucioni =?, teemat_interes =? where email =?");
	            ps.setString(1, re.getEmail());
	            ps.setString(2, re.getEmri());
	            ps.setString(3, re.getMbiemri());
	            ps.setString(4, re.getTel());
	            ps.setString(5, re.getInsitucioni());
	            ps.setString(6, re.getTemat_interes());
	            ps.setString(7, email);
	            System.out.println("Reviewer updated!");
	            int count = ps.executeUpdate();
	            return count == 1;
	        } catch (Exception ex) {
	            System.out.println("ReviewerDAO->editReviewer() : " + ex.getMessage());
	            return false;
	        }
	    }
	    
	    public static void saveAssignReviewer(String email, int merita_teknike, int kuptueshmeria, int origjinaliteti, int perkatesi_konference, String rekomandime, int art_id){
	        try{
	        	Connection conn = Database.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("insert into shqyrtues_artikulli(sh_email, art_id, merita_teknike, kuptueshmeria, origjinaliteti, perkatesi_konference, rekomandime) values(?,?,?,?,?,?,?)");
	            stmt.setInt(1, art_id);
	            stmt.setString(2, email);
	            stmt.setInt(3,merita_teknike);
	            stmt.setInt(4,kuptueshmeria);
	            stmt.setInt(5,origjinaliteti);
	            stmt.setInt(6,perkatesi_konference);
	            stmt.setString(7, rekomandime);
	            int result = stmt.executeUpdate();
	            System.out.println("Shqyrtues_Artikulli saved successfully!");
	            conn.close();
	        }catch(Exception e){
	        	System.out.println("ReviewerDAO->saveAssignReviewer() : " + e.getMessage());
	        }
	    }

	}


