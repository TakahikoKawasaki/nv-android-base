/*
 * Copyright (C) 2011-2013 Neo Visionaries Inc.
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
package com.neovisionaries.android.core;


import com.neovisionaries.android.ui.BaseRootActivity;
import com.neovisionaries.android.util.L;
import android.app.Application;


/**
 * Base class for {@code android.app.Application} subclasses.
 * The purpose of {@code BaseApplication} is just to call
 * {@link App#init(android.content.Context) App.init(Context)}
 * to initialize {@code App} class.
 *
 * <p>
 * {@code BaseApplication} or its subclass is expected to be set
 * as the value of {@code android:name} attribute of {@code
 * application} tag in {@code AndroidManifest.xml} as shown below.
 * </p>
 *
 * <style type="text/css">
 * span.tag { color: #009999; font-weight: bold; }
 * span.key { color: purple; font-weight: bold; }
 * span.value { color: blue; font-style: italic; }
 * </style>
 *
 * <pre style="margin: 1em; padding: 0.5em; border: solid 1px black;">
 * <span class="tag">&lt;?xml</span> <span class="key">version</span>=<span class="value">"1.0"</span> <span class="key">encoding</span>=<span class="value">"utf-8"</span> <span class="tag">?&gt;</span>
 * <span class="tag">&lt;manifest</span>
 *     <span class="key">xmlns:android</span>=<span class="value">"http://schemas.android.com/apk/res/android"</span>
 *     ......<span class="tag">&gt;</span>
 *
 *     <span class="tag">&lt;application</span>
 *         <span class="key">android:name</span>=<span class="value">"com.neovisionaries.android.core.BaseApplication"</span><span class="tag">&gt;</span>
 *         ......<span class="tag">&gt;</span>
 * </pre>
 *
 * @see App
 *
 * @author Takahiko Kawasaki
 */
public class BaseApplication extends Application
{
    /**
     * Called after this instance was created. This is the physical
     * starting point of the application <span style="color: grey;">
     * (the 'logical' starting point is {@code onCreate(android.os.Bundle)}
     * of the root Activity)</span> , but because Android may not
     * discard this instance even after the root Activity finishes,
     * this method is not always called on application startup.
     *
     * <p>
     * The implementation of this method calls {@link
     * App#init(android.content.Context) App.init}{@code (this)} and
     * emits "CREATED" log message.
     * </p>
     *
     * @see BaseRootActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate()
    {
        super.onCreate();

        // Initialize App class.
        App.init(this);

        log("CREATED");
    }


    /**
     * Called before this instance is discarded. This is the physical
     * end point of the application <span style="color: grey;">(the
     * 'logical' end point is {@code onDestroy()} of the root
     * Activity)</span>, but because Android may not discard
     * this instance even after the root Activity finished, this method
     * is not always called on application termination. In addition,
     * the Android specification does not guarantee that this method
     * is called.
     *
     * <p>
     * The implementation of this method emits "TERMINATED" log message.
     * </p>
     *
     * @see BaseRootActivity#onDestroy()
     */
    @Override
    public void onTerminate()
    {
        log("TERMINATED");

        super.onTerminate();
    }


    private void log(String verb)
    {
        // App instance.
        App app = App.getInstance();

        // Emit the log message.
        L.dformat(this, "===== APPLICATION '%s' (version = %s) %s =====",
                app.getApplicationLabel(), app.getVersionName(), verb);
    }
}
