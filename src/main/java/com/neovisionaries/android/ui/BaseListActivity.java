/*
 * Copyright (C) 2012-2013 Neo Visionaries Inc.
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
package com.neovisionaries.android.ui;


import com.neovisionaries.android.core.App;
import android.app.ListActivity;


/**
 * Base class for Activities that honor the chain of {@code finish()}
 * mechanism.
 *
 * <p>
 * {@code onResume()} method of subclasses of {@code BaseActivity}
 * should follow the template to support the chain of {@code finish()}
 * mechanism. See the description of {@link #onResume()} for details.
 * </p>
 *
 * @see BaseActivity
 *
 * @author Takahiko Kawasaki
 */
public class BaseListActivity extends ListActivity
{
    /**
     * Check if this application is stopping or not.
     *
     * <p>
     * This method calls {@code App.getInstance().}{@link App#isStopping()
     * isStopping()} and returns its return value. In other words,
     * this method is an alias of {@code App.getInstance().isStopping()}.
     * </p>
     *
     * @return
     *         True if this application is stopping.
     */
    public boolean isStopping()
    {
        return App.getInstance().isStopping();
    }


    /**
     * Exit from this application.
     *
     * <p>
     * This method marks this application as stopping (by calling
     * {@code App.getInstance().}{@link App#setStopping(boolean)
     * setStopping}{@code (true)}) and closes this Activity (by
     * calling {@code finish()}). If all Activities on the Activity
     * stack honor the chain of {@code finish()} mechanism, all the
     * Activities including the root Activity will be finished.
     * </p>
     *
     * <p>
     * The implementation of this method emits "STOPPING" log message.
     * </p>
     */
    public void exit()
    {
        ActivityHelper.exit(this);
    }


    /**
     * Called when this Activity gets resumed.
     *
     * <p>
     * First, the implementation of {@code onResume()} method of {@link
     * BaseListActivity} checks if this application is stopping. In other
     * words, the implementation checks if {@link #exit()} method of an
     * Activity on the stack above this Activity has been called. Then,
     * if the application is stopping, the implementation calls {@code
     * this.finish()}. This causes another Activity instance (which is
     * right under this Activity instance on the stack) that belongs to
     * the application to get resumed. It is expected that the {@code
     * onResume()} instance of the next Activity is implemented like
     * the following in order to let the application finish gracefully.
     * </p>
     *
     * <style type="text/css">
     * span.keyword { color: purple; font-weight: bold; }
     * span.annotation { color: grey; }
     * span.comment { color: green; }
     * span.field { color: blue; font-style: italic; }
     * </style>
     *
     * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
     * <span class="keyword">super</span>.onResume();
     *
     * <span class="comment">// Check if the application is stopping.</span>
     * <span class="keyword">if</span> (isStopping())
     * {
     *     <span class="comment">// finish() has already been called in super.onResume()
     *     // (if this Activity is a subclass of Base*Activity), so
     *     // leave this Activity without doing anything. This chain
     *     // of finish() will finally reach the root Activity.</span>
     *     <span class="keyword">return</span>;
     * }
     *
     * <span class="comment">// Code specific to this Activity.</span>
     * ......
     * </pre>
     */
    @Override
    protected void onResume()
    {
        super.onResume();

        if (isStopping())
        {
            finish();
        }
    }
}
