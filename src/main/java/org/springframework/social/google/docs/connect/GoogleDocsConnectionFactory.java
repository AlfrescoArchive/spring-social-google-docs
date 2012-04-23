
package org.springframework.social.google.docs.connect;


import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.google.docs.api.GoogleDocs;


public class GoogleDocsConnectionFactory
    extends OAuth2ConnectionFactory<GoogleDocs>
{

    public GoogleDocsConnectionFactory(String consumerKey, String consumerSecret)
    {
        super("googledocs", new GoogleDocsServiceProvider(consumerKey, consumerSecret), new GoogleDocsAdapter());
    }

}
