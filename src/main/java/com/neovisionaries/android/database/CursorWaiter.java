/*
 * Copyright (C) 2013 Neo Visionaries Inc.
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


import android.database.Cursor;
import android.database.DataSetObserver;


/**
 * Utility to wait for a cursor to retrieve data.
 *
 * <style type="text/css">
 * span.keyword { color: purple; font-weight: bold; }
 * span.comment { color: green; }
 * </style>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * Cursor cursor = ...;
 *
 * <span class="keyword">try</span>
 * {
 *     <span class="comment">// Wait for the cursor to retrieve data.</span> 
 *     <span class="keyword">new</span> CursorWaiter(cursor).{@link #join()};
 * }
 * <span class="keyword">catch</span> (InterruptedException e) {}
 * </pre>
 *
 * @since 1.3
 * @author Takahiko Kawasaki
 */
public class CursorWaiter extends DataSetObserver
{
    private final Cursor cursor;
    private Thread waiter;


    /**
     * Constructor with a cursor to wait for.
     *
     * @param cursor
     *         A cursor to wait for.
     */
    public CursorWaiter(Cursor cursor)
    {
        if (cursor == null)
        {
            throw new IllegalArgumentException("cursor is null.");
        }

        this.cursor = cursor;
    }


    /**
     * Wait for the cursor to retrieve data.
     */
    public void join() throws InterruptedException
    {
        if (0 <= cursor.getCount())
        {
            // The cursor has already finished data retrieval.
            // Return without starting any waiting thread.
            return;
        }

        waiter = new WaiterThread();
        waiter.start();

        cursor.registerDataSetObserver(this);

        // Interrupt once for the case where the data retrieval
        // had finished before registerDataSetObserver() finished.
        waiter.interrupt();

        // Wait until data retrieval finishes.
        waiter.join();
    }


    @Override
    public void onChanged()
    {
        if (waiter.isAlive())
        {
            waiter.interrupt();
        }
    }


    private class WaiterThread extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                if (0 <= cursor.getCount())
                {
                    // The data retrieval has finished.
                    return;
                }

                try
                {
                    synchronized (this)
                    {
                        // Wait until onChanged() method is called.
                        wait();
                    }
                }
                catch (InterruptedException e) {}
            }
        }
    }
}
