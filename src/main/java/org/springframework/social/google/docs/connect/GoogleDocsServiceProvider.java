
package org.springframework.social.google.docs.connect;


import org.springframework.social.google.docs.api.GoogleDocs;
import org.springframework.social.google.docs.api.impl.GoogleDocsTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;


public class GoogleDocsServiceProvider
    extends AbstractOAuth2ServiceProvider<GoogleDocs>
{
    public GoogleDocsServiceProvider(String consumerKey, String consumerSecret)
    {
        super(new OAuth2Template(consumerKey, consumerSecret, "https://accounts.google.com/o/oauth2/auth", "https://accounts.google.com/o/oauth2/token"));
    }


    @Override
    public GoogleDocs getApi(String accessToken)
    {
        return new GoogleDocsTemplate(accessToken);
    }
}
