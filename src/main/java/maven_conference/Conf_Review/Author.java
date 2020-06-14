package maven_conference.Conf_Review;
	
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
	public class Author {

		ArrayList authorList, authorArticleList;
		
		private String email_id, emri, mbiemri, message; 
		

		public String getEmail_id() {
			return email_id;
		}

		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}

		public String getEmri() {
			return emri;
		}

		public void setEmri(String emri) {
			this.emri = emri;
		}

		public String getMbiemri() {
			return mbiemri;
		}

		public void setMbiemri(String mbiemri) {
			this.mbiemri = mbiemri;
		}
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		
		public ArrayList authorList(){
			authorList = AuthorDAO.authorList();
			
			return authorList;
		}
		
		public String list() {
			return "/user_panel/actor?faces-redirect=true";
		}
		
		public ArrayList authorArticlesList(String id){
			authorArticleList = AuthorDAO.authorArticlesList(id);
			return authorArticleList;
		}
		
		
		public String article(String email_id) { //email_id eshte primary key per autoret
			String name = AuthorDAO.authorName(email_id);
			sessionMap.put("email_id", email_id); 
			sessionMap.put("emri", name);

			return "/user_panel/author_article?faces-redirect=true";
		}
		

		public String adminList() {
	    	return "/admin_panel/author_index?faces-redirect=true";
	    }
	    
	    // Used to delete actor record
	    public void delete(String id){
	        AuthorDAO.delete(id);
	    }
	    
	    // Used to create new actor record
	    public String saveAuthor() {
	    	AuthorDAO.save(this);
	    	message = "Success! New actor created!";
	    	return "author_create";
	    }
	    
	    // Used to edit actor record
	    public String edit(String email_id) {
	    	Author a = AuthorDAO.edit(email_id);
	    	this.email_id = email_id;
	    	this.emri = a.getEmri();
	    	this.mbiemri = a.getMbiemri();
	    	return "author_edit";
	    }
	    
	    public String editAuthor(String id) {
	    	boolean done = AuthorDAO.editAuthor(this, id);
	        if ( done ) {
	        	 message = "Success! Author updated!";
	             return "author_index";
	        }else {
	             message = "Sorry! Could not update author. Please try again!";
	             return "author_edit";
	        }
	    }
	    /*
	    
	    public String saveAssignActor(int movie_id) {
	    	ActorDAO.saveAssignActor(this.aktor_id, this.citimi, movie_id);
	    	message = "Success! New assign actor created!";
	    	return "movie_assign_actors";
	    }

	}
	*/
}
