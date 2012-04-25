
package org.springframework.social.google.docs.api.impl;


import org.springframework.social.google.docs.api.GoogleDocs;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

import com.google.gdata.client.docs.DocsService;


public class GoogleDocsTemplate
    extends AbstractOAuth2ApiBinding
    implements GoogleDocs
{
    private String accessToken;


    public GoogleDocsTemplate(String accessToken)
    {
        super(accessToken);
        this.accessToken = accessToken;
    }


    public DocsService setAuthentication(DocsService docsService)
    {
        docsService.setHeader("Authorization", "Bearer " + accessToken);
        return docsService;
    }
}
