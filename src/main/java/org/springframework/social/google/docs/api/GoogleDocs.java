
package org.springframework.social.google.docs.api;


import com.google.gdata.client.docs.DocsService;


public interface GoogleDocs
{
    /**
     * The neeed Scopes for access documentList API
     * 
     * https://docs.google.com/feeds/
     * https://docs.googleusercontent.com/
     * https://spreadsheets.google.com/feeds/
     * 
     */
    public static final String SCOPE = "https://docs.google.com/feeds/ https://docs.googleusercontent.com/ https://spreadsheets.google.com/feeds/";

    public DocsService setAuthentication(DocsService docsService);
}
