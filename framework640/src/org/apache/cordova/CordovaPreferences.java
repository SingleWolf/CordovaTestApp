/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package org.apache.cordova;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;

public class CordovaPreferences {
    private HashMap<String, String> prefs = new HashMap<String, String>(20);
    private Bundle preferencesBundleExtras;

    public void setPreferencesBundle(Bundle extras) {
        preferencesBundleExtras = extras;
    }

    public void set(String name, String value) {
        prefs.put(name.toLowerCase(Locale.ENGLISH), value);
    }

    public void set(String name, boolean value) {
        set(name, "" + value);
    }

    public void set(String name, int value) {
        set(name, "" + value);
    }
    
    public void set(String name, double value) {
        set(name, "" + value);
    }
    
    public Map<String, String> getAll() {
        return prefs;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        name = name.toLowerCase(Locale.ENGLISH);
        String value = prefs.get(name);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    // Added in 4.0.0
    public boolean contains(String name) {
        return getString(name, null) != null;
    }

    public int getInteger(String name, int defaultValue) {
        name = name.toLowerCase(Locale.ENGLISH);
        String value = prefs.get(name);
        if (value != null) {
            // Use Integer.decode() can't handle it if the highest bit is set.
            return (int)(long)Long.decode(value);
        }
        return defaultValue;
    }

    public double getDouble(String name, double defaultValue) {
        name = name.toLowerCase(Locale.ENGLISH);
        String value = prefs.get(name);
        if (value != null) {
            return Double.valueOf(value);
        }
        return defaultValue;
    }

    public String getString(String name, String defaultValue) {
        name = name.toLowerCase(Locale.ENGLISH);
        String value = prefs.get(name);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public void copyIntoIntentExtras(Activity action) {
        Iterator i$ = this.prefs.keySet().iterator();

        while(i$.hasNext()) {
            String name = (String)i$.next();
            String value = (String)this.prefs.get(name);
            if (value != null) {
                if (name.equals("loglevel")) {
                    LOG.setLogLevel(value);
                } else {
                    int asInt;
                    if (name.equals("splashscreen")) {
                        asInt = action.getResources().getIdentifier(value, "drawable", action.getClass().getPackage().getName());
                        if (asInt == 0) {
                            asInt = action.getResources().getIdentifier(value, "drawable", action.getPackageName());
                        }

                        action.getIntent().putExtra(name, asInt);
                    } else if (name.equals("backgroundcolor")) {
                        asInt = Long.decode(value).intValue();
                        action.getIntent().putExtra(name, asInt);
                    } else if (name.equals("loadurltimeoutvalue")) {
                        asInt = Integer.decode(value);
                        action.getIntent().putExtra(name, asInt);
                    } else if (name.equals("splashscreendelay")) {
                        asInt = Integer.decode(value);
                        action.getIntent().putExtra(name, asInt);
                    } else {
                        boolean asBool;
                        if (name.equals("keeprunning")) {
                            asBool = Boolean.parseBoolean(value);
                            action.getIntent().putExtra(name, asBool);
                        } else if (name.equals("inappbrowserstorageenabled")) {
                            asBool = Boolean.parseBoolean(value);
                            action.getIntent().putExtra(name, asBool);
                        } else if (name.equals("disallowoverscroll")) {
                            asBool = Boolean.parseBoolean(value);
                            action.getIntent().putExtra(name, asBool);
                        } else {
                            action.getIntent().putExtra(name, value);
                        }
                    }
                }
            }
        }

        if (this.preferencesBundleExtras == null) {
            this.preferencesBundleExtras = action.getIntent().getExtras();
        }

    }
}
