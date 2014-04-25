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


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A {@link WebViewClient} for {@link OAuth20View}.
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
class OAuth20ViewClient extends WebViewClient
{
    private final OAuth20View mOAuth20View;
    private final AuthorizationRequest mRequest;
    private final ResponseType mResponseType;
    private final String mRedirectUri;
    private final OAuth20ViewListener mListener;


    OAuth20ViewClient(OAuth20View view, AuthorizationRequest request, OAuth20ViewListener listener)
    {
        mOAuth20View  = view;
        mRequest      = request;
        mResponseType = request.getResponseType();
        mRedirectUri  = (request.getRedirectUri() == null) ? null
                      : request.getRedirectUri().toString();
        mListener     = listener;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        process(url);

        return super.shouldOverrideUrlLoading(view, url);
    }


    private void process(String url)
    {
        // If a redirect URI has been specified explicitly and
        // the URL does not match the redirect URI.
        if (mRedirectUri != null && url.startsWith(mRedirectUri) == false)
        {
            // The URL does not contain a response from the
            // authorization endpoint.
            return;
        }

        URL target;

        try
        {
            // Convert String to URL.
            target = new URL(url);
        }
        catch (MalformedURLException e)
        {
            // This should not happen.
            return;
        }

        if (mResponseType == ResponseType.code)
        {
            // A response from the authorization endpoint includes
            // parameters in the query component.
            processCode(target.getQuery());
        }
        else
        {
            // A response from the authorization endpoint includes
            // parameters in the fragment component.
            processToken(target.getRef());
        }
    }


    private void processCode(String query)
    {
        if (query == null)
        {
            return;
        }

        Map<String, List<String>> parameters = parseParameters(query);

        if (parameters.containsKey("code"))
        {
            fireCodeIssued(parameters);
        }
        else if (parameters.containsKey("error"))
        {
            fireError(parameters);
        }
    }


    private void processToken(String fragment)
    {
        if (fragment == null)
        {
            return;
        }

        Map<String, List<String>> parameters = parseParameters(fragment);

        if (parameters.containsKey("access_token"))
        {
            fireTokenIssued(parameters);
        }
        else if (parameters.containsKey("error"))
        {
            fireError(parameters);
        }
    }


    private Map<String, List<String>> parseParameters(String input)
    {
        Map<String, List<String>> parameters = new HashMap<String, List<String>>();

        for (String parameter : input.split("&"))
        {
            String[] pair = parameter.split("=", 2);

            if (pair == null || pair.length == 0 || pair[0].length() == 0)
            {
                continue;
            }

            String key   = urlDecode(pair[0]);
            String value = (pair.length == 2) ? urlDecode(pair[1]) : "";

            List<String> list = parameters.get(key);

            if (list == null)
            {
                list = new ArrayList<String>();
                parameters.put(key, list);
            }

            list.add(value);
        }

        return parameters;
    }


    private String urlDecode(String input)
    {
        try
        {
            return URLDecoder.decode(input, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This never happens.
            return input;
        }
    }


    private String getParameter(Map<String, List<String>> parameters, String name)
    {
        List<String> list = parameters.get(name);

        if (list == null || list.size() == 0)
        {
            return null;
        }

        return list.get(0);
    }


    private int getParameterAsInt(Map<String, List<String>> parameters, String name)
    {
        String value = getParameter(parameters, name);

        if (value == null)
        {
            return 0;
        }

        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }


    private OAuth20Error getParameterError(Map<String, List<String>> parameters)
    {
        String value = getParameter(parameters, "error");

        if (value == null)
        {
            return null;
        }

        try
        {
            // Convert String to OAuth20Error.
            return OAuth20Error.valueOf(value);
        }
        catch (Exception e)
        {
            // Unknown error. The OAuth 2.0 authorization endpoint
            // does not comply with the specification.
            return null;
        }
    }


    private void fireCodeIssued(Map<String, List<String>> parameters)
    {
        String code  = getParameter(parameters, "code");
        String state = getParameter(parameters, "state");

        try
        {
            mListener.onCodeIssued(mOAuth20View, mRequest, code, state);
        }
        catch (Throwable t)
        {
            // Ignore.
        }
    }


    private void fireTokenIssued(Map<String, List<String>> parameters)
    {
        String accessToken = getParameter(parameters, "access_token");
        String tokenType   = getParameter(parameters, "token_type");
        int expiresIn      = getParameterAsInt(parameters, "expires_in");
        String scope       = getParameter(parameters, "scope");
        String state       = getParameter(parameters, "state");

        AccessToken ac = new AccessToken()
            .setAccessToken(accessToken)
            .setTokenType(tokenType)
            .setExpiresIn(expiresIn)
            .setScope(scope)
            ;

        try
        {
            mListener.onTokenIssued(mOAuth20View, mRequest, ac, state);
        }
        catch (Throwable t)
        {
            // Ignore.
        }
    }


    private void fireError(Map<String, List<String>> parameters)
    {
        OAuth20Error error = getParameterError(parameters);
        String description = getParameter(parameters, "error_description");
        String uri         = getParameter(parameters, "error_uri");
        String state       = getParameter(parameters, "state");

        try
        {
            mListener.onError(mOAuth20View, mRequest, error, description, uri, state);
        }
        catch (Throwable t)
        {
            // Ignore.
        }
    }
}
