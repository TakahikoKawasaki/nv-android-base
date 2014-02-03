/*
 * Copyright (C) 2011-2014 Neo Visionaries Inc.
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
package com.neovisionaries.android.app;


import java.util.Locale;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.neovisionaries.android.util.Preferences;


/**
 * A class to manage global information of the application.
 * {@link #init(Context)} method must be called before use.
 *
 * @see BaseApplication
 *
 * @author Takahiko Kawasaki
 */
public class App
{
    /**
     * Singleton instance of this class. The static field is
     * set by {@link #init(Context)} method.
     */
    private static App instance;

    /**
     * The application context passed as the argument of
     * {@link #init(Context)} method.
     */
    private final Context context;

    /**
     * Application label.
     */
    private final String applicationLabel;

    /**
     * Version name.
     */
    private final String versionName;

    /**
     * Preferences.
     */
    private final Preferences preferences;

    /**
     * Flag to indicate whether the application is in the process
     * of termination.
     */
    private boolean stopping;

    /**
     * Flag to indicate whether the application is in the process
     * of restart.
     */
    private boolean restarting;


    /**
     * Constructor.
     */
    private App(Context context) throws NameNotFoundException
    {
        this.context = context;

        // Package Manager
        PackageManager pkgMgr = context.getPackageManager();

        // Package Name
        String pkgName = context.getPackageName();

        // Package Info
        PackageInfo pkgInfo = pkgMgr.getPackageInfo(pkgName, 0);

        // Application Info
        ApplicationInfo appInfo = pkgMgr.getApplicationInfo(pkgName, 0);

        // Application Label
        this.applicationLabel = (String)pkgMgr.getApplicationLabel(appInfo);

        // Version Name
        this.versionName = pkgInfo.versionName;

        // Preferences
        this.preferences = new Preferences(context, applicationLabel);

        // Workaround against Issue 9431.
        doWorkaroundAgainstIssue9431();
    }


    /**
     * Initialize this class. This static method must be called
     * before use of {@link #getInstance()} method. Otherwise,
     * {@code getInstance()} will throw {@code IllegalStateException}.
     *
     * <p>
     * It is recommended to call this static method in an implementation
     * of {@code onCreate()} method of a subclass of {@link
     * android.app.Application Application}.
     * In the case, the subclass should be specified as the value
     * of {@code android:name} attribute of {@code application} tag in
     * {@code AndroidManifest.xml}. The easiest way to do it is to use
     * {@link BaseApplication} class whose {@code onCreate()} method calls
     * {@code App.init(this)}.
     * </p>
     *
     * <p>
     * In particular, items below are performed.
     * </p>
     *
     * <ul>
     * <li>Set &quot;{@code java.net.preferIPv4Stack}&quot; to true
     *     if the system property is not set, and set
     *     &quot;{@code java.net.preferIPv6Addresses}&quot; to false
     *     if the system property is not set. These are done as a
     *     workaround against
     *     <a href="http://code.google.com/p/android/issues/detail?id=9431"
     *     >Issue 9431</a>. Without this workaround,
     *     &quot;{@code java.net.SocketException: Bad address family}&quot;
     *     would be thrown on a device which does not support IPv6.
     * </ul>
     *
     * @param context
     *         The application context.
     *
     * @return
     *         A newly created instance of {@code App} that has been initialized
     *         with the given context. {@link #getInstance()} will return
     *         the same instance.
     *
     * @throws IllegalArgumentException
     *         The argument is null.
     */
    public static App init(Context context)
    {
        if (context == null)
        {
            // The argument is null. The application context is
            // necessary to initialize this class.
            throw new IllegalArgumentException("Context is null");
        }

        try
        {
            instance = new App(context);
        }
        catch (NameNotFoundException e)
        {
            // This won't happen.
        }

        return instance;
    }


    /**
     * Get the singleton instance of this class. {@link #init(Context)}
     * method must be called before use of this method. Otherwise,
     * {@code IllegalStateException} is thrown.
     *
     * @return
     *         The singleton instance of this class.
     */
    public static App getInstance()
    {
        if (instance == null)
        {
            // init() method must be called before use of getInstance().
            throw new IllegalStateException("Not initialized");
        }

        return instance;
    }


    /**
     * Set some system properties as a workaround against
     * <a href="http://code.google.com/p/android/issues/detail?id=9431"
     * >Issue 9431</a>.
     */
    private void doWorkaroundAgainstIssue9431()
    {
        setSystemPropertyIfNotSet("java.net.preferIPv4Stack", "true");
        setSystemPropertyIfNotSet("java.net.preferIPv6Addresses", "false");
    }


    private void setSystemPropertyIfNotSet(String key, String value)
    {
        // Get the current value of the system property.
        String currentValue = System.getProperty(key);

        // If already set.
        if (currentValue != null)
        {
            // Not overwrite.
            return;
        }

        System.setProperty(key, value);
    }


    /**
     * Get the application context.
     *
     * @return
     *         The object passed to {@link #init(Context)} is returned.
     */
    public Context getContext()
    {
        return context;
    }


    /**
     * Get the application label of this application.
     *
     * @return
     *         The value returned from {@link
     *         android.content.pm.PackageManager#getApplicationLabel(ApplicationInfo)
     *         PackageManager.getApplicationLabel()}</a>.
     */
    public String getApplicationLabel()
    {
        return applicationLabel;
    }


    /**
     * Get the version name of this application.
     *
     * @return
     *         The value of {@link
     *         android.content.pm.PackageInfo#versionName PackageInfo.versionName}.
     */
    public String getVersionName()
    {
        return versionName;
    }


    /**
     * Get the {@link Preferences} instance that is supposed to be used
     * globally for this application.
     *
     * @return
     *         A {@link Preferences} instance which holds a private ({@link
     *         android.content.Context#MODE_PRIVATE MODE_PRIVATE})
     *         {@link android.content.SharedPreferences SharedPreferences}
     *         instance which has been initialized with the application
     *         context passed to {@link #init(Context)} method and the
     *         application name as the SharedPreferences instance's name.
     */
    public Preferences getPreferences()
    {
        return preferences;
    }


    /**
     * Get the current locale.
     */
    public Locale getLocale()
    {
        return context.getResources().getConfiguration().locale;
    }


    /**
     * Get the current language.
     *
     * @return An alpha-2 code defined ISO-639.
     * @see <a href="http://www.loc.gov/standards/iso639-2/php/code_list.php"
     *      >ISO 639 Language Code List</a>
     */
    public String getLanguage()
    {
        // Return an alpha-2 code defined in ISO-639.
        // http://www.loc.gov/standards/iso639-2/php/code_list.php
        return getLocale().getLanguage();
    }


    /**
     * Get the current country.
     *
     * @return An uppercase ISO 3166 2-letter code.
     * @see <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     *      >ISO 3166-1 alpha-2 Country Code List</a>
     */
    public String getCountry()
    {
        // An uppercase ISO 3166 2-letter code.
        return getLocale().getCountry();
    }


    /**
     * Check whether this application is in the process of termination.
     */
    public boolean isStopping()
    {
        return stopping;
    }


    /**
     * Set the termination state of this application.
     */
    public void setStopping(boolean stopping)
    {
        this.stopping = stopping;
    }


    /**
     * Check whether this application is in the process of restart.
     *
     * @since 1.7
     */
    public boolean isRestarting()
    {
        return restarting;
    }


    /**
     * Set the restart state of this application.
     *
     * @since 1.7
     */
    public void setRestarting(boolean restarting)
    {
        this.restarting = restarting;
    }
}
