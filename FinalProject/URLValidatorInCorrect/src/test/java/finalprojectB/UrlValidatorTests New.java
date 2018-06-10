
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
		//You can use this function to implement your manual testing	   
		UrlValidator UVal=new UrlValidator(new String[0], UrlValidator.ALLOW_ALL_SCHEMES);
		String url;
		try{
		assertEquals("Null url",false,UVal.isValid(null));
		url="https://www.google.com";
		boolean result=UVal.isValid(url);
		assertEquals(url,true,result);
		url="http://serverip/~foldername";
		assertEquals(url, true, UVal.isValid(url));

		url="http:///foldername";
		assertEquals(url, true, UVal.isValid(url));
		url="www.google";
		assertEquals(url, false, UVal.isValid(url));
		url="ftp:/google.comeomsdofmsdofksdf";
		assertEquals(url, false, UVal.isValid(url));
		url="https://stackoverflow.com/questions/50787507/how-to-get-file-names-or-index-within-tar-or-zip-without-uncompressing-it";
		assertEquals(url, true, UVal.isValid(url));
		url="255.254.243.255";
		assertEquals(url, true, UVal.isValid(url));}finally{}
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing
		//You can use this function for programming based testing
		UrlValidator UVal=new UrlValidator(null,null,UrlValidator.ALLOW_ALL_SCHEMES);
		String url;
		boolean valid=false;
		for(int i=0; i<testUrlScheme.length; i++){
			for(int j=0; j<testUrlAuthority.length; j++){
				for(int k=0; k<testUrlPort.length; k++){
					url=testUrlScheme[i].item +
						testUrlAuthority[j].item +
						testUrlPort[k].item;
					valid=testUrlScheme[i].valid &
						testUrlAuthority[j].valid &
						testUrlPort[k].valid;
					
					for(int h=0; h<testPath.length; h++){
						assertEquals("Failed on: "+url, valid & testPath[h].valid, UVal.isValid(url+testPath[h].item));
					}
					for(int h=0; h<testUrlQuery.length; h++){
						assertEquals("Failed on: "+url, valid & testPath[h].valid, UVal.isValid(url+testPath[h].item));
					}
				}
			}
		}
   }
   
   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
											new ResultPair("ftp://", true),
											new ResultPair("h3t://", true),
											new ResultPair("3ht://", false),
											new ResultPair("http:/", false),
											new ResultPair("http:", false),
											new ResultPair("http/", false),
											new ResultPair("://", false),
											new ResultPair("", true)};

   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com", true),
												new ResultPair("go.com", true),
												new ResultPair("go.au", true),
												new ResultPair("0.0.0.0", true),
												new ResultPair("255.255.255.255", true),
												new ResultPair("256.256.256.256", false),
												new ResultPair("255.com", true),
												new ResultPair("1.2.3.4.5", false),
												new ResultPair("1.2.3.4.", false),
												new ResultPair("1.2.3", false),
												new ResultPair(".1.2.3.4", false),
												new ResultPair("go.a", false),
												new ResultPair("go.a1a", false),
												new ResultPair("go.1aa", false),
												new ResultPair("aaa.", false),
												new ResultPair(".aaa", false),
												new ResultPair("aaa", false),
												new ResultPair("", false)
   };
   ResultPair[] testUrlPort = {	new ResultPair(":80", true),
											new ResultPair(":65535", true),
											new ResultPair(":0", true),
											new ResultPair("", true),
											new ResultPair(":-1", false),
											new ResultPair(":65636",false),
											new ResultPair(":65a", false)
   };
   ResultPair[] testPath = {	new ResultPair("/test1", true),
										new ResultPair("/t123", true),
										new ResultPair("/$23", true),
										new ResultPair("/..", false),
										new ResultPair("/../", false),
										new ResultPair("/test1/", true),
										new ResultPair("", true),
										new ResultPair("/test1/file", true),
										new ResultPair("/..//file", false),
										new ResultPair("/test1//file", false)
   };
   //Test allow2slash, noFragment
   ResultPair[] testUrlPathOptions = {	new ResultPair("/test1", true),
													new ResultPair("/t123", true),
													new ResultPair("/$23", true),
													new ResultPair("/..", false),
													new ResultPair("/../", false),
													new ResultPair("/test1/", true),
													new ResultPair("/#", false),
													new ResultPair("", true),
													new ResultPair("/test1/file", true),
													new ResultPair("/t123/file", true),
													new ResultPair("/$23/file", true),
													new ResultPair("/../file", false),
													new ResultPair("/..//file", false),
													new ResultPair("/test1//file", true),
													new ResultPair("/#/file", false)
   };

   ResultPair[] testUrlQuery = {	new ResultPair("?action=view", true),
											new ResultPair("?action=edit&mode=up", true),
											new ResultPair("", true)
   };

   Object[] testUrlParts = {testUrlScheme, testUrlAuthority, testUrlPort, testPath, testUrlQuery};
   Object[] testUrlPartsOptions = {testUrlScheme, testUrlAuthority, testUrlPort, testUrlPathOptions, testUrlQuery};

   //---------------- Test data for individual url parts ----------------
   ResultPair[] testScheme = {new ResultPair("http", true),
										new ResultPair("ftp", false),
										new ResultPair("httpd", false),
										new ResultPair("telnet", false)
	};
}
