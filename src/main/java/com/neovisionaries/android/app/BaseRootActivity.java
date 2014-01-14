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
package com.neovisionaries.android.app;


import android.app.Activity;
import android.os.Bundle;
import com.neovisionaries.android.util.L;


/**
 * Base class for Activities that work as a root Activity of an application
 * whose Activities honor the chain of {@code finish()} mechanism.
 *
 * <p>
 * Sub classes are required to implement {@link #dispatch()} method which is
 * expected to launch another Activity. A simple example of a root Activity
 * that extends {@code BaseRootActivity} is shown below.
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
 * <span class="keyword">public class</span> RootActivity <span class="keyword">extends</span> BaseRootActivity
 * {
 *     <span class="annotation">&#x0040;Override</span>
 *     <span class="keyword">public void</span> onCreate(Bundle savedInstance)
 *     {
 *         <span class="keyword">super</span>.onCreate(savedInstance);
 *
 *         setContentView(R.layout.<span class="field">root_activity</span>);
 *     }
 *
 *     <span class="annotation">&#x0040;Override</span>
 *     <span class="keyword">protected void</span> dispatch()
 *     {   <span class="comment">
 *         // Start HomeActivity. Activities launched from here
 *         // should call App.getInstance().setStopping(true)
 *         // when they receive a key down event of KEYCODE_BACK.
 *         // Otherwise, such Activities will be re-launched by
 *         // the onResume() implementation of BaseRootActivity.
 *         // You may find BottomActivity and its variants useful.</span>
 *         startActivity(<span class="keyword">new</span> Intent(<span class="keyword">this</span>, HomeActivity.<span class="keyword">class</span>));
 *
 *         <span class="comment">// Note that finish() should not be called here.
 *         // This RootActivity instance should sit at the
 *         // bottom of the Activity stack in order to benefit
 *         // from the default mechanism of Android's Activity
 *         // management.</span>
 *     }
 * }
 * </pre>
 *
 * @author Takahiko Kawasaki
 */
public abstract class BaseRootActivity extends Activity
{
    /**
     * Called when this Activity gets resumed.
     * The implementation of this method does the following.
     *
     * <br/><br/>
     * <ol>
     * <li>Calls {@code super.onResume()}.<br/><br/>
     * <li>Calls {@code App.getInstance().}{@link App#isStopping() isStopping()}
     *     and if the return value is true, calls {@code finish()} and executes
     *     {@code App.getInstance().}{@link App#setStopping(boolean)
     *     setStopping}{@code (false)}. The {@code finish()} call terminates
     *     this application (if the subclass of BaseRootActivity is used as the
     *     root Activity). The reason to call {@code App.getInstance().setStopping(false)}
     *     is that Android may reuse this Activity instance instead of creating
     *     a new one.<br/><br/>
     * <li>Otherwise, calls {@link #dispatch()} that subclasses must implement.
     *     <br/><br/>
     * </ol>
     *
     * <p>
     * Note that if a subclass of {@code BaseRootActivity} is used as the root
     * Activity, the application won't terminate until {@code
     * App.getInstance().}{@link App#setStopping(boolean) setStopping}{@code
     * (true)} is explicitly called. You may find {@link BaseActivity#exit()}
     * useful to stop your application.
     * </p>
     */
    @Override
    protected final void onResume()
    {
        super.onResume();

        if (App.getInstance().isStopping())
        {
            // Close this Activity, meaning that this application is closed
            // because this Activity is the root.
            finish();

            // Reset the termination state because Android may reuse this
            // Activity instance instead of creating a new one.
            App.getInstance().setStopping(false);
        }
        else
        {
            // Launch another Activity.
            dispatch();
        }
    }


    /**
     * Launch another Activity.
     *
     * <p>
     * This method is called from the context of {@code onResume()} if and
     * only if {@code App.getInstance().}{@link App#isStopping() isStopping()}
     * returned false when {@code onResume()} of this Activity was called.
     * Implementations of this method are expected to invoke another Activity.
     * </p>
     *
     * <p>
     * Note that implementations of this method should not call {@code finish()}
     * after invoking another Activity. It is because the purpose of the root
     * Activity is to sit at the bottom of the Activity stack so that this
     * application can benefit from the default mechanism of Android's Activity
     * management.
     * </p>
     *
     * <p>
     * Activities launched from within {@code dispatch()} method should call
     * {@code App.getInstance().setStopping(true)} when they receive a key
     * down event of {@code KEYCODE_BACK}. Otherwise, such Activities will
     * be re-launched by the {@code onResume()} implementation of
     * {@code BaseRootActivity}. A simple way to satisfy the requirement is
     * to use {@link BottomActivity} or its variants whose {@code onKeyDown}
     * method calls {@code App.getInstance().setStopping(true)}.
     * </p>
     */
    protected abstract void dispatch();


    /**
     * Called when this Activity gets started.
     *
     * <p>
     * This method is the best point to re-initialize the application.
     * In other words, this is the logical starting point of the application.
     * <span style="color: grey;">(The 'physical' starting point of the
     * application is {@code onCreate()} of {@code android.app.Application}.)
     * </span>
     * </p>
     *
     * <p>
     * The implementation of this method emits "STARTED" log message.
     * </p>
     *
     * @see BaseApplication#onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        log("STARTED");
    }


    /**
     * Called when this Activity gets stopped.
     *
     * <p>
     * This method is the best point to finalize the application.
     * In other words, this is the logical end point of the application.
     * <span style="color: grey;">(The 'physical' end point of the
     * application is {@code onTerminate()} of {@code android.app.Application}.)
     * </span>
     * </p>
     *
     * <p>
     * The implementation of this method emits "STOPPED" log message.
     * </p>
     *
     * @see BaseApplication#onTerminate()
     */
    @Override
    protected void onDestroy()
    {
        log("STOPPED");

        super.onDestroy();
    }


    private void log(String verb)
    {
        // App instance.
        App app = App.getInstance();

        // Emit the log message.
        L.dformat(this, "== APPLICATION '%s' (version = %s) %s ==",
                app.getApplicationLabel(), app.getVersionName(), verb);
    }
}
