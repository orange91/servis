package servis.client;

public class ClientDetails{

	private int idClient;
	private String clientName;
	private String clientAddress;
	private String clientSurName;
	private String clientComments;
	private int clientPhoneNumber;
	
	ClientDetails(){
	}

	public int getidClient(){
		return idClient;
	}
	public void setBookId(int idClient){
		this.idClient = idClient;
	}

	public String getClientName(){
		return clientName;
	}
	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public String getClientAddress(){
		return clientAddress;
	}
	public void setClientAddress(String clientAddress){
		this.clientAddress = clientAddress;
	}
	
	public String getClientSurName(){
		return clientSurName;
	}

	public void setClientSurName(String clientSurName){
		this.clientSurName = clientSurName;
	}
	
	public String getClientComments(){
		return clientComments;
	}
	
	public void setClientComments(String clientComments){
		this.clientComments = clientComments;
	}
	
	public int getClientPhoneNumber(){
		return clientPhoneNumber;
	}
	
	public void setClientPhoneNumber(int clientPhoneNumber){
		this.clientPhoneNumber = clientPhoneNumber;
	}

	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Client Name: ").append(clientName);
		sb.append("Client SurName: ").append(clientSurName);
		sb.append("Client Address: ").append(clientAddress);
		sb.append("Client PhoneNumber: ").append(clientPhoneNumber);
		sb.append("Client Comments: ").append(clientComments);	
		return sb.toString();
	}
}