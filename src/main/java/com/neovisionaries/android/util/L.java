/*
 * Copyright (C) 2012 Neo Visionaries Inc.
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


import android.util.Log;


/**
 * Logging utility.
 *
 * @author Takahiko Kawasaki
 */
public final class L
{
    private static String toTag(Object object)
    {
        // If CharBuffer, String, StringBuffer, StringBuilder or
        // any other object that implements CharSequence interface.
        if (object instanceof CharSequence)
        {
            return object.toString();
        }
        else if (object instanceof Class<?>)
        {
            return ((Class<?>)object).getSimpleName();
        }
        else
        {
            return object.getClass().getSimpleName();
        }
    }


    public static int d(String tag, String msg)
    {
        return Log.d(tag, msg);
    }


    public static int d(String tag, String msg, Throwable throwable)
    {
        return Log.d(tag, msg, throwable);
    }


    public static int d(Object tag, String msg)
    {
        return d(toTag(tag), msg);
    }


    public static int d(Object tag, String msg, Throwable throwable)
    {
        return d(toTag(tag), msg, throwable);
    }


    public static int dformat(Object tag, String format, Object... args)
    {
        return d(toTag(tag), String.format(format, args));
    }


    public static int dformat(Object tag, Throwable throwable, String format, Object... args)
    {
        return d(toTag(tag), String.format(format, args), throwable);
    }


    public static int e(String tag, String msg)
    {
        return Log.e(tag, msg);
    }


    public static int e(String tag, String msg, Throwable throwable)
    {
        return Log.e(tag, msg, throwable);
    }


    public static int e(Object tag, String msg)
    {
        return e(toTag(tag), msg);
    }


    public static int e(Object tag, String msg, Throwable throwable)
    {
        return e(toTag(tag), msg, throwable);
    }


    public static int eformat(Object tag, String format, Object... args)
    {
        return e(toTag(tag), String.format(format, args));
    }


    public static int eformat(Object tag, Throwable throwable, String format, Object... args)
    {
        return e(toTag(tag), String.format(format, args), throwable);
    }


    public static int i(String tag, String msg)
    {
        return Log.i(tag, msg);
    }


    public static int i(String tag, String msg, Throwable throwable)
    {
        return Log.i(tag, msg, throwable);
    }


    public static int i(Object tag, String msg)
    {
        return i(toTag(tag), msg);
    }


    public static int i(Object tag, String msg, Throwable throwable)
    {
        return i(toTag(tag), msg, throwable);
    }


    public static int iformat(Object tag, String format, Object... args)
    {
        return i(toTag(tag), String.format(format, args));
    }


    public static int iformat(Object tag, Throwable throwable, String format, Object... args)
    {
        return i(toTag(tag), String.format(format, args), throwable);
    }


    public static int v(String tag, String msg)
    {
        return Log.v(tag, msg);
    }


    public static int v(String tag, String msg, Throwable throwable)
    {
        return Log.v(tag, msg, throwable);
    }


    public static int v(Object tag, String msg)
    {
        return v(toTag(tag), msg);
    }


    public static int v(Object tag, String msg, Throwable throwable)
    {
        return v(toTag(tag), msg, throwable);
    }


    public static int vformat(Object tag, String format, Object... args)
    {
        return v(toTag(tag), String.format(format, args));
    }


    public static int vformat(Object tag, Throwable throwable, String format, Object... args)
    {
        return v(toTag(tag), String.format(format, args), throwable);
    }


    public static int w(String tag, String msg)
    {
        return Log.w(tag, msg);
    }


    public static int w(String tag, String msg, Throwable throwable)
    {
        return Log.w(tag, msg, throwable);
    }


    public static int w(Object tag, String msg)
    {
        return w(toTag(tag), msg);
    }


    public static int w(Object tag, String msg, Throwable throwable)
    {
        return w(toTag(tag), msg, throwable);
    }


    public static int wformat(Object tag, String format, Object... args)
    {
        return w(toTag(tag), String.format(format, args));
    }


    public static int wformat(Object tag, Throwable throwable, String format, Object... args)
    {
        return w(toTag(tag), String.format(format, args), throwable);
    }
}
