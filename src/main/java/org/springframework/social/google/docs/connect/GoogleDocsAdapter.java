
package org.springframework.social.google.docs.connect;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.social.ApiException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.google.docs.api.GoogleDocs;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.docs.MetadataEntry;
import com.google.gdata.util.ServiceException;

public class GoogleDocsAdapter implements ApiAdapter<GoogleDocs>
{
    private static String BASE_URL = "https://docs.google.com/";

    private static String METADATA_URL = BASE_URL + "feeds/metadata/default";

    public UserProfile fetchUserProfile(GoogleDocs googleDocs)
    {
        MetadataEntry entry = null;
        try
        {
            entry = this.getUserMetadata(googleDocs);
        }
        catch (ServiceException error)
        {
            throw new ApiException(error.getMessage(), error);
        }
        
        if (entry != null){
        return new UserProfileBuilder().setName(entry.getAuthors().get(0).getName())
                    .setEmail(entry.getAuthors().get(0).getEmail())
                    .setUsername(entry.getAuthors().get(0).getName()).build();
        } else {
            return null;
        }
    }

    @Override
    public void setConnectionValues(GoogleDocs googleDocs, ConnectionValues values)
    {
        MetadataEntry entry = null;
        try
        {
            entry = this.getUserMetadata(googleDocs);
        }
        catch (ServiceException error)
        {
            throw new ApiException(ServiceException.class.getSimpleName(), error);
        }

        if (entry != null)
        {
            values.setProviderUserId(entry.getEtag());
            values.setDisplayName(entry.getAuthors().get(0).getName());
        }
    }

    public boolean test(GoogleDocs googleDocs)
    {
        try
        {
            this.getUserMetadata(googleDocs);
            return true;
        }
        catch (ApiException e)
        {
            return false;
        }
        catch (ServiceException e)
        {
            return false;
        }
    }

    public void updateStatus(GoogleDocs arg0, String arg1)
    {
        throw new OperationNotPermittedException("updateStates not implemented.");

    }

    private MetadataEntry getUserMetadata(GoogleDocs googleDocs) throws ServiceException
    {
        DocsService docsService = new DocsService("spring-social-google-docs/1.0");
        docsService = googleDocs.setAuthentication(docsService);

        try
        {
            return docsService.getEntry(new URL(METADATA_URL), MetadataEntry.class);
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
            throw e;
        }
    }
}
