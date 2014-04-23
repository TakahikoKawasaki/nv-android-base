package com.neovisionaries.android.oauth20;


import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;


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
