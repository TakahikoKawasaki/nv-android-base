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


    public static boolean readBoolean(Parcel in)
    {
        return (in.readByte() != 0);
    }


    public static void writeBoolean(Parcel out, boolean value)
    {
        out.writeByte(value ? (byte)1 : (byte)0);
    }


    public static char readChar(Parcel in)
    {
        return (char)(in.readInt() & 0xFFFF);
    }


    public static void writeChar(Parcel out, char ch)
    {
        out.writeInt(ch);
    }


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
