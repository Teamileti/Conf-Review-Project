package maven_conference.Conf_Review;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;
	import java.util.Map;
	import java.util.Properties;
	import javax.activation.DataHandler;
	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.RequestScoped;
	import javax.faces.context.FacesContext;
	import javax.faces.event.ActionEvent;
	import javax.mail.Message;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

	@ManagedBean
	@RequestScoped 
	public class Article {
		ArrayList articleList, articleAuthorList, articleShqyrtuesList;
		private String titulli, abstrakti, doc_name;
		private int artikull_id;
		private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	
		public String getTitulli() {
			return titulli;
		}

		public void setTitulli(String titulli) {
			this.titulli = titulli;
		}

		public String getAbstrakti() {
			return abstrakti;
		}

		public void setAbstrakti(String abstrakti) {
			this.abstrakti = abstrakti;
		}

		public String getDoc_name() {
			return doc_name;
		}

		public void setDoc_name(String doc_name) {
			this.doc_name = doc_name;
		}

		public int getArtikull_id() {
			return artikull_id;
		}

		public void setArtikull_id(int artikull_id) {
			this.artikull_id = artikull_id;
		}

		public ArrayList articleList(){
			articleList = ArticleDAO.articleList();
			
			return articleList;
		}
		
		public String list() {
			return "/user_panel/article?faces-redirect=true";
		}
		
		/* public ArrayList articleAuthorList(int id){
			articleAuthorList = ArticleDAO.articleAuthorList(id);
			return articleAuthorList;
		}
		
	   public String actors(int film_id) {
			String title = MovieDAO.movieTitle(film_id);
			sessionMap.put("film_id", film_id);
			sessionMap.put("title", title);

			return "/user_panel/movie_actors?faces-redirect=true";
		}
	
		public ArrayList articleShqyrtuesList(int id){
			articleShqyrtuesList = ArticleDAO.articleShqyrtuesList(id);
			return articleShqyrtuesList;
		}
		
		/*public String directors(int film_id) {
			String title = MovieDAO.movieTitle(film_id);
			sessionMap.put("film_id", film_id);
			sessionMap.put("title", title);

			return "/user_panel/movie_directors?faces-redirect=true";
		}
	    
	    public String adminList() {
	    	return "/admin_panel/movie_index?faces-redirect=true";
	    }
	    
	 // Used to delete user record
	    public void delete(int id){
	        MovieDAO.delete(id);
	    }
	    
	    // Used to create new movie record
	    public String saveMovie() {
	    	MovieDAO.save(this);
	    	message = "Success! New movie created!";
	    	return "movie_create";
	    }
	    
	    // Used to edit movie record
	    public String edit(int movie_id) {
	    	Movie movie = MovieDAO.edit(movie_id);
	    	this.film_id = movie_id;
	    	this.titulli = movie.getTitulli();
	    	this.viti = movie.getViti();
	    	this.gjatesia = movie.getGjatesia();
	    	this.skenari = movie.getSkenari();
	    	this.kompania = movie.getKompania();
	    	return "movie_edit";
	    }
	    
	    public String editMovie(int id) {
	    	boolean done = MovieDAO.editMovie(this, id);
	        if ( done ) {
	        	 message = "Success! Movie updated!";
	             return "movie_index";
	        }else {
	             message = "Sorry! Could not update movie. Please try again!";
	             return "movie_edit";
	        }
	    }
	    
	    public String assignActor(int movie_id) {
			String title = MovieDAO.movieTitle(movie_id);
			sessionMap.put("film_id", movie_id);
			sessionMap.put("title", title);

			return "/user_panel/movie_assign_actor?faces-redirect=true";
	    }

	    public String assignDirector(int movie_id) {
			String title = MovieDAO.movieTitle(movie_id);
			sessionMap.put("film_id", movie_id);
			sessionMap.put("title", title);

			return "/user_panel/movie_assign_director?faces-redirect=true";
	    }
	    
	}
	*/
}
