package com.neovisionaries.android.oauth20;


public interface OAuth20ViewListener
{
    void onCodeIssued(OAuth20View view, String code, String state);
    void onTokenIssued(OAuth20View view, AccessToken accessToken, String state);
    void onError(OAuth20View view, OAuth20Error error, String description, String uri, String state);
}
