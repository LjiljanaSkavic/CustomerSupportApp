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

	private static final long serialVersionUID = 4952863914488455345L;
	
	public String replayEmail(EmailBean emailBean) {
		Client client = ClientBuilder.newClient();
		System.out.println(client);
		WebTarget target = client.target("http://localhost:8080/email");
		System.out.println(target);
		System.out.println(Entity.json(emailBean));
		Response response = target.request("application/json").post(Entity.json(emailBean));
		System.out.println(response);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(String.class);
		}
		return "";
	}

}
