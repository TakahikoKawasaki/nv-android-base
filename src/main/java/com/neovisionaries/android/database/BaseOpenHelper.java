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
package com.neovisionaries.android.database;


import java.util.concurrent.locks.ReentrantReadWriteLock;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.neovisionaries.android.app.App;


/**
 * Database open helper with a read/write lock, which is useful
 * when a database needs to be accessed by multiple threads.
 *
 * <style type="text/css">
 * span.keyword { color: purple; font-weight: bold; }
 * span.comment { color: green; }
 * span.field { color: blue; font-style: italic; }
 * </style>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * <span class="comment">// A subclass of BaseOpenHelper.</span>
 * MyOpenHelper helper = ...;
 *
 * SQLiteDatabase db = <span class="keyword">null</span>;
 *
 * <span class="keyword">try</span>
 * {
 *     <span class="comment">// Open the database in read mode. openReadable()
 *     // acquires a read lock and then opens the database
 *     // by getReadableDatabase().</span>
 *     db = helper.{@link #openReadable()};
 *
 *     ......
 * }
 * <span class="keyword">finally</span>
 * {
 *     <span class="comment">// Close the database and release the read lock.</span>
 *     helper.{@link #closeReadable(SQLiteDatabase) closeReadable}(db);
 * }</pre>
 *
 * @since 1.8
 * @author Takahiko Kawasaki
 */
public abstract class BaseOpenHelper extends SQLiteOpenHelper
{
    /**
     * The internal read-write lock.
     */
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);



    /**
     * Constructor. This constructor does the following.
     *
     * <pre style="border: 1px solid black; margin: 0.5em; padding: 0.5em;">
     * {@link SQLiteOpenHelper#SQLiteOpenHelper(Context, String, CursorFactory, int)
     * super}(context, name, factory, version);</pre>
     */
    public BaseOpenHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    /**
     * Constructor. This constructor does the following.
     *
     * <pre style="border: 1px solid black; margin: 0.5em; padding: 0.5em;">
     * {@link #BaseOpenHelper(Context, String, CursorFactory, int)
     * this}({@link App}.{@link App#getInstance() getInstance()}.{@link
     * App#getContext() getContext()}, name, factory, version);</pre>
     */
    public BaseOpenHelper(String name, CursorFactory factory, int version)
    {
        this(App.getInstance().getContext(), name, factory, version);
    }


    /**
     * Constructor. This constructor does the following.
     *
     * <pre style="border: 1px solid black; margin: 0.5em; padding: 0.5em;">
     * {@link #BaseOpenHelper(String, CursorFactory, int)
     * this}({@link App}.{@link App#getInstance() getInstance()}.{@link
     * App#getContext() getContext()}.{@link Context#getPackageName()
     * getPackageName()}, factory, version);</pre>
     */
    public BaseOpenHelper(CursorFactory factory, int version)
    {
        this(App.getInstance().getContext().getPackageName(), factory, version);
    }


    /**
     * Constructor. This constructor does the following.
     *
     * <pre style="border: 1px solid black; margin: 0.5em; padding: 0.5em;">
     * {@link #BaseOpenHelper(CursorFactory, int) this}(null, version);</pre>
     */
    public BaseOpenHelper(int version)
    {
        this(null, version);
    }


    /**
     * Open the database in read mode.
     *
     * <p>
     * This method acquires a read lock first, then calls
     * {@link #getReadableDatabase()}. If {@code getReadableDatabase()}
     * throws an {@code SQLiteException}, the read lock is released and
     * the exception is re-thrown. The {@code SQLiteDatabase} instance
     * returned by this method has to be closed by
     * {@link #closeReadable(SQLiteDatabase)}, not by {@link
     * SQLiteDatabase#close()}. Otherwise, the read lock is not released.
     * </p>
     */
    public SQLiteDatabase openReadable()
    {
        try
        {
            // Acquire a read lock before opening the database.
            beginReadLock();

            // Open the database in read mode.
            return getReadableDatabase();
        }
        catch (SQLiteException e)
        {
            // Failed to open the database in read mode.
            // Release the read lock.
            endReadLock();

            // Re-throw the SQLiteException.
            throw e;
        }
    }


    /**
     * Close a database which has been opened by {@link #openReadable()}.
     *
     * <p>
     * This method closes the passed database by calling
     * {@link SQLiteDatabase#close() db.close()} and then releases
     * a read lock (even if {@code db.close()} failed).
     * </p>
     *
     * @param db
     *         A database which has been opened by {@link #openReadable()}.
     *         If {@code null} is given, nothing is done (any read lock
     *         is released).
     */
    public void closeReadable(SQLiteDatabase db)
    {
        if (db == null)
        {
            // Nothing is done. Any read lock is released.
            return;
        }

        try
        {
            // Close the database.
            db.close();
        }
        finally
        {
            // Release a read lock.
            endReadLock();
        }
    }


    /**
     * Open the database in write mode.
     *
     * <p>
     * This method acquires a write lock first, then calls
     * {@link #getWritableDatabase()}. If {@code getWritableDatabase()}
     * throws an {@code SQLiteException}, the write lock is released and
     * the exception is re-thrown. The {@code SQLiteDatabase} instance
     * returned by this method has to be closed by
     * {@link #closeWritable(SQLiteDatabase)}, not by {@link
     * SQLiteDatabase#close()}. Otherwise, the write lock is not released.
     * </p>
     */
    public SQLiteDatabase openWritable()
    {
        try
        {
            // Acquire a write lock before opening the database.
            beginWriteLock();

            // Open the database in write mode.
            return getWritableDatabase();
        }
        catch (SQLiteException e)
        {
            // Failed to open the database in write mode.
            // Release the write lock.
            endWriteLock();

            // Re-throw the SQLiteException.
            throw e;
        }
    }


    /**
     * Close a database which has been opened by {@link #openWritable()}.
     *
     * <p>
     * This method closes the passed database by calling
     * {@link SQLiteDatabase#close() db.close()} and then releases
     * a write lock (even if {@code db.close()} failed).
     * </p>
     *
     * @param db
     *         A database which has been opened by {@link #openWritable()}.
     *         If {@code null} is given, nothing is done (any write lock
     *         is released).
     */
    public void closeWritable(SQLiteDatabase db)
    {
        if (db == null)
        {
            // Nothing is done. (\Any write lock is released.
            return;
        }

        try
        {
            // Close the database.
            db.close();
        }
        finally
        {
            // Release a write lock.
            endWriteLock();
        }
    }


    /**
     * Get the internal read-write lock instance.
     */
    public ReentrantReadWriteLock getLock()
    {
        return rwLock;
    }


    /**
     * Acquire a read lock.
     */
    private void beginReadLock()
    {
        rwLock.readLock().lock();
    }


    /**
     * Release a read lock.
     */
    private void endReadLock()
    {
        rwLock.readLock().unlock();
    }


    /**
     * Acquire a write lock.
     */
    private void beginWriteLock()
    {
        rwLock.writeLock().lock();
    }


    /**
     * Release a write lock.
     */
    private void endWriteLock()
    {
        rwLock.writeLock().unlock();
    }
}
