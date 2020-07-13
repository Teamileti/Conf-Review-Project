package maven_conference.Conf_Review;

import java.io.*;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.event.*;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class FormSettings implements Serializable {
  private static final long serialVersionUID = 1L;
  private boolean isEnglish = true;
  private static final Locale ENGLISH = new Locale("en");
  private static final Locale ITALIAN = new Locale("it");
  private Locale locale = ENGLISH;
  private String language = locale.getLanguage();
  private static final Map<String,String> LANGUAGE_MAP = 
    new LinkedHashMap<String, String>(); 
  
  static {
    LANGUAGE_MAP.put("English", "en");
    LANGUAGE_MAP.put("Italian", "it");
  }
  
  public Locale getLocale() {
    return(locale);
  }
  
  public void swapLocale(ActionEvent event) {
    isEnglish = !isEnglish;
    if (isEnglish) {
      locale = ENGLISH;
    } else {
      locale = ITALIAN;
    }
  }

  public String getLanguage() {
    return(language);
  }

  public void setLanguage(String language) {
    this.language = language;
    locale = new Locale(language);
  }

  public Map<String,String> getLanguages() {
    return(LANGUAGE_MAP);
  }
}
