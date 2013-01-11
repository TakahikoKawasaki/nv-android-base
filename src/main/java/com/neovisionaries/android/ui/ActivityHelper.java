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


import com.neovisionaries.android.core.App;
import com.neovisionaries.android.util.L;
import android.app.Activity;
import android.view.KeyEvent;


public final class ActivityHelper
{
    private ActivityHelper()
    {
    }


    /**
     * Mark this application as stopping and finish the given Activity.
     *
     * <p>
     * {@link App#setStopping(boolean) App.getInstance().setStopping}{@code (true)}
     * is called and then {@code finish()} is called on the given Activity.
     * </p>
     *
     * <p>
     * {@code BaseXxxActivity} classes can use this method to implement
     * {@code exit()} method. See the implementation of {@link
     * BaseActivity#exit()} as an example.
     * </p>
     *
     * @param self
     */
    public static void exit(Activity self)
    {
        // App instance.
        App app = App.getInstance();

        // Emit a log message.
        L.dformat(self, "===== APPLICATION '%s' (version = %s) STOPPING =====",
                app.getApplicationLabel(), app.getVersionName());

        // Make the application's state as "stopping".
        app.setStopping(true);

        // Close the Activity. After the Activity is closed,
        // another Activity's onResume() will be called. It is
        // expected the implementation of the onResume() checks
        // the return value of App.getInstance().getStopping()
        // and returns without doing anything if the return
        // value is true. This chain mechanism terminates the
        // application gracefully without destroying the life
        // cycle of Activities.
        self.finish();
    }


    /**
     * Exit this application by calling {@link #exit(Activity)
     * exit}{@code (true)} if the given key code is {@link
     * KeyEvent#KEYCODE_BACK}. Otherwise, call self.{@link
     * Activity#onKeyDown(int, KeyEvent) onKeyDown}{@code
     * (keyCode, event)}.
     *
     * <p>
     * {@code BottomXxxActivity} classes can use this method to implement
     * {@code onKeyDown(int, android.view.KeyEvent)} method.
     * See the implementation of {@link BottomActivity#onKeyDown(int, KeyEvent)}
     * as an example.
     * </p>
     *
     * @param self
     * @param keyCode
     * @param event
     * @return
     *         True if the key event was consumed.
     */
    public static boolean exitOnBackKeyDown(Activity self, int keyCode, KeyEvent event)
    {
        // If the given key code is 'back'.
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // Terminate this application.
            exit(self);

            // The key event was consumed.
            return true;
        }

        // The given key code is not 'back'.
        // Let the super class handle the key event.
        return self.onKeyDown(keyCode, event);
    }
}
