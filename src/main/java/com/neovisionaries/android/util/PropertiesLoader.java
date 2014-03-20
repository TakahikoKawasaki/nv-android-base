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
package com.neovisionaries.android.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import android.content.Context;
import android.content.res.Resources;
import com.neovisionaries.android.app.App;


/**
 * Properties loader.
 *
 * @since 1.8
 *
 * @author Takahiko Kawasaki
 */
public class PropertiesLoader
{
    /**
     * File location types.
     *
     * @author Takahiko Kawasaki
     */
    public enum FileLocation
    {
        /**
         * File system.
         *
         * <p>
         * The file is opened by {@link Context#openFileInput(String)
         * Context.openFileInput(file)}.
         * </p>
         */
        FILESYSTEM,

        /**
         * Classpath.
         *
         * <p>
         * The file is opened by {@code PropertiesLoader.class.}{@link
         * Class#getResourceAsStream(String) getResourceAsString(file)}.
         * If the file name given to a {@code load} method does not
         * start with {@code "/"}, {@code "/"} is prepended.
         * </p>
         */
        CLASSPATH,

        /**
         * {@code "assets"} folder.
         *
         * <p>
         * The file is opened by {@link android.content.res.AssetManager#open(String)
         * AssetManager.open(file)}.
         * </p>
         */
        ASSETS,

        /**
         * {@code "res/raw"} folder.
         *
         * <p>
         * If this file location type is passed to a {@code load} method, the resource
         * ID of the file (<code>R.raw.<i>file</i></code>) is looked up by
         * {@link Resources#getIdentifier(String, String, String)
         * Resources.getIdentifier}{@code (file, "raw", Context.}{@link
         * Context#getPackageName() getPackageName()}{@code )} and then the resource
         * is opened by {@link Resources#openRawResource(int)
         * Resources.openRawResource(resourceId)}.
         * </p>
         */
        RAW,
    }


    /**
     * Load properties from a file.
     *
     * <p>
     * This method is an alias of {@link #load(String, FileLocation[]) load}{@code
     * (file, }{@link FileLocation FileLocation}{@code .values())}.
     * </p>
     *
     * @param file
     *         File name.
     *
     * @return
     *         {@link Properties Properties} loaded from the file.
     *         {@code null} is returned on failure.
     *
     * @throws IllegalArgumentException
     *         {@code file} is {@code null}.
     */
    public static TypedProperties load(String file)
    {
        return load(file, FileLocation.values());
    }


    /**
     * Load properties from a file.
     *
     * <p>
     * This method tries to load the file from the given locations
     * in the order by calling {@link #load(String, FileLocation)}.
     * The content of the first successfully-loaded file is returned.
     * </p>
     *
     * @param file
     *         File name.
     *
     * @param locations
     *         Locations from which the file is loaded.
     *
     * @return
     *         {@link Properties Properties} loaded from the file.
     *         {@code null} is returned on failure.
     *
     * @throws IllegalArgumentException
     *         {@code file} is {@code null}, or {@code locations} is {@code null}.
     */
    public static TypedProperties load(String file, FileLocation[] locations)
    {
        // If file is null.
        if (file == null)
        {
            // file must be specified.
            throw new IllegalArgumentException("file is null.");
        }

        // If locations is null.
        if (locations == null)
        {
            // locations must be specified.
            throw new IllegalArgumentException("locations is null.");
        }

        TypedProperties properties = null;

        // For each file location.
        for (FileLocation location : locations)
        {
            // If location is null.
            if (location == null)
            {
                // Just ignore.
                continue;
            }

            // Load the file from the location.
            properties = load(file, location);

            // If loaded successfully.
            if (properties != null)
            {
                break;
            }
        }

        return properties;
    }


    /**
     * Load properties from a file.
     *
     * @param file
     *         File name. If {@code location} is {@link FileLocation#CLASSPATH
     *         CLASSPATH} and if {@code file} does not start with {@code "/"},
     *         {@code "/"} is prepended.
     *
     * @param location
     *         Location from which the file is loaded.
     *
     * @return
     *         {@link Properties Properties} loaded from the file.
     *         {@code null} is returned on failure.
     *
     * @throws IllegalArgumentException
     *         {@code file} is {@code null}, or {@code location} is {@code null}.
     */
    public static TypedProperties load(String file, FileLocation location)
    {
        // If file is null.
        if (file == null)
        {
            // file must be specified.
            throw new IllegalArgumentException("file is null.");
        }

        // If location is null.
        if (location == null)
        {
            // location must be specified.
            throw new IllegalArgumentException("location is null.");
        }

        InputStream in = null;

        try
        {
            // Open the file.
            in = open(file, location);

            if (in == null)
            {
                // Failed to open the file.
                return null;
            }

            // Build Properties from the input stream.
            Properties properties = load(in);

            // Wrap the properties.
            return new PropertiesWrapper(properties);
        }
        catch (IOException e)
        {
            // Failed to open the file, or Properties.load() failed.
            return null;
        }
        finally
        {
            // Close the input stream silently.
            close(in);
        }
    }


    private static InputStream open(String file, FileLocation location) throws IOException
    {
        switch (location)
        {
            case FILESYSTEM:
                return openFileSystem(file);

            case CLASSPATH:
                return openClasspath(file);

            case ASSETS:
                return openAssets(file);

            case RAW:
                return openRaw(file);

            default:
                return null;
        }
    }


    private static InputStream openFileSystem(String file) throws IOException
    {
        return getContext().openFileInput(file);
    }


    private static InputStream openClasspath(String file)
    {
        // If the file name does not start with '/'.
        if (file.startsWith("/") == false)
        {
            // Prepend '/'.
            file = "/" + file;
        }

        // Get the resource using the class loader
        // which has loaded this class.
        return PropertiesLoader.class.getResourceAsStream(file);
    }


    private static InputStream openAssets(String file) throws IOException
    {
        return getContext().getResources().getAssets().open(file);
    }


    private static InputStream openRaw(String file)
    {
        Context context = getContext();
        Resources resources = context.getResources();

        // Get the resource ID (R.raw.{file}).
        int resourceId = resources.getIdentifier(file, "raw", context.getPackageName());

        // If there is no such a resource.
        if (resourceId == 0)
        {
            return null;
        }

        return resources.openRawResource(resourceId);
    }


    private static void close(InputStream in)
    {
        if (in == null)
        {
            return;
        }

        try
        {
            in.close();
        }
        catch (IOException e)
        {
            // Just ignore.
        }
    }


    private static Context getContext()
    {
        return App.getInstance().getContext();
    }


    private static Properties load(InputStream in) throws IOException
    {
        // Create a empty Properties instance.
        Properties properties = new Properties();

        // Load the content of the file into the Properties instance.
        properties.load(in);

        // The content of the file was loaded successfully.
        return properties;
    }
}
