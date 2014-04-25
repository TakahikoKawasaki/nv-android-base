/*
 * Copyright (C) 2014 Neo Visionaries Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.neovisionaries.android.oauth20;


/**
 * Access token.
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
public class AccessToken
{
    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String scope;


    /**
     * Get the access token (= the value of {@code access_token}
     * (REQUIRED parameter)).
     *
     * @return
     *         The access token.
     */
    public String getAccessToken()
    {
        return access_token;
    }


    /**
     * Set the access token (= the value of {@code access_token}
     * (REQUIRED parameter)).
     *
     * @param accessToken
     *         The access token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setAccessToken(String accessToken)
    {
        this.access_token = accessToken;

        return this;
    }


    /**
     * Get the token type (= the value of {@code token_type}
     * (REQUIRED parameter)).
     *
     * @return
     *         The token type.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-7.1"
     *      >RFC 6749 (OAuth 2.0), 7.1. Access Token Types</a>
     */
    public String getTokenType()
    {
        return token_type;
    }


    /**
     * Set the token type (= the value of {@code token_type}
     * (REQUIRED parameter)).
     *
     * @param tokenType
     *         The token type.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setTokenType(String tokenType)
    {
        this.token_type = tokenType;

        return this;
    }


    /**
     * Get the lifetime of the access token in seconds (= the value of
     * {@code expires_in} parameter (RECOMMENDED parameter)).
     *
     * @return
     *         The lifetime of the access token in seconds.
     *         0 may be returned when the value is not available.
     */
    public int getExpiresIn()
    {
        return expires_in;
    }


    /**
     * Set the lifetime of the access token in seconds (= the value of
     * {@code expires_in} parameter (RECOMMENDED parameter)).
     *
     * @param expiresIn
     *         The lifetime of the access token in seconds.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setExpiresIn(int expiresIn)
    {
        this.expires_in = expiresIn;

        return this;
    }


    /**
     * Get the refresh token (= the value of {@code refresh_token} parameter
     * (OPTIONAL parameter)).
     *
     * @return
     *         The refresh token. {@code null} may be returned when
     *         the value is not available.
     */
    public String getRefreshToken()
    {
        return refresh_token;
    }


    /**
     * Set the refresh token (= the value of {@code refresh_token} parameter
     * (OPTIONAL parameter)).
     *
     * @param refreshToken
     *         The refresh token.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setRefreshToken(String refreshToken)
    {
        this.refresh_token = refreshToken;

        return this;
    }


    /**
     * Get the space-delimited scope list (= the value of {@code scope}
     * parameter (OPTIONAL parameter)).
     *
     * <p>
     * <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     * >RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a> says <i>
     * "The value of the scope parameter is expressed as a list of
     * <b>space</b>-delimited, case-sensitive strings."</i>
     * However, Facebook does not comply with this specification
     * and uses a comma (0x2C) as the delimiter. Therefore, this
     * method returns a <b>comma</b>-delimited scope list if this
     * {@code AccessToken} instance was built from a response from
     * Facebook.
     * </p>
     *
     * @return
     *         The space-delimited scope list.
     */
    public String getScope()
    {
        return scope;
    }


    /**
     * Set the space-delimited scope list (= the value of {@code scope}
     * parameter (OPTIONAL parameter)).
     *
     * @param scope
     *         The space-delimited scope list.
     *
     * @return
     *         {@code this} object.
     */
    public AccessToken setScope(String scope)
    {
        this.scope = scope;

        return this;
    }
}
