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


import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWrapper;


/**
 * A cursor wrapper that provides some utility methods such as
 * {@code getXxxByName(String columnName)}.
 *
 * @since 1.3
 * @author Takahiko Kawasaki
 */
public class BaseCursor extends CursorWrapper
{
    /**
     * Constructor that just calls {@code super(cursor)}.
     *
     * @param cursor
     *         The wrapped cursor.
     */
    public BaseCursor(Cursor cursor)
    {
        super(cursor);
    }


    /**
     * Copy the value of the requested column to the given buffer.
     *
     * @param columnName
     *         Column name.
     *
     * @param buffer
     *         Destination buffer to copy data to.
     */
    public void copyStringToBufferByName(String columnName, CharArrayBuffer buffer)
    {
        copyStringToBuffer(getColumnIndex(columnName), buffer);
    }


    /**
     * Get the value of the requested column as a byte array.
     *
     * <p>
     * This method is equivalent to {@link #getBlob(int) getBlob}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public byte[] getBlobByName(String columnName)
    {
        return getBlob(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as a double.
     *
     * <p>
     * This method is equivalent to {@link #getDouble(int) getDouble}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public double getDoubleByName(String columnName)
    {
        return getDouble(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as a float.
     *
     * <p>
     * This method is equivalent to {@link #getFloat(int) getFloat}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public float getFloatByName(String columnName)
    {
        return getFloat(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as an int.
     *
     * <p>
     * This method is equivalent to {@link #getInt(int) getInt}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public int getIntByName(String columnName)
    {
        return getInt(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as a long.
     *
     * <p>
     * This method is equivalent to {@link #getLong(int) getLong}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public long getLongByName(String columnName)
    {
        return getLong(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as a short.
     *
     * <p>
     * This method is equivalent to {@link #getShort(int) getShort}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public short getShortByName(String columnName)
    {
        return getShort(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as a String.
     *
     * <p>
     * This method is equivalent to {@link #getString(int) getString}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public String getStringByName(String columnName)
    {
        return getString(getColumnIndex(columnName));
    }


    /**
     * Check if the value of the requested column is null.
     *
     * <p>
     * This method is equivalent to {@link #isNull(int) isNull}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public boolean isNullByName(String columnName)
    {
        return isNull(getColumnIndex(columnName));
    }


    /**
     * Get the value of the requested column as a boolean.
     *
     * <p>
     * This method is equivalent to {@code Boolean.valueOf(}{@link #getString(int)
     * getString}{@code (columnIndex)).booleanValue()}.
     * </p>
     *
     * @param columnIndex
     *         Column index.
     *
     * @return
     *         The value of the column.
     */
    public boolean getBoolean(int columnIndex)
    {
        String value = getString(columnIndex);

        return Boolean.valueOf(value).booleanValue();
    }


    /**
     * Get the value of the requested column as a boolean.
     *
     * <p>
     * This method is equivalent to {@link #getBoolean(int) getBoolean}{@code
     * (}{@link #getColumnIndex(String) getColumnIndex}{@code
     * (columnName))}.
     * </p>
     *
     * @param columnName
     *         Column name.
     *
     * @return
     *         The value of the column.
     */
    public boolean getBooleanByName(String columnName)
    {
        return getBoolean(getColumnIndex(columnName));
    }


    /**
     * Wait until this cursor finishes fetching data from the database.
     *
     * <p>
     * The specification allows data fetch to be performed asynchronously.
     * As a matter of fact, the cursor implementation for SQLite may behave
     * asynchronously. Asynchronous implementations of {@code Cursor.getCount()}
     * return a negative value instead of 0 until they finish fetching data.
     * </p>
     *
     * <p>
     * This method waits for this cursor to prepare data.
     * </p>
     */
    public void join() throws InterruptedException
    {
        if (0 <= getCount())
        {
            // Data retrieval has already finished.
            return;
        }

        new CursorWaiter(this).join();
    }
}
