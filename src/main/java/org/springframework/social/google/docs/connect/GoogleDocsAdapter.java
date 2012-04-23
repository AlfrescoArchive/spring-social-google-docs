
package org.springframework.social.google.docs.connect;


import org.springframework.social.ApiException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.google.docs.api.GoogleDocs;

import com.google.gdata.data.docs.MetadataEntry;


public class GoogleDocsAdapter
    implements ApiAdapter<GoogleDocs>
{

    public UserProfile fetchUserProfile(GoogleDocs googleDocs)
    {
        MetadataEntry entry = googleDocs.getUserMetadata();
        return new UserProfileBuilder().setName(entry.getAuthors().get(0).getName()).setEmail(entry.getAuthors().get(0).getEmail()).setUsername(entry.getAuthors().get(0).getName()).build();
    }


    public void setConnectionValues(GoogleDocs googleDocs, ConnectionValues values)
    {
        MetadataEntry entry = googleDocs.getUserMetadata();
        values.setProviderUserId(entry.getEtag());
        values.setDisplayName(entry.getAuthors().get(0).getName());
    }


    public boolean test(GoogleDocs googleDocs)
    {
        try
        {
            googleDocs.getUserMetadata();
            return true;
        }
        catch (ApiException e)
        {
            return false;
        }
    }


    public void updateStatus(GoogleDocs arg0, String arg1)
    {
        throw new OperationNotPermittedException("updateStates not implemented.");

    }

}
