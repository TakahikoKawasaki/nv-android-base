package com.neovisionaries.android.oauth20;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;


public class AuthorizationRequest
{
    private URL mEndpoint;
    private ResponseType mResponseType;
    private String mClientId;
    private URL mRedirectUri;
    private Set<String> mScopeSet;
    private String mState;


    public URL getEndpoint()
    {
        return mEndpoint;
    }


    public AuthorizationRequest setEndpoint(URL endpoint)
    {
        mEndpoint = endpoint;

        return this;
    }


    public ResponseType getResponseType()
    {
        return mResponseType;
    }


    public AuthorizationRequest setResponseType(ResponseType responseType)
    {
        mResponseType = responseType;

        return this;
    }


    public String getClientId()
    {
        return mClientId;
    }


    public AuthorizationRequest setClientId(String clientId)
    {
        mClientId = clientId;

        return this;
    }


    public URL getRedirectUri()
    {
        return mRedirectUri;
    }


    public AuthorizationRequest setRedirectUri(URL redirectUri)
    {
        if (redirectUri != null)
        {
            validateRedirectUri(redirectUri);
        }

        mRedirectUri = redirectUri;

        return this;
    }


    public Set<String> getScopeSet()
    {
        return mScopeSet;
    }


    public AuthorizationRequest setScopeSet(Set<String> scopeSet)
    {
        mScopeSet = scopeSet;

        return this;
    }


    public AuthorizationRequest addScopes(String... scopes)
    {
        if (mScopeSet == null)
        {
            mScopeSet = new HashSet<String>();
        }

        for (String scope : scopes)
        {
            if (scope == null || scope.length() == 0)
            {
                continue;
            }

            validateScope(scope);

            mScopeSet.add(scope);
        }

        return this;
    }


    public AuthorizationRequest removeScopes(String... scopes)
    {
        if (mScopeSet == null)
        {
            return this;
        }

        for (String scope : scopes)
        {
            if (scope != null)
            {
                mScopeSet.remove(scope);
            }
        }

        return this;
    }


    public String getState()
    {
        return mState;
    }


    public AuthorizationRequest setState(String state)
    {
        mState = state;

        return this;
    }


    private void validateRedirectUri(URL redirectUri)
    {
        if (redirectUri.getRef() != null)
        {
            throw new IllegalArgumentException("Redirect URI must not include a fragment component.");
        }
    }


    private void validateScope(String scope)
    {
        int len = scope.length();

        for (int i = 0; i < len; ++i)
        {
            char ch = scope.charAt(i);

            if (ch < 0x21 || 0x7E < ch || ch == 0x22 || ch == 0x5C)
            {
                throw new IllegalArgumentException("Bad scope: " + scope);
            }
        }
    }


    private void validate()
    {
        if (mEndpoint == null)
        {
            throw new IllegalStateException("Endpoint is not set.");
        }

        if (mResponseType == null)
        {
            throw new IllegalStateException("Response type is not set.");
        }

        if (mClientId == null)
        {
            throw new IllegalStateException("Client ID is not set.");
        }
    }


    public URL toURL()
    {
        // Validate parameters.
        validate();

        // Query component.
        String query = buildQuery();

        String url = new StringBuilder()
                .append(mEndpoint.getProtocol())
                .append("://")
                .append(mEndpoint.getAuthority())
                .append(mEndpoint.getPath())
                .append("?")
                .append(query)
                .toString();

        try
        {
            return new URL(url);
        }
        catch (MalformedURLException e)
        {
            // This should not happen.
            e.printStackTrace();
            return null;
        }
    }


    private String buildQuery()
    {
        StringBuilder queryBuilder = new StringBuilder();

        // Get the query component of the authorization endpoint.
        String query = mEndpoint.getQuery();

        // If the authorization endpoint includes a query component.
        if (query != null)
        {
            queryBuilder.append(query).append("&");
        }

        // response_type (REQUIRED)
        queryBuilder.append("response_type=").append(mResponseType.name());

        // client_id (REQUIRED)
        queryBuilder.append("&client_id=").append(urlEncode(mClientId));

        // redirect_uri (OPTIONAL)
        if (mRedirectUri != null)
        {
            queryBuilder.append("&redirect_uri=").append(urlEncode(mRedirectUri.toString()));
        }

        // scope (OPTIONAL)
        if (mScopeSet != null)
        {
            String scope = buildScope();
            queryBuilder.append("scope=").append(urlEncode(scope));
        }

        // state (RECOMMENDED)
        if (mState != null)
        {
            queryBuilder.append("&state=").append(urlEncode(mState));
        }

        return queryBuilder.toString();
    }


    private String buildScope()
    {
        StringBuilder scopeBuilder = new StringBuilder();

        for (String scope : mScopeSet)
        {
            scopeBuilder.append(scope).append(" ");
        }

        if (scopeBuilder.length() != 0)
        {
            // Remove the space at the end.
            scopeBuilder.setLength(scopeBuilder.length() - 1);
        }

        return scopeBuilder.toString();
    }


    private static String urlEncode(String value)
    {
        try
        {
            return URLEncoder.encode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This never happens.
            return value;
        }
    }
}
