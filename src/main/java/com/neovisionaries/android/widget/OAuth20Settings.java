package com.neovisionaries.android.widget;


import java.net.URL;
import java.util.HashSet;
import java.util.Set;


public class OAuth20Settings
{
    public static enum ResponseType
    {
        CODE,
        TOKEN
        ;
    }


    private URL mAuthorizationEndpoint;
    private ResponseType mResponseType;
    private String mClientId;
    private URL mRedirectUri;
    private Set<String> mScopeSet;
    private String mState;


    /**
     * Package-private constructor.
     */
    OAuth20Settings()
    {
    }


    public URL getAuthorizationEndpoint()
    {
        return mAuthorizationEndpoint;
    }


    public OAuth20Settings setAuthorizationEndpoint(URL endpoint)
    {
        mAuthorizationEndpoint = endpoint;

        return this;
    }


    public ResponseType getResponseType()
    {
        return mResponseType;
    }


    public OAuth20Settings setResponseType(ResponseType responseType)
    {
        mResponseType = responseType;

        return this;
    }


    public String getClientId()
    {
        return mClientId;
    }


    public OAuth20Settings setClientId(String clientId)
    {
        mClientId = clientId;

        return this;
    }


    public URL getRedirectUri()
    {
        return mRedirectUri;
    }


    public OAuth20Settings setRedirectUri(URL redirectUri)
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


    public OAuth20Settings setScopeSet(Set<String> scopeSet)
    {
        mScopeSet = scopeSet;

        return this;
    }


    public OAuth20Settings addScopes(String... scopes)
    {
        if (mScopeSet == null)
        {
            mScopeSet = new HashSet<String>();
        }

        for (String scope : scopes)
        {
            if (scope == null)
            {
                continue;
            }

            validateScope(scope);

            mScopeSet.add(scope);
        }

        return this;
    }


    public OAuth20Settings removeScopes(String... scopes)
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


    public OAuth20Settings setState(String state)
    {
        mState = state;

        return this;
    }


    private void validateRedirectUri(URL redirectUri)
    {
        // TODO
    }


    private void validateScope(String scope)
    {
        // TODO
    }


    private void validate()
    {
        if (mAuthorizationEndpoint == null)
        {
            throw new IllegalStateException("Authorization endpoint is not set.");
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


    URL buildUrl()
    {
        validate();

        // TODO
        return null;
    }
}
