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
package com.neovisionaries.android.ui;


import android.app.Activity;
import com.neovisionaries.android.core.App;


/**
 * Base class for Activities that work as a root Activity of an application
 * whose Activities honor the chain of {@code finish()} mechanism.
 *
 * @author Takahiko Kawasaki
 */
public abstract class BaseRootActivity extends Activity
{
    /**
     * The implementation of this method does the following.
     *
     * <ol>
     * <li>Calls super.onResume().
     * <li>Calls {@code App.getInstance().}{@link App#isStopping() isStopping()}
     *     and if the return value is true, calls {@code finish()} and executes
     *     {@code App.getInstance().}{@link App#setStopping(boolean)
     *     setStopping}{@code (false)}. The {@code finish()} call terminates
     *     this application (if the subclass of BaseRootActivity is used as the
     *     root Activity). The reason to call {@code App.getInstance().setStopping(false)}
     *     is that Android may reuse this Activity instance instead of creating
     *     a new one.
     * <li>Otherwise, calls {@link #dispatch()} that subclasses must override.
     * </ol>
     *
     * <p>
     * Note that if a subclass of {@code BaseRootActivity} is used as the root
     * Activity, the application won't terminate until {@code
     * App.getInstance().}{@link App#setStopping(boolean) setStopping}{@code
     * (true)} is called. You may find {@link BaseActivity#exit()} useful to
     * stop your application.
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
     */
    protected abstract void dispatch();
}
