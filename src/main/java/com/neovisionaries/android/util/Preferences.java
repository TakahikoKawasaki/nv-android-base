/*
 * Copyright (C) 2011-2013 Neo Visionaries Inc.
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
package com.neovisionaries.android.util;


import android.content.Context;
import android.content.SharedPreferences;
import com.neovisionaries.android.app.App;


/**
 * Wrapper over SharedPreferences.
 *
 * <p>
 * As {@code getXxx()} and {@code setXxx()} methods accept {@code
 * Enum<?>} as key as well as {@code String}, a code snippet like below
 * </p>
 *
 * <style type="text/css">
 * span.keyword { color: purple; font-weight: bold; }
 * span.stringliteral { color: blue; }
 * span.comment { color: green; }
 * </style>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * <span class="keyword">private static final</span> String KEY_AAA = <span class="stringliteral">"AAA"</span>;
 * <span class="keyword">private static final</span> String KEY_BBB = <span class="stringliteral">"BBB"</span>;
 *
 * SharedPreferences preferences = ...
 *
 * String aaa = preferences.getString(KEY_AAA, <span class="keyword">null</span>);
 * String bbb = preferences.getString(KEY_BBB, <span class="keyword">null</span>);
 * </pre>
 *
 * <p>
 * can be written like below.
 * </p>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * <span class="keyword">enum</span> Key
 * {
 *     AAA,
 *     BBB
 *     ;
 * }
 *
 * Preferences preferences = ...
 *
 * String aaa = preferences.getString(Key.AAA);
 * String bbb = preferences.getString(Key.BBB);
 * </pre>
 *
 * <p>
 * {@code setXxx(key, value)} methods are equivalent to {@code edit().putXxx(key, value).commit()}.
 * </p>
 *
 * <p>
 * {@link App}{@code .}{@link App#getInstance() getInstance()}{@code .}{@link App#getPreferences()
 * getPreferences()} returns a {@code Preferences} instance that is supposed to be
 * used as application-wide preferences.
 * </p>
 *
 * @author Takahiko Kawasaki
 */
public class Preferences extends TypedProperties
{
    private final SharedPreferences prefs;


    /**
     * Equivalent to {@link #Preferences(Context, String, int)
     * this}{@code (context, name, Context.MODE_PRIVATE)}.
     */
    public Preferences(Context context, String name)
    {
        this(context, name, Context.MODE_PRIVATE);
    }


    /**
     * Constructor with parameters to get SharedPreferences.
     *
     * @param context
     *
     * @param name
     *         Preference name. This argument is given as the
     *         first argument to
     *         {@code Context.getSharedPreferences(String, int)}.
     *
     * @param mode
     *         Operating mode. This argument is given as the
     *         second argument to
     *         {@code Context.getSharedPreferences(String, int)}.
     */
    public Preferences(Context context, String name, int mode)
    {
        this(context.getSharedPreferences(name, mode));
    }


    /**
     * Constructor with a wrapped {@link SharedPreferences}.
     *
     * @param prefs
     *         An {@link SharedPreferences} to be wrapped.
     *
     * @since 1.11
     */
    public Preferences(SharedPreferences prefs)
    {
        this.prefs = prefs;
    }


    /**
     * Equivalent to {@code contains(key)} on the internal
     * SharedPreferences instance.
     *
     * @since 1.6
     */
    @Override
    public boolean contains(String key)
    {
        return prefs.contains(key);
    }


    /**
     * Equivalent to {@code getBoolean(key, defaultValue)}
     * on the internal SharedPreferences instance.
     * If the given key is null, {@code defaultValue} is returned.
     */
    @Override
    public boolean getBoolean(String key, boolean defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getBoolean(key, defaultValue);
    }


    /**
     * Equivalent to {@code getFloat(key, defaultValue)}
     * on the internal SharedPreferences instance.
     * If the given key is null, {@code defaultValue} is returned.
     */
    @Override
    public float getFloat(String key, float defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getFloat(key, defaultValue);
    }


    /**
     * Equivalent to {@code getInt(key, defaultValue)}
     * on the internal SharedPreferences instance.
     * If the given key is null, {@code defaultValue} is returned.
     */
    @Override
    public int getInt(String key, int defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getInt(key, defaultValue);
    }


    /**
     * Equivalent to {@code getLong(key, defaultValue)}
     * on the internal SharedPreferences instance.
     * If the given key is null, {@code defaultValue} is returned.
     */
    @Override
    public long getLong(String key, long defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getLong(key, defaultValue);
    }


    /**
     * Equivalent to {@code getString(key, defaultValue)}
     * on the internal SharedPreferences instance.
     * If the given key is null, {@code defaultValue} is returned.
     */
    @Override
    public String getString(String key, String defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getString(key, defaultValue);
    }


    /**
     * Equivalent to {@code edit().putBoolean(key, value).commit()}
     * on the internal SharedPreferences instance.
     * If the given key is null, nothing is done.
     */
    @Override
    public void setBoolean(String key, boolean value)
    {
        if (key != null)
        {
            prefs.edit().putBoolean(key, value).commit();
        }
    }


    /**
     * Equivalent to {@code edit().putFloat(key, value).commit()}
     * on the internal SharedPreferences instance.
     * If the given key is null, nothing is done.
     */
    @Override
    public void setFloat(String key, float value)
    {
        if (key != null)
        {
            prefs.edit().putFloat(key, value).commit();
        }
    }


    /**
     * Equivalent to {@code edit().putInt(key, value).commit()}
     * on the internal SharedPreferences instance.
     * If the given key is null, nothing is done.
     */
    @Override
    public void setInt(String key, int value)
    {
        if (key != null)
        {
            prefs.edit().putInt(key, value).commit();
        }
    }


    /**
     * Equivalent to {@code edit().putLong(key, value).commit()}
     * on the internal SharedPreferences instance.
     * If the given key is null, nothing is done.
     */
    @Override
    public void setLong(String key, long value)
    {
        if (key != null)
        {
            prefs.edit().putLong(key, value).commit();
        }
    }


    /**
     * Equivalent to {@code edit().putString(key, value).commit()}
     * on the internal SharedPreferences instance.
     * If the given key is null, nothing is done.
     */
    @Override
    public void setString(String key, String value)
    {
        if (key != null)
        {
            prefs.edit().putString(key, value).commit();
        }
    }


    /**
     * Equivalent to {@code edit().remove(key).commit()}
     * on the internal SharedPreferences instance.
     * If the given key is null, nothing is done.
     */
    @Override
    public void remove(String key)
    {
        if (key != null)
        {
            prefs.edit().remove(key).commit();
        }
    }


    /**
     * Equivalent to {@code edit().clear().commit()}
     * on the internal SharedPreferences instance.
     */
    @Override
    public void clear()
    {
        prefs.edit().clear().commit();
    }
}
