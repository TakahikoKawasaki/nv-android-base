/*
 * Copyright (C) 2011-2012 Neo Visionaries Inc.
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


import com.neovisionaries.android.util.TypedProperties;
import android.content.Context;
import android.content.SharedPreferences;


public class Preferences extends TypedProperties
{
    private final SharedPreferences prefs;


    public Preferences(Context context, String name)
    {
        this(context, name, Context.MODE_PRIVATE);
    }


    public Preferences(Context context, String name, int mode)
    {
        prefs = context.getSharedPreferences(name, mode);
    }


    @Override
    public boolean getBoolean(String key, boolean defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getBoolean(key, defaultValue);
    }


    @Override
    public float getFloat(String key, float defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getFloat(key, defaultValue);
    }


    @Override
    public int getInt(String key, int defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getInt(key, defaultValue);
    }


    @Override
    public long getLong(String key, long defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getLong(key, defaultValue);
    }


    @Override
    public String getString(String key, String defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return prefs.getString(key, defaultValue);
    }


    @Override
    public void setBoolean(String key, boolean value)
    {
        if (key != null)
        {
            prefs.edit().putBoolean(key, value).commit();
        }
    }


    @Override
    public void setFloat(String key, float value)
    {
        if (key != null)
        {
            prefs.edit().putFloat(key, value).commit();
        }
    }


    @Override
    public void setInt(String key, int value)
    {
        if (key != null)
        {
            prefs.edit().putInt(key, value).commit();
        }
    }


    @Override
    public void setLong(String key, long value)
    {
        if (key != null)
        {
            prefs.edit().putLong(key, value).commit();
        }
    }


    @Override
    public void setString(String key, String value)
    {
        if (key != null)
        {
            prefs.edit().putString(key, value).commit();
        }
    }


    @Override
    public void remove(String key)
    {
        if (key != null)
        {
            prefs.edit().remove(key).commit();
        }
    }


    @Override
    public void clear()
    {
        prefs.edit().clear().commit();
    }
}
