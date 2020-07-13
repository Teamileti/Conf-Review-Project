package maven_conference.Conf_Review;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;
import javax.mail.Part;

@ManagedBean
@ViewScoped
public class UploadBean {

	private Part file;
	
	public void upload() throws IOException, MessagingException{
		Scanner s = new Scanner(file.getInputStream());
		String fileContent = s.useDelimiter("\\A").next();
		s.close();
		
		Files.write(Paths.get("C:\\" + file.getFileName()), fileContent.getBytes(), StandardOpenOption.CREATE);
		
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	public void validate(FacesContext context, UIComponent component, Object value) throws MessagingException {
		Part file = (Part) value;
		if (!file.getContentType().equals("text/plain"))
				throw new ValidatorException(new FacesMessage("File is not a text file")); 
		
	}
	
}
/* 
 * <h:form enctype = "multipart/form-data"> 
<h:inputFile id="file" value = "#{uploadBean.file}"/>
<h:commandButton value = "Upload" action = "#{uploadBean.upload}" ></h:commandButton>
</h:form>
*/
