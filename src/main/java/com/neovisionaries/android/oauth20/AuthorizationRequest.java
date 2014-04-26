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


import static com.neovisionaries.android.util.ParcelUtils.readChar;
import static com.neovisionaries.android.util.ParcelUtils.readSerializableWithPresenceFlag;
import static com.neovisionaries.android.util.ParcelUtils.readStringWithPresenceFlag;
import static com.neovisionaries.android.util.ParcelUtils.writeChar;
import static com.neovisionaries.android.util.ParcelUtils.writeSerializableWithPresenceFlag;
import static com.neovisionaries.android.util.ParcelUtils.writeStringWithPresenceFlag;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * OAuth 2&#x002E;0 authorization request.
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1"
 *      >RFC 6749 (OAuth 2.0), 3.1. Authorization Endpoint</a>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.1.1"
 *      >RFC 6749 (OAuth 2.0), 4.1.1. Authorization Request</a>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.2.1"
 *      >RFC 6749 (OAuth 2.0), 4.2.1. Authorization Request</a>
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
public class AuthorizationRequest implements Parcelable
{
    private URL mEndpoint;
    private ResponseType mResponseType;
    private String mClientId;
    private URL mRedirectUri;
    private Set<String> mScopeSet;
    private String mState;
    private Map<String, String> mExtraParameters;
    private char mScopeDelimiter = ' ';


    /**
     * The default constructor.
     */
    public AuthorizationRequest()
    {
    }


    private AuthorizationRequest(Parcel in)
    {
        mEndpoint        = (URL)readSerializableWithPresenceFlag(in);
        mResponseType    = (ResponseType)readSerializableWithPresenceFlag(in);
        mClientId        = readStringWithPresenceFlag(in);
        mRedirectUri     = (URL)readSerializableWithPresenceFlag(in);
        mScopeSet        = readScopeSet(in);
        mState           = readStringWithPresenceFlag(in);
        mExtraParameters = readExtraParameters(in);
        mScopeDelimiter  = readChar(in);
    }


    /**
     * Get the authorization endpoint.
     *
     * @return
     *         The authorization endpoint.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1"
     *      >RFC 6749 (OAuth 2.0), 3.1. Authorization Endpoint</a>
     */
    public URL getEndpoint()
    {
        return mEndpoint;
    }


    /**
     * Set the authorization endpoint.
     *
     * @param endpoint
     *         The authorization endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The endpoint includes a fragment component.
     *         It is prohibited by the specification.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1"
     *      >RFC 6749 (OAuth 2.0), 3.1. Authorization Endpoint</a>
     */
    public AuthorizationRequest setEndpoint(URL endpoint)
    {
        if (endpoint != null)
        {
            validateEndpoint(endpoint);
        }

        mEndpoint = endpoint;

        return this;
    }


    /**
     * Set the authorization endpoint.
     *
     * @param endpoint
     *         The authorization endpoint.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The given endpoint is not a valid URL, or
     *         it includes a fragment component.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1"
     *      >RFC 6749 (OAuth 2.0), 3.1. Authorization Endpoint</a>
     */
    public AuthorizationRequest setEndpoint(String endpoint)
    {
        URL url = null;

        if (endpoint != null)
        {
            try
            {
                url = new URL(endpoint);
            }
            catch (MalformedURLException e)
            {
                // The endpoint is malformed.
                throw new IllegalArgumentException(e);
            }
        }

        return setEndpoint(url);
    }


    /**
     * Get the response type (= the value of {@code response_type}
     * parameter (REQUIRED parameter)).
     *
     * @return
     *         The response type.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.1"
     *      >RFC 6749 (OAuth 2.0), 3.1.1. Response Type</a>
     */
    public ResponseType getResponseType()
    {
        return mResponseType;
    }


    /**
     * Set the response type (= the value of {@code response_type}
     * parameter (REQUIRED parameter)).
     *
     * @param responseType
     *         The response type.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.1"
     *      >RFC 6749 (OAuth 2.0), 3.1.1. Response Type</a>
     */
    public AuthorizationRequest setResponseType(ResponseType responseType)
    {
        mResponseType = responseType;

        return this;
    }


    /**
     * Get the client ID (= the value of {@code client_id} parameter
     * (REQUIRED parameter)).
     *
     * @return
     *         The client ID.
     */
    public String getClientId()
    {
        return mClientId;
    }


    /**
     * Set the client ID (= the value of {@code client_id} parameter
     * (REQUIRED parameter)).
     *
     * @param clientId
     *         The client ID.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationRequest setClientId(String clientId)
    {
        mClientId = clientId;

        return this;
    }


    /**
     * Get the redirect URI (= the value of {@code redirect_uri} parameter
     * (OPTIONAL parameter)).
     *
     * @return
     *         The redirect URI.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     *      >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a>
     */
    public URL getRedirectUri()
    {
        return mRedirectUri;
    }


    /**
     * Set the redirect URI (= the value of {@code redirect_uri} parameter
     * (OPTIONAL parameter)).
     *
     * <p>
     * <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     * >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a> says
     * <i>"The redirection endpoint URI MUST be an absolute URI", so
     * this method accepts the parameter value as {@link URL}.
     * </p>
     *
     * @param redirectUri
     *         The redirect URI.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The redirect URI failed to be converted into a {@link URL},
     *         or it includes a fragment component.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     *      >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a>
     */
    public AuthorizationRequest setRedirectUri(URL redirectUri)
    {
        if (redirectUri != null)
        {
            validateRedirectUri(redirectUri);
        }

        mRedirectUri = redirectUri;

        return this;
    }


    /**
     * Set the redirect URI (= the value of {@code redirect_uri} parameter
     * (OPTIONAL parameter)).
     *
     * <p>
     * <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     * >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a> says
     * <i>"The redirection endpoint URI MUST be an absolute URI".
     * </p>
     *
     * @param redirectUri
     *         The redirect URI.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The redirect URI includes a fragment component.
     *         It is prohibited by the specification.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.2"
     *      >RFC 6749 (OAuth 2.0), 3.1.2. Redirection Endpoint</a>
     */
    public AuthorizationRequest setRedirectUri(String redirectUri)
    {
        URL url = null;

        if (redirectUri != null)
        {
            try
            {
                url = new URL(redirectUri);
            }
            catch (MalformedURLException e)
            {
                // The redirect URI is malformed.
                throw new IllegalArgumentException(e);
            }
        }

        return setRedirectUri(url);
    }


    /**
     * Get the scopes, which are elements in the value of
     * {@code scope} parameter (OPTIONAL parameter).
     *
     * @return
     *         The scopes.
     */
    public Set<String> getScopeSet()
    {
        return mScopeSet;
    }


    /**
     * Set the scopes, which are elements in the value of
     * {@code scope} parameter (OPTIONAL parameter).
     *
     * <p>
     * Scopes must comply with the specification shown below.
     * Otherwise, {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * <pre>
     * scope-token = 1*( %x21 / %x23-5B / %x5D-7E )
     * </pre>
     *
     * <p>
     * {@code null} and an empty value do not raise
     * {@code IllegalArgumentException} and are just ignored.
     * </p>
     *
     * @param scopeSet
     *         Scopes to be set.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         There is a scope which does not comply with the
     *         specification.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      >RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
     */
    public AuthorizationRequest setScopeSet(Set<String> scopeSet)
    {
        mScopeSet = scopeSet;

        if (scopeSet == null)
        {
            return this;
        }

        // Check whether all the scopes comply with the specification.
        for (String scope : scopeSet)
        {
            if (scope == null || scope.length() == 0)
            {
                continue;
            }

            validateScope(scope);
        }

        return this;
    }


    /**
     * Add scopes, which are elements in the value of
     * {@code scope} parameter (OPTIONAL parameter).
     *
     * <p>
     * Scopes must comply with the specification shown below.
     * Otherwise, {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * <pre>
     * scope-token = 1*( %x21 / %x23-5B / %x5D-7E )
     * </pre>
     *
     * <p>
     * {@code null} and an empty value do not raise
     * {@code IllegalArgumentException} and are just ignored.
     * </p>
     *
     * @param scopes
     *         Scopes to be added.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         There is a scope which does not comply with the
     *         specification.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      >RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
     */
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


    /**
     * Remove scopes, which are elements in the value of
     * {@code scope} parameter (OPTIONAL parameter).
     *
     * @param scopes
     *         Scopes to be removed.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      >RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
     */
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

        if (mScopeSet.size() == 0)
        {
            mScopeSet = null;
        }

        return this;
    }


    /**
     * Get the state (= the value of {@code state} parameter
     * (RECOMMENDED parameter)).
     *
     * @return
     *         The value of {@code state} parameter.
     */
    public String getState()
    {
        return mState;
    }


    /**
     * Set the state (= the value of {@code state} parameter
     * (RECOMMENDED parameter)).
     *
     * @param state
     *         The value of {@code state} parameter.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationRequest setState(String state)
    {
        mState = state;

        return this;
    }


    /**
     * Get extra parameters added to the authorization request.
     *
     * @return
     *         Extra parameters.
     */
    public Map<String, String> getExtraParameters()
    {
        return mExtraParameters;
    }


    /**
     * Set extra parameters added to the authorization request.
     *
     * @param parameters
     *         Extra parameters.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationRequest setExtraParameters(Map<String, String> parameters)
    {
        mExtraParameters = parameters;

        return this;
    }


    /**
     * Add an extra parameter to the authorization request.
     * For example, ({@code "display"}, {@code "touch"}) for Facebook.
     *
     * @param name
     *         Parameter name.
     *
     * @param value
     *         Parameter value.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationRequest addParameter(String name, String value)
    {
        if (name == null || name.length() == 0)
        {
            return this;
        }

        if (mExtraParameters == null)
        {
            mExtraParameters = new HashMap<String, String>();
        }

        mExtraParameters.put(name, value);

        return this;
    }


    /**
     * Remove an extra parameter from the authorization request.
     *
     * @param name
     *         Parameter name.
     *
     * @return
     *         {@code this} object.
     */
    public AuthorizationRequest removeParameter(String name)
    {
        if (mExtraParameters == null)
        {
            return this;
        }

        if (name == null)
        {
            return this;
        }

        mExtraParameters.remove(name);

        if (mExtraParameters.size() == 0)
        {
            mExtraParameters = null;
        }

        return this;
    }


    /**
     * Get the delimiter for scopes.
     * The default value is a space (0x20).
     *
     * @return
     *         The scope delimiter.
     */
    public char getScopeDelimiter()
    {
        return mScopeDelimiter;
    }


    /**
     * Set the delimiter for scopes.
     *
     * <p>
     * <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     * >RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a> says <i>
     * "The value of the scope parameter is expressed as a list of
     * <b>space</b>-delimited, case-sensitive strings."</i>
     * However, Facebook does not comply with this specification
     * and uses a comma (0x2C) as the delimiter. Therefore, for
     * Facebook, {@code setScopeDelimiter(',')} needs to be called.
     * </p>
     *
     * @param delimiter
     *         The scope
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.3"
     *      >RFC 6749 (OAuth 2.0), 3.3. Access Token Scope</a>
     */
    public AuthorizationRequest setScopeDelimiter(char delimiter)
    {
        mScopeDelimiter = delimiter;

        return this;
    }


    private void validateEndpoint(URL endpoint)
    {
        if (endpoint.getRef() != null)
        {
            throw new IllegalArgumentException("Endpoint must not include a fragment component.");
        }
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


    /**
     * Convert this authorization request to a URL.
     *
     * @return
     *         The URL that represents this authorization request.
     *
     * @throws IllegalStateException
     *         <ul>
     *         <li>Endpoint is not set.
     *         <li>Response type is not set.
     *         <li>Client ID is not set.
     *         </ul>
     */
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
            queryBuilder.append("&scope=").append(urlEncode(scope));
        }

        // state (RECOMMENDED)
        if (mState != null)
        {
            queryBuilder.append("&state=").append(urlEncode(mState));
        }

        // Extra parameters.
        if (mExtraParameters != null)
        {
            appendExtraParameters(queryBuilder);
        }

        return queryBuilder.toString();
    }


    private String buildScope()
    {
        StringBuilder scopeBuilder = new StringBuilder();

        for (String scope : mScopeSet)
        {
            scopeBuilder.append(scope).append(mScopeDelimiter);
        }

        if (scopeBuilder.length() != 0)
        {
            // Remove the space at the end.
            scopeBuilder.setLength(scopeBuilder.length() - 1);
        }

        return scopeBuilder.toString();
    }


    private void appendExtraParameters(StringBuilder builder)
    {
        for (Map.Entry<String, String> entry : mExtraParameters.entrySet())
        {
            String name = entry.getKey();

            if (name == null || name.length() == 0)
            {
                continue;
            }

            String value = entry.getValue();

            if (value == null)
            {
                value = "";
            }

            builder
                .append("&")
                .append(urlEncode(name))
                .append("=")
                .append(urlEncode(value));
        }
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


    public int describeContents()
    {
        return 0;
    }


    public void writeToParcel(Parcel out, int flags)
    {
        writeSerializableWithPresenceFlag(out, mEndpoint);
        writeSerializableWithPresenceFlag(out, mResponseType);
        writeStringWithPresenceFlag(out, mClientId);
        writeSerializableWithPresenceFlag(out, mRedirectUri);
        writeScopeSet(out, mScopeSet);
        writeStringWithPresenceFlag(out, mState);
        writeExtraParameters(out, mExtraParameters);
        writeChar(out, mScopeDelimiter);
    }


    private Set<String> readScopeSet(Parcel in)
    {
        // Read the size.
        int size = in.readInt();

        if (size == 0)
        {
            return null;
        }

        Set<String> set = new HashSet<String>(size);

        for (int i = 0; i < size; ++i)
        {
            set.add(in.readString());
        }

        return set;
    }


    private void writeScopeSet(Parcel out, Set<String> set)
    {
        if (set == null)
        {
            // Not present (size = 0).
            out.writeInt(0);
            return;
        }

        int size = set.size();

        // Write the size.
        out.writeInt(size);

        for (String value : set)
        {
            out.writeString(value);
        }
    }


    private Map<String, String> readExtraParameters(Parcel in)
    {
        // Read the size.
        int size = in.readInt();

        if (size == 0)
        {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>(size);

        for (int i = 0; i < size; ++i)
        {
            String key   = in.readString();
            String value = in.readString();

            map.put(key, value);
        }

        return map;
    }


    private void writeExtraParameters(Parcel out, Map<String, String> map)
    {
        if (map == null)
        {
            // Not present (size = 0).
            out.writeInt(0);
            return;
        }

        int size = map.size();

        // Write the size.
        out.writeInt(size);

        for (Map.Entry<String, String> entry : map.entrySet())
        {
            out.writeString(entry.getKey());
            out.writeString(entry.getValue());
        }
    }


    /**
     * CREATER required by {@link Parcelable} interface.
     */
    public static final Parcelable.Creator<AuthorizationRequest> CREATOR
        = new Parcelable.Creator<AuthorizationRequest>()
    {
        public AuthorizationRequest createFromParcel(Parcel in)
        {
            return new AuthorizationRequest(in);
        }


        public AuthorizationRequest[] newArray(int size)
        {
            return new AuthorizationRequest[size];
        }
    };
}
