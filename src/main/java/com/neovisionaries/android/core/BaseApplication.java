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
package com.neovisionaries.android.core;


import com.neovisionaries.android.util.L;
import android.app.Application;


public class BaseApplication extends Application
{
    /**
     * The entry point of this application.
     *
     * <p>
     * {@link App#init(android.content.Context) App.init}{@code (this)} is called.
     * </p>
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
     * The exit point of this application.
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
