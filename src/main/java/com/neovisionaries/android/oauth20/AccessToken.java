package com.neovisionaries.android.oauth20;


public class AccessToken
{
    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String scope;


    public String getAccessToken()
    {
        return access_token;
    }


    public AccessToken setAccessToken(String accessToken)
    {
        this.access_token = accessToken;

        return this;
    }


    public String getTokenType()
    {
        return token_type;
    }


    public AccessToken setTokenType(String tokenType)
    {
        this.token_type = tokenType;

        return this;
    }


    public int getExpiresIn()
    {
        return expires_in;
    }


    public AccessToken setExpiresIn(int expiresIn)
    {
        this.expires_in = expiresIn;

        return this;
    }


    public String getRefreshToken()
    {
        return refresh_token;
    }


    public AccessToken setRefreshToken(String refreshToken)
    {
        this.refresh_token = refreshToken;

        return this;
    }


    public String getScope()
    {
        return scope;
    }


    public AccessToken setScope(String scope)
    {
        this.scope = scope;

        return this;
    }


    public String[] getScopes()
    {
        if (scope == null)
        {
            return null;
        }

        return scope.split(" +");
    }
}
