package com.neovisionaries.android.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class OAuth20View extends WebView
{
    private final OAuth20Settings mOAuth20Settings = new OAuth20Settings();


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

        // Set a WebViewClient.
        setWebViewClient(new LocalWebViewClient());
    }


    public OAuth20Settings getOAuth20Settings()
    {
        return mOAuth20Settings;
    }


    private class LocalWebViewClient extends WebViewClient
    {
        // TODO
    }
}
