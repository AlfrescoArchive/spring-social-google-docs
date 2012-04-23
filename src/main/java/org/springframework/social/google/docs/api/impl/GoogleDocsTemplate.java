
package org.springframework.social.google.docs.api.impl;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.social.ApiException;
import org.springframework.social.google.docs.api.GoogleDocs;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.docs.MetadataEntry;
import com.google.gdata.util.ServiceException;


public class GoogleDocsTemplate
    extends AbstractOAuth2ApiBinding
    implements GoogleDocs
{
    private static String metadataURL = "https://docs.google.com/feeds/metadata/default";
    private String        accessToken;


    public GoogleDocsTemplate(String accessToken)
    {
        super(accessToken);
        this.accessToken = accessToken;
    }


    private DocsService getDocsService()
    {
        DocsService docsService = new DocsService("Alfresco-GoogleDocs/2.0");
        docsService.setHeader("Authorization", "OAuth " + accessToken);

        return docsService;
    }


    public MetadataEntry getUserMetadata()
    {
        try
        {
            return getDocsService().getEntry(new URL(metadataURL), MetadataEntry.class);
        }
        catch (MalformedURLException e)
        {
            throw new ApiException(e.getMessage());
        }
        catch (IOException e)
        {
            throw new ApiException(e.getMessage());
        }
        catch (ServiceException e)
        {
            throw new ApiException(e.getMessage());
        }
    }

}
