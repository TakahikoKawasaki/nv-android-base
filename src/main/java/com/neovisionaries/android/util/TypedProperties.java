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


public abstract class TypedProperties
{
    protected TypedProperties()
    {
    }


    public abstract boolean getBoolean(String key, boolean defaultValue);


    public boolean get(String key, boolean defaultValue)
    {
        return getBoolean(key, defaultValue);
    }


    public boolean getBoolean(String key)
    {
        return getBoolean(key, false);
    }


    public boolean getBoolean(Enum<?> key, boolean defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return getBoolean(key.name(), defaultValue);
    }


    public boolean get(Enum<?> key, boolean defaultValue)
    {
        return getBoolean(key, defaultValue);
    }


    public boolean getBoolean(Enum<?> key)
    {
        if (key == null)
        {
            return false;
        }

        return getBoolean(key.name());
    }


    public abstract float getFloat(String key, float defaultValue);


    public float get(String key, float defaultValue)
    {
        return getFloat(key, defaultValue);
    }


    public float getFloat(String key)
    {
        return getFloat(key, 0.0F);
    }


    public float getFloat(Enum<?> key, float defaultValue)
    {
        if (key == null)
        {
            return defaultValue;
        }

        return getFloat(key.name(), defaultValue);
    }


    public float get(Enum<?> key, float defaultValue)
    {
        return getFloat(key, defaultValue);
    }


    public float getFloat(Enum<?> key)
    {
        if (key == null)
        {
            return 0.0F;
        }

        return getFloat(key.name());
    }


    public abstract int getInt(String key, int defaultValue);


    public int get(String key, int defaultValue)
    {
        return getInt(key, defaultValue);
    }


    public int getInt(String key)
    {
        return getInt(key, 0);
    }


    public int getInt(Enum<?> key, int defaultValue)
    {
        if (key == null)
        {
            return 0;
        }

        return getInt(key.name(), defaultValue);
    }


    public int get(Enum<?> key, int defaultValue)
    {
        return getInt(key, defaultValue);
    }


    public int getInt(Enum<?> key)
    {
        if (key == null)
        {
            return 0;
        }

        return getInt(key.name());
    }


    public abstract long getLong(String key, long defaultValue);


    public long get(String key, long defaultValue)
    {
        return getLong(key, defaultValue);
    }


    public long getLong(String key)
    {
        return getLong(key, 0L);
    }


    public long getLong(Enum<?> key, long defaultValue)
    {
        if (key == null)
        {
            return 0L;
        }

        return getLong(key.name(), defaultValue);
    }


    public long get(Enum<?> key, long defaultValue)
    {
        return getLong(key, defaultValue);
    }


    public long getLong(Enum<?> key)
    {
        if (key == null)
        {
            return 0L;
        }

        return getLong(key.name());
    }


    public abstract String getString(String key, String defaultValue);


    public String get(String key, String defaultValue)
    {
        return getString(key, defaultValue);
    }


    public String getString(String key)
    {
        return getString(key, null);
    }


    public String get(String key)
    {
        return getString(key);
    }


    public String getString(Enum<?> key, String defaultValue)
    {
        if (key == null)
        {
            return null;
        }

        return getString(key.name(), defaultValue);
    }


    public String get(Enum<?> key, String defaultValue)
    {
        return getString(key, defaultValue);
    }


    public String getString(Enum<?> key)
    {
        if (key == null)
        {
            return null;
        }

        return getString(key.name());
    }


    public String get(Enum<?> key)
    {
        return getString(key);
    }


    public abstract void setBoolean(String key, boolean value);


    public void set(String key, boolean value)
    {
        setBoolean(key, value);
    }


    public void setBoolean(Enum<?> key, boolean value)
    {
        if (key == null)
        {
            return;
        }

        setBoolean(key.name(), value);
    }


    public void set(Enum<?> key, boolean value)
    {
        setBoolean(key, value);
    }


    public abstract void setFloat(String key, float value);


    public void set(String key, float value)
    {
        setFloat(key, value);
    }


    public void setFloat(Enum<?> key, float value)
    {
        if (key == null)
        {
            return;
        }

        setFloat(key.name(), value);
    }


    public void set(Enum<?> key, float value)
    {
        setFloat(key, value);
    }


    public abstract void setInt(String key, int value);


    public void set(String key, int value)
    {
        setInt(key, value);
    }


    public void setInt(Enum<?> key, int value)
    {
        if (key == null)
        {
            return;
        }

        setInt(key.name(), value);
    }


    public void set(Enum<?> key, int value)
    {
        setInt(key, value);
    }


    public abstract void setLong(String key, long value);


    public void set(String key, long value)
    {
        setLong(key, value);
    }


    public void setLong(Enum<?> key, long value)
    {
        if (key == null)
        {
            return;
        }

        setLong(key.name(), value);
    }


    public void set(Enum<?> key, long value)
    {
        setLong(key, value);
    }


    public abstract void setString(String key, String value);


    public void set(String key, String value)
    {
        setString(key, value);
    }


    public void setString(Enum<?> key, String value)
    {
        setString(key.name(), value);
    }


    public void set(Enum<?> key, String value)
    {
        setString(key, value);
    }


    public abstract void remove(String key);


    public void remove(Enum<?> key)
    {
        if (key == null)
        {
            return;
        }

        remove(key.name());
    }


    public abstract void clear();
}
