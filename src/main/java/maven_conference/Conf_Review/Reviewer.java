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
public class Reviewer {
	
	ArrayList reviewerList, reviewerArticleList;
	private List<Reviewer> filteredReviewerList;
	
	private String email, emri, mbiemri, message, tel, insitucioni, temat_interes, rekomandime, statusi; //rekomandime i perket tabeles shqyrtues-artikulli
   private int merita_teknike, kuptueshmeria, origjinaliteti, perkatesi_konference;
	
	private String ch = " Not decided for ";
	public String getRekomandime() {
	return rekomandime;
}

public void setRekomandime(String rekomandime) {
	this.rekomandime = rekomandime;
}

public int getMerita_teknike() {
	return merita_teknike;
}

public void setMerita_teknike(int merita_teknike) {
	this.merita_teknike = merita_teknike;
}

public int getKuptueshmeria() {
	return kuptueshmeria;
}

public void setKuptueshmeria(int kuptueshmeria) {
	this.kuptueshmeria = kuptueshmeria;
}

public int getOrigjinaliteti() {
	return origjinaliteti;
}

public void setOrigjinaliteti(int origjinaliteti) {
	this.origjinaliteti = origjinaliteti;
}

public int getPerkatesi_konference() {
	return perkatesi_konference;
}

public void setPerkatesi_konference(int perkatesi_konference) {
	this.perkatesi_konference = perkatesi_konference;
}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getInsitucioni() {
		return insitucioni;
	}

	public void setInsitucioni(String insitucioni) {
		this.insitucioni = insitucioni;
	}

	public String getTemat_interes() {
		return temat_interes;
	}

	public void setTemat_interes(String temat_interes) {
		this.temat_interes = temat_interes;
	}

	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	
	public ArrayList reviewerList(){
		reviewerList = ReviewerDao.reviewerList();
		
		return reviewerList;
	}
	
	public String list() {
		return "/user_panel/reviewer?faces-redirect=true";
	}
	
	public ArrayList reviewerArticleList(String email){
		reviewerArticleList = ReviewerDao.reviewerArticleList(email);
		return reviewerArticleList;
	}
	
	public String articles(String email) {
		String name = ReviewerDao.reviewerName(email);
		sessionMap.put("email", email);
		sessionMap.put("reviewer_name", emri);

		return "reviewer_articles?faces-redirect=true";
	}

	public String adminList() {
    	return "/admin_panel/reviewer_index?faces-redirect=true";
    }
    
    // Used to delete director record
    public void delete(String email){
        ReviewerDao.delete(email);
    }
    
    // Used to create new director record
    public String saveReviewer() {
    	ReviewerDao.save(this);
    	message = "Success! New reviewer created!";
    	return "reviewer_create";
    }
    
    // Used to edit director record
    public String edit(String email) {
    	Reviewer re = ReviewerDao.edit(email);
    	this.email = email;
    	this.emri = re.getEmri();
    	this.mbiemri = re.getMbiemri();
    	this.tel = re.getTel();
    	this.insitucioni = re.getInsitucioni();
    	this.temat_interes = re.getTemat_interes();   	
    	return "reviewer_edit";
    }
    
    public String editReviewer(String email) {
    	boolean done = ReviewerDao.editReviewer(this, email);
        if ( done ) {
        	 message = "Success! Reviewer updated!";
             return "reviewer_index";
        }else {
             message = "Sorry! Could not update reviewer. Please try again!";
             return "reviewer_edit";
        }
    }

    public String saveAssignReviewer(int art_id) {
    	ReviewerDao.saveAssignReviewer(this.email, this.merita_teknike, this.kuptueshmeria, this.origjinaliteti, this.perkatesi_konference, this.rekomandime, this.statusi, art_id);
    	message = "Success! New assign reviewer created!";
    	return "reviewer_article_feedback";
    }

	public String getStatusi() {
		return statusi;
	}

	public void setStatusi(String statusi) {
		this.statusi = statusi;
	}
	public String Choice(String ch) {
		if (ch == "Dissaprove") {
			return "Dissaproved";
		}
		else if (ch== "Approve") {
			return "Approved";
		}
		return ch;
	}
}
