spring-social-google-docs
=========================

Spring Social Google Docs

This is a Spring Social OAuth2 wrapper to the Google DocsService.

How to use

	GoogleDocsConnectionFactory connectionFactory = new GoogleDocsConnectionFactory(consumerKey, consumerSecret);
	
	OAuth2Parameters parameters = new OAuth2Parameters();
    	parameters.setRedirectUri(redirectUri);
        parameters.setScope(GoogleDocs.SCOPE);
        parameters.setState(state);
        //If your application needs offline access
        //parameters.set("access_type","offline");
    
    String = authUrl = connectionFactory.getOAuthOperations().buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters);
	
	//Do the dance here ...
	
	AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(accessToken, redirectUri, null);

	//Persist your tokens here ...

	Connection<GoogleDocs> connection = connectionFactory.createConnection(accessGrant);
    GoogleDocs google = connection.getApi();
    
    DocsService docsService = new DocsService("your app name/1.0");
    docsService = google.setAuthentication(docsService);
    
    //Use docsService client here ...
    
    
 Why should I use this one and not spring-social-google?
 
 	Spring Social Google maybe what you need.  We are building an integration between Alfresco (http://www.alfresco.com) and
 	Google Docs.  Alfresco already has a very large code base and includes some older versions of the libraries required by
 	Spring Social Google.  Downgrading those libraries broke Spring Social Google.  Upgrading those libraries is a this time
 	outside the scope of the project.  It would also cause backwards compatibility issues across the current enterprise release
 	of Alfresco: our targeted platform.

    