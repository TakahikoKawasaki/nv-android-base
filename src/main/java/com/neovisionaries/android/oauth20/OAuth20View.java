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


import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A {@link WebView} subclass for OAuth 2&#x002E;0 authorization endpoint.
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
public class OAuth20View extends WebView
{
    /**
     * A constructor that calls {@link WebView#WebView(Context, AttributeSet, int)
     * super(context, attrs, defStyle)}.
     */
    public OAuth20View(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        // Additional initialization.
        init();
    }


    /**
     * A constructor that calls {@link WebView#WebView(Context, AttributeSet)
     * super(context, attrs)}.
     */
    public OAuth20View(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Additional initialization.
        init();
    }


    /**
     * A constructor that calls {@link WebView#WebView(Context) super(context)}.
     */
    public OAuth20View(Context context)
    {
        super(context);

        // Additional initialization.
        init();
    }


    /**
     * Initialization common for all constructors.
     */
    private void init()
    {
        WebSettings settings = getSettings();

        // Not use cache.
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // Enable JavaScript.
        settings.setJavaScriptEnabled(true);

        // Enable zoom control.
        settings.setBuiltInZoomControls(true);

        // Scroll bar
        setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
    }


    /**
     * Access to OAuth 2&#x002E;0 authorization endpoint.
     *
     * @param request
     *         The authorization request.
     *
     * @param listener
     *         A listener to receive the result of the authorization request.
     *         One of the listener's methods is called in normal cases, but
     *         none of them is not called when the authorization server did
     *         not made other response than redirects (3xx).
     */
    public void load(AuthorizationRequest request, OAuth20ViewListener listener)
    {
        if (request == null)
        {
            throw new IllegalArgumentException("request is null.");
        }

        if (listener == null)
        {
            throw new IllegalArgumentException("listener is null.");
        }

        // Convert the request to a URL. toURL() validates the content
        // of the request. IllegalStateException is thrown when invalid.
        String url = request.toURL().toString();

        // Set a WebViewClient that handles a response from OAuth 2.0
        // authorization endpoint.
        setWebViewClient(new OAuth20ViewClient(this, request, listener));

        // Send the request to OAuth 2.0 authorization endpoint.
        super.loadUrl(url);
    }
}
