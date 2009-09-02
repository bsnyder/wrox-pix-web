using System; 
using System.Data; 

public class Client { 
	public static void Main( ) 
	{ 
		PixUser user = new PixUser();

		Affiliate aff = new Affiliate();

		user.userName ="netuser";
		user.password ="netuser";
		user.email = "netuser@yahoo.com";
		user.firstName ="net";
		user.lastName = "user";

		aff.userName ="netaffiliate";
		aff.password ="netaffiliate";
		aff.email = "netaffiliate@yahoo.com";
		aff.firstName = "net";
		aff.lastName = "affiliate";
		aff.websiteURL = "http://netaffiliate.org";
		aff.companyName = "dummyaff";

		AffiliateManagmentServiceImpl proxy = new AffiliateManagmentServiceImpl(); 
		

		WriteMessage("Invoking Affiliate Management  Web Service."); 
		try 
		{ 
		 
		 //Registered Affiliate
		 proxy.enrollAffiliate(aff);
	 	 WriteMessage("Registered affiliate " + aff.companyName); 

		 proxy.enrollUserViaAffiliateWebSite(user,aff);
 	 	 WriteMessage("Enrolled user " + user.userName); 

		
		}
		catch(Exception e) 
		{Console.WriteLine("Threw general exception: {0}", e);} 
	}

	private static void WriteMessage(string message) 
	{
		Console.WriteLine("Server returns: {0}", message); 
	}
}
