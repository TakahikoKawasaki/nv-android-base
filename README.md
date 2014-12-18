nv-android-base
===============

Overview
--------

Basic building blocks for Android applications.

A mechanism to terminate/restart an application gracefully is implemented.
The mechanism is achieved based on the idea of "finish chain". See the
description about onResume() method of BaseActivity for details.

The expected flow to start implementing an application is as follows.

1. Set BaseApplication or its subclass as application name in
   AndroidManifest.xml. (See BaseApplication for details.)

2. Create a subclass of BaseRootActivity and set it as the main activity.
   (See BaseRootActivity for details.)

3. Implement your activities which support "finish chain" mechanism
   by extending Base*Activity or by using ActivityHelper.



License
-------

Apache License, Version 2.0


Download
--------

    git clone https://github.com/TakahikoKawasaki/nv-android-base.git


Javadoc
-------

[Javadoc of Neo Visionaries Android Base Package](http://TakahikoKawasaki.github.com/nv-android-base/)


Maven
-----

```xml
<dependency>
    <groupId>com.neovisionaries</groupId>
    <artifactId>nv-android-base</artifactId>
    <version>1.11</version>
</depenency>
```


Author
------

Takahiko Kawasaki, Neo Visionaries Inc.
