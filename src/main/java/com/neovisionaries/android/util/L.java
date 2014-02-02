/*
 * Copyright (C) 2012-2014 Neo Visionaries Inc.
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
 * Logging utility (Simple wrapper over android.util.Log).
 *
 * <p>
 * {@code L} class provides some methods whose {@code tag}
 * argument's type is {@code Object}. The methods convert
 * the given {@code tag} object into a {@code String}
 * instance by the steps shown below before calling
 * counterpart methods of {@code android.util.Log}.
 * </p>
 *
 * <ol>
 * <li>If {@code tag} implements {@code CharSequence},
 *     call {@code tag.toString()}.
 * <li>Otherwise, if {@code tag} is an instance of {@code Class<?>},
 *     call {@code ((Class<?>)tag).getSimpleName()}.
 * <li>Otherwise, call {@code tag.getClass().getSimpleName()}.
 *     <br/>
 * </ol>
 *
 * <p>
 * The conversion above allows programmers to write code snippets
 * like below.
 * </p>
 *
 * <style type="text/css">
 * span.keyword { color: purple; font-weight: bold; }
 * span.stringliteral { color: blue; }
 * span.comment { color: green; }
 * </style>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * <span class="comment">// 'this' can be used as tag.</span>
 * L.d(<span class="keyword">this</span>, <span class="stringliteral">"'this' can be used as tag."</span>);
 *
 * <span class="comment">// A Class instance can be used as tag.</span>
 * L.d(MyClass.<span class="keyword">class</span>, <span class="stringliteral">"A Class instance can be used as tag."</span>);
 * </pre>
 *
 * <p>
 * {@code L} class also provides {@code ?(Object tag, String format, Object... args)}
 * and {@code ?(Object tag, Throwable cause, String format, Object... args)} methods.
 * They internally build a {@code String} instance by calling
 * {@code String.format(format, args)} before calling counterpart
 * methods of {@code android.util.Log}. String concatenation by
 * {@code +} is not needed any more before calling logging methods.
 * </p>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * String firstName = ...;
 * String lastName = ...;
 *
 * L.d(<span class="keyword">this</span>, <span class="stringliteral">"Name = %s %s"</span>, firstName, lastName);
 * </pre>
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


    private L()
    {
    }


    /**
     * Emit a log message of DEBUG level by calling {@link Log#d(String, String)
     * Log.d}{@code (tag, message)}.
     */
    public static int d(String tag, String message)
    {
        return Log.d(tag, message);
    }


    /**
     * Emit a log message of DEBUG level by calling {@link Log#d(String, String, Throwable)
     * Log.d}{@code (tag, message, cause)}.
     */
    public static int d(String tag, String message, Throwable cause)
    {
        return Log.d(tag, message, cause);
    }


    /**
     * Emit a log message of DEBUG level.
     */
    public static int d(Object tag, String message)
    {
        return d(toTag(tag), message);
    }


    /**
     * Emit a log message of DEBUG level.
     */
    public static int d(Object tag, String message, Throwable cause)
    {
        return d(toTag(tag), message, cause);
    }


    /**
     * Emit a log message of DEBUG level.
     *
     * @since 1.7
     */
    public static int d(Object tag, String format, Object... args)
    {
        return dformat(tag, format, args);
    }


    /**
     * Emit a log message of DEBUG level.
     *
     * @since 1.7
     */
    public static int d(Object tag, Throwable cause, String format, Object... args)
    {
        return dformat(tag, cause, format, args);
    }


    /**
     * Emit a log message of DEBUG level.
     *
     * @deprecated Use {@link #d(Object, String, Object...)}.
     */
    @Deprecated
    public static int dformat(Object tag, String format, Object... args)
    {
        return d(toTag(tag), String.format(format, args));
    }


    /**
     * Emit a log message of DEBUG level.
     *
     * @deprecated Use {@link #d(Object, Throwable, String, Object...)}.
     */
    @Deprecated
    public static int dformat(Object tag, Throwable cause, String format, Object... args)
    {
        return d(toTag(tag), String.format(format, args), cause);
    }


    /**
     * Emit a log message of ERROR level by calling {@link Log#e(String, String)
     * Log.e}{@code (tag, message)}.
     */
    public static int e(String tag, String message)
    {
        return Log.e(tag, message);
    }


    /**
     * Emit a log message of ERROR level by calling {@link Log#e(String, String, Throwable)
     * Log.e}{@code (tag, message, cause)}.
     */
    public static int e(String tag, String message, Throwable cause)
    {
        return Log.e(tag, message, cause);
    }


    /**
     * Emit a log message of ERROR level.
     */
    public static int e(Object tag, String message)
    {
        return e(toTag(tag), message);
    }


    /**
     * Emit a log message of ERROR level.
     */
    public static int e(Object tag, String message, Throwable cause)
    {
        return e(toTag(tag), message, cause);
    }


    /**
     * Emit a log message of ERROR level.
     *
     * @since 1.7
     */
    public static int e(Object tag, String format, Object... args)
    {
        return eformat(tag, format, args);
    }


    /**
     * Emit a log message of ERROR level.
     *
     * @since 1.7
     */
    public static int e(Object tag, Throwable cause, String format, Object... args)
    {
        return eformat(tag, cause, format, args);
    }


    /**
     * Emit a log message of ERROR level.
     *
     * @deprecated Use {@link #e(Object, String, Object...)}.
     */
    @Deprecated
    public static int eformat(Object tag, String format, Object... args)
    {
        return e(toTag(tag), String.format(format, args));
    }


    /**
     * Emit a log message of ERROR level.
     *
     * @deprecated Use {@link #e(Object, Throwable, String, Object...)}.
     */
    @Deprecated
    public static int eformat(Object tag, Throwable cause, String format, Object... args)
    {
        return e(toTag(tag), String.format(format, args), cause);
    }


    /**
     * Emit a log message of INFO level by calling {@link Log#i(String, String)
     * Log.i}{@code (tag, message)}.
     */
    public static int i(String tag, String message)
    {
        return Log.i(tag, message);
    }


    /**
     * Emit a log message of INFO level by calling {@link Log#i(String, String, Throwable)
     * Log.i}{@code (tag, message, cause)}.
     */
    public static int i(String tag, String message, Throwable cause)
    {
        return Log.i(tag, message, cause);
    }


    /**
     * Emit a log message of INFO level.
     */
    public static int i(Object tag, String message)
    {
        return i(toTag(tag), message);
    }


    /**
     * Emit a log message of INFO level.
     */
    public static int i(Object tag, String message, Throwable cause)
    {
        return i(toTag(tag), message, cause);
    }


    /**
     * Emit a log message of INFO level.
     *
     * @since 1.7
     */
    public static int i(Object tag, String format, Object... args)
    {
        return iformat(tag, format, args);
    }


    /**
     * Emit a log message of INFO level.
     *
     * @since 1.7
     */
    public static int i(Object tag, Throwable cause, String format, Object... args)
    {
        return iformat(tag, cause, format, args);
    }


    /**
     * Emit a log message of INFO level.
     *
     * @deprecated Use {@link #i(Object, String, Object...)}.
     */
    @Deprecated
    public static int iformat(Object tag, String format, Object... args)
    {
        return i(toTag(tag), String.format(format, args));
    }


    /**
     * Emit a log message of INFO level.
     *
     * @deprecated Use {@link #i(Object, Throwable, String, Object...)}.
     */
    @Deprecated
    public static int iformat(Object tag, Throwable cause, String format, Object... args)
    {
        return i(toTag(tag), String.format(format, args), cause);
    }


    /**
     * Emit a log message of VERBOSE level by calling {@link Log#v(String, String)
     * Log.v}{@code (tag, message)}.
     */
    public static int v(String tag, String message)
    {
        return Log.v(tag, message);
    }


    /**
     * Emit a log message of VERBOSE level by calling {@link Log#v(String, String, Throwable)
     * Log.v}{@code (tag, message, cause)}.
     */
    public static int v(String tag, String message, Throwable cause)
    {
        return Log.v(tag, message, cause);
    }


    /**
     * Emit a log message of VERBOSE level.
     */
    public static int v(Object tag, String message)
    {
        return v(toTag(tag), message);
    }


    /**
     * Emit a log message of VERBOSE level.
     */
    public static int v(Object tag, String message, Throwable cause)
    {
        return v(toTag(tag), message, cause);
    }


    /**
     * Emit a log message of VERBOSE level.
     *
     * @since 1.7
     */
    public static int v(Object tag, String format, Object... args)
    {
        return vformat(tag, format, args);
    }


    /**
     * Emit a log message of VERBOSE level.
     *
     * @since 1.7
     */
    public static int v(Object tag, Throwable cause, String format, Object... args)
    {
        return vformat(tag, cause, format, args);
    }


    /**
     * Emit a log message of VERBOSE level.
     *
     * @deprecated Use {@link #v(Object, String, Object...)}.
     */
    @Deprecated
    public static int vformat(Object tag, String format, Object... args)
    {
        return v(toTag(tag), String.format(format, args));
    }


    /**
     * Emit a log message of VERBOSE level.
     *
     * @deprecated Use {@link #v(Object, Throwable, String, Object...)}.
     */
    @Deprecated
    public static int vformat(Object tag, Throwable cause, String format, Object... args)
    {
        return v(toTag(tag), String.format(format, args), cause);
    }


    /**
     * Emit a log message of WARN level by calling {@link Log#w(String, String)
     * Log.w}{@code (tag, message)}.
     */
    public static int w(String tag, String message)
    {
        return Log.w(tag, message);
    }


    /**
     * Emit a log message of WARN level by calling {@link Log#w(String, String, Throwable)
     * Log.w}{@code (tag, message, cause)}.
     */
    public static int w(String tag, String message, Throwable cause)
    {
        return Log.w(tag, message, cause);
    }


    /**
     * Emit a log message of WARN level.
     */
    public static int w(Object tag, String message)
    {
        return w(toTag(tag), message);
    }


    /**
     * Emit a log message of WARN level.
     */
    public static int w(Object tag, String message, Throwable cause)
    {
        return w(toTag(tag), message, cause);
    }


    /**
     * Emit a log message of WARN level.
     *
     * @since 1.7
     */
    public static int w(Object tag, String format, Object... args)
    {
        return wformat(tag, format, args);
    }


    /**
     * Emit a log message of WARN level.
     *
     * @since 1.7
     */
    public static int w(Object tag, Throwable cause, String format, Object... args)
    {
        return wformat(tag, cause, format, args);
    }


    /**
     * Emit a log message of WARN level.
     *
     * @deprecated Use {@link #w(Object, String, Object...)}.
     */
    @Deprecated
    public static int wformat(Object tag, String format, Object... args)
    {
        return w(toTag(tag), String.format(format, args));
    }


    /**
     * Emit a log message of WARN level.
     *
     * @deprecated Use {@link #w(Object, Throwable, String, Object...)}.
     */
    @Deprecated
    public static int wformat(Object tag, Throwable cause, String format, Object... args)
    {
        return w(toTag(tag), String.format(format, args), cause);
    }
}
