/*
 * Copyright (C) 2013-2014 Neo Visionaries Inc.
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


/**
 * Math utility.
 *
 * @author Takahiko Kawasaki
 */
public final class Mathematics
{
    private Mathematics()
    {
    }


    /**
     * Clamp the given value to the given range.
     *
     * <p>
     * This method just does {@link Math#max(int, int) Math.max}{@code
     * (min, }{@link Math#min(int, int) Math.min}{@code (value, max)).
     * </p>
     */
    public static int clamp(int min, int value, int max)
    {
        return Math.max(min, Math.min(value, max));
    }


    /**
     * Clamp the given value to the given range.
     *
     * <p>
     * This method just does {@link Math#max(long, long) Math.max}{@code
     * (min, }{@link Math#min(long, long) Math.min}{@code (value, max)).
     * </p>
     */
    public static long clamp(long min, long value, long max)
    {
        return Math.max(min, Math.min(value, max));
    }


    /**
     * Clamp the given value to the given range.
     *
     * <p>
     * This method just does {@link Math#max(float, float) Math.max}{@code
     * (min, }{@link Math#min(float, float) Math.min}{@code (value, max)).
     * </p>
     */
    public static float clamp(float min, float value, float max)
    {
        return Math.max(min, Math.min(value, max));
    }


    /**
     * Clamp the given value to the given range.
     *
     * <p>
     * This method just does {@link Math#max(double, double) Math.max}{@code
     * (min, }{@link Math#min(double, double) Math.min}{@code (value, max)).
     * </p>
     */
    public static double clamp(double min, double value, double max)
    {
        return Math.max(min, Math.min(value, max));
    }
}
