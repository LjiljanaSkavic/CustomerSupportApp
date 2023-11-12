package services;

import java.io.Serializable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import beans.EmailBean;

public class EmailService implements Serializable {

	private static final long serialVersionUID = -2721286242909250128L;
	
	public String replayEmail(EmailBean emailBean) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8090/email/sendMail");
		Response response = target.request("application/json").post(Entity.json(emailBean));
		System.out.println(response);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(String.class);
		}
		return "";
	}
	
}