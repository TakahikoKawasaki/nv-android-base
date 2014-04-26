/*
 * Copyright (C) 2014 Neo Visionaries Inc.
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


import java.io.Serializable;
import android.os.Parcel;


/**
 * Utilities for {@link Parcel}.
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
public class ParcelUtils
{
    private ParcelUtils()
    {
    }


    /**
     * Read a {@code boolean} value from the {@code Parcel}.
     *
     * <p>
     * This method reads one {@code byte} and returns {@code true}
     * if the read value is not 0.
     * </p>
     *
     * @param in
     *         {@code Parcel} to read from.
     *
     * @return
     *         A {@code boolean} value read from the {@code Parcel}.
     */
    public static boolean readBoolean(Parcel in)
    {
        return (in.readByte() != 0);
    }


    /**
     * Write a {@code boolean} value into the {@code Parcel}.
     *
     * <p>
     * This method writes {@code (byte)1} when {@code value} is {@code true}
     * and writes {@code (byte)0} when {@code value} is {@code false}.
     * </p>
     *
     * @param out
     *         {@code Parcel} to write into.
     *
     * @param value
     *         A boolean value to write.
     */
    public static void writeBoolean(Parcel out, boolean value)
    {
        out.writeByte(value ? (byte)1 : (byte)0);
    }


    /**
     * Read a {@code char} value from the {@code Parcel}.
     *
     * <p>
     * This method reads one {@code int} and converts the read value
     * into a {@code char} value.
     * </p>
     *
     * @param in
     *         {@code Parcel} to read from.
     *
     * @return
     *         A {@code char} value read from the {@code Parcel}.
     */
    public static char readChar(Parcel in)
    {
        return (char)(in.readInt() & 0xFFFF);
    }


    /**
     * Write a {@code char} value into the {@code Parcel}.
     *
     * <p>
     * This method writes the given {@code char} value as {@code int}.
     * </p>
     *
     * @param out
     *         {@code Parcel} to write into.
     *
     * @param value
     *         A {@code char} value to write.
     */
    public static void writeChar(Parcel out, char value)
    {
        out.writeInt(value);
    }


    /**
     * Read a {@code String} object from the {@code Parcel}.
     *
     * <p>
     * First, this method reads a {@code boolean} value from the
     * {@code Parcel} using {@link #readBoolean(Parcel)} method.
     * When the read {@code boolean} value is {@code true}, this
     * method executes {@link Parcel#readString() in.readString()}
     * and returns the value. Otherwise, when the read {@code
     * boolean} value is {@code false}, this method returns
     * {@code null}.
     * </p>
     *
     * @param in
     *         {@code Parcel} to read from.
     *
     * @return
     *         {@code String} object or {@code null}.
     */
    public static String readStringWithPresenceFlag(Parcel in)
    {
        if (readBoolean(in))
        {
            // Present.
            return in.readString();
        }
        else
        {
            // Not present.
            return null;
        }
    }


    /**
     * Write a {@code String} object with a presence flag into the {@code Parcel}.
     *
     * <p>
     * First, this method checks whether {@code value} is {@code null} or not.
     * When {@code null}, this method writes {@code false} into the {@code Parcel}
     * by calling {@link #writeBoolean(Parcel, boolean) writeBoolean(false)} and
     * does nothing any more. Otherwise, when not {@code null}, this method writes
     * {@code true} by calling {@link #writeBoolean(Parcel, boolean)
     * writeBoolean(true)} and then writes the {@code String} object by calling
     * {@link Parcel#writeString(String) out.writeString(value)}.
     * </p>
     *
     * @param out
     *         {@code Parcel} to write into.
     *
     * @param value
     *         A {@code String} object to write.
     */
    public static void writeStringWithPresenceFlag(Parcel out, String value)
    {
        if (value == null)
        {
            // Not present.
            writeBoolean(out, false);
        }
        else
        {
            // Present.
            writeBoolean(out, true);

            // The value.
            out.writeString(value);
        }
    }


    /**
     * Read a {@code Serializable} object from the {@code Parcel}.
     *
     * <p>
     * First, this method reads a {@code boolean} value from the
     * {@code Parcel} using {@link #readBoolean(Parcel)} method.
     * When the read {@code boolean} value is {@code true}, this
     * method executes {@link Parcel#readSerializable()
     * in.readSerializable()} and returns the value. Otherwise,
     * when the read {@code boolean} value is {@code false},
     * this method returns
     * {@code null}.
     * </p>
     *
     * @param in
     *         {@code Parcel} to read from.
     *
     * @return
     *         {@code Serializable} object or {@code null}.
     */
    public static Serializable readSerializableWithPresenceFlag(Parcel in)
    {
        if (readBoolean(in))
        {
            // Present.
            return in.readSerializable();
        }
        else
        {
            // Not present.
            return null;
        }
    }


    /**
     * Write a {@code Serializable} object with a presence flag into the {@code Parcel}.
     *
     * <p>
     * First, this method checks whether {@code value} is {@code null} or not.
     * When {@code null}, this method writes {@code false} into the {@code Parcel}
     * by calling {@link #writeBoolean(Parcel, boolean) writeBoolean(false)} and
     * does nothing any more. Otherwise, when not {@code null}, this method writes
     * {@code true} by calling {@link #writeBoolean(Parcel, boolean)
     * writeBoolean(true)} and then writes the {@code Serializable} object by calling
     * {@link Parcel#writeSerializable(Serializable) out.writeSerializable(value)}.
     * </p>
     *
     * @param out
     *         {@code Parcel} to write into.
     *
     * @param value
     *         A {@code String} object to write.
     */
    public static void writeSerializableWithPresenceFlag(Parcel out, Serializable value)
    {
        if (value == null)
        {
            // Not present.
            writeBoolean(out, false);
        }
        else
        {
            // Present.
            writeBoolean(out, true);

            // The value.
            out.writeSerializable(value);
        }
    }
}
