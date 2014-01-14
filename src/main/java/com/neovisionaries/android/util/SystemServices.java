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
package com.neovisionaries.android.util;


import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.service.wallpaper.WallpaperService;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import com.neovisionaries.android.app.App;


/**
 * System service utility.
 *
 * @since 1.2
 * @author Takahiko Kawasaki
 */
public final class SystemServices
{
    private SystemServices()
    {
    }


    /**
     * Get a system service by name.
     *
     * <p>
     * This method is equivalent to {@link App}{@code .}{@link App#getInstance()
     * getInstance()}{@code .}{@link App#getContext() getContext()}{@code
     * .getSystemService(serviceName)}. Therefore, {@code App} class must be
     * initialized by {@link App#init(Context)} before calling this method.
     * </p>
     *
     * @param serviceName
     *         System service name.
     *
     * @return
     *         System service instance.
     */
    public static Object getSystemService(String serviceName)
    {
        return App.getInstance().getContext().getSystemService(serviceName);
    }


    /**
     * Get {@link AccessibilityManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.ACCESSIBILITY_SERVICE)}.
     * </p>
     *
     * @return
     *         AccessibilityManager
     */
    public static AccessibilityManager getAccessibilityManager()
    {
        return (AccessibilityManager)getSystemService(Context.ACCESSIBILITY_SERVICE);
    }


    /**
     * Get {@link AccountManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.ACCOUNT_SERVICE)}.
     * </p>
     *
     * @return
     *         AccountManager
     */
    public static AccountManager getAccountManager()
    {
        return (AccountManager)getSystemService(Context.ACCOUNT_SERVICE);
    }


    /**
     * Get {@link ActivityManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.ACTIVITY_SERVICE)}.
     * </p>
     *
     * @return
     *         ActivityManager
     */
    public static ActivityManager getActivityManager()
    {
        return (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
    }


    /**
     * Get {@link AlarmManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.ALARM_SERVICE)}.
     * </p>
     *
     * @return
     *         AlarmManager
     */
    public static AlarmManager getAlarmManager()
    {
        return (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }


    /**
     * Get {@link AudioManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.AUDIO_SERVICE)}.
     * </p>
     *
     * @return
     *         AudioManager
     */
    public static AudioManager getAudioManager()
    {
        return (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    }


    /**
     * Get {@link ClipboardManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.CLIPBOARD_SERVICE)}.
     * </p>
     *
     * @return
     *         ClipboardManager
     */
    public static ClipboardManager getClipboardManager()
    {
        return (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
    }


    /**
     * Get {@link DevicePolicyManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.DEVICE_POLICY_SERVICE)}.
     * </p>
     *
     * @return
     *         DevicePolicyManager
     */
    public static DevicePolicyManager getDevicePolicyManager()
    {
        return (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
    }


    /**
     * Get {@link DropBoxManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.DROPBOX_SERVICE)}.
     * </p>
     *
     * @return
     *         DropBoxManager
     */
    public static DropBoxManager getDownloadManager()
    {
        return (DropBoxManager)getSystemService(Context.DROPBOX_SERVICE);
    }


    /**
     * Get {@link InputMethodManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.INPUT_METHOD_SERVICE)}.
     * </p>
     *
     * @return
     *         InputMethodManager
     */
    public static InputMethodManager getInputMethodManager()
    {
        return (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    /**
     * Get {@link KeyguardManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.KEYGUARD_SERVICE)}.
     * </p>
     *
     * @return
     *         KeyguardManager
     */
    public static KeyguardManager getKeyguardManager()
    {
        return (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
    }


    /**
     * Get {@link LayoutInflater}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.LAYOUT_INFLATER_SERVICE)}.
     * </p>
     *
     * @return
     *         LayoutInflater
     */
    public static LayoutInflater getLayoutInflater()
    {
        return (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * Get {@link LocationManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.LOCATION_SERVICE)}.
     * </p>
     *
     * @return
     *         LocationManager
     */
    public static LocationManager getLocationManager()
    {
        return (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }


    /**
     * Get {@link NotificationManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.NOTIFICATION_SERVICE)}.
     * </p>
     *
     * @return
     *         NotificationManager
     */
    public static NotificationManager getNotificationManager()
    {
        return (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    }


    /**
     * Get {@link PowerManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.POWER_SERVICE)}.
     * </p>
     *
     * @return
     *         PowerManager
     */
    public static PowerManager getPowerManager()
    {
        return (PowerManager)getSystemService(Context.POWER_SERVICE);
    }


    /**
     * Get {@link SearchManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.SEARCH_SERVICE)}.
     * </p>
     *
     * @return
     *         SearchManager
     */
    public static SearchManager getSearchManager()
    {
        return (SearchManager)getSystemService(Context.SEARCH_SERVICE);
    }


    /**
     * Get {@link SensorManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.SENSOR_SERVICE)}.
     * </p>
     *
     * @return
     *         SensorManager
     */
    public static SensorManager getSensorManager()
    {
        return (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }


    /**
     * Get {@link TelephonyManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.TELEPHONY_SERVICE)}.
     * </p>
     *
     * @return
     *         TelephonyManager
     */
    public static TelephonyManager getTelephonyManager()
    {
        return (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    }


    /**
     * Get {@link UiModeManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.UI_MODE_SERVICE)}.
     * </p>
     *
     * @return
     *         UiModeManager
     */
    public static UiModeManager getUiModeManager()
    {
        return (UiModeManager)getSystemService(Context.UI_MODE_SERVICE);
    }


    /**
     * Get {@link Vibrator}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.VIBRATOR_SERVICE)}.
     * </p>
     *
     * @return
     *         Vibrator
     */
    public static Vibrator getVibrator()
    {
        return (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }


    /**
     * Get {@link WallpaperService}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.WALLPAPER_SERVICE)}.
     * </p>
     *
     * @return
     *         WallpaperService
     */
    public static WallpaperService getWallpaperService()
    {
        return (WallpaperService)getSystemService(Context.WALLPAPER_SERVICE);
    }


    /**
     * Get {@link WifiManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.WIFI_SERVICE)}.
     * </p>
     *
     * @return
     *         WifiManager
     */
    public static WifiManager getWifiManager()
    {
        return (WifiManager)getSystemService(Context.WIFI_SERVICE);
    }


    /**
     * Get {@link WindowManager}.
     *
     * <p>
     * This method is equivalent to {@link #getSystemService(String)
     * getSystemService}{@code (Context.WINDOW_SERVICE)}.
     * </p>
     *
     * @return
     *         WindowManager
     */
    public static WindowManager getWindowManager()
    {
        return (WindowManager)getSystemService(Context.WINDOW_SERVICE);
    }
}
