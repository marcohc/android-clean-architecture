Android Clean Architecture
==============

Following Uncle Bob's clean architecture approach over a communication bus and using a bunch of useful libraries (see credits).

Usage
-----

Take a look on the sample app or check the diagram:

![Diagram] (https://github.com/marcohc/android-clean-architecture/blob/master/structure.png)

Compatibility
-------------

This library is compatible from API 15.

Gradle
------

This library uses the awesome tool [![](https://jitpack.io/v/marcohc/android-clean-architecture.svg)](https://jitpack.io/#marcohc/android-clean-architecture)

Add the repository to your general build.gradle:

``` xml
repositories {
	    maven {
	        url "https://jitpack.io"
	    }
	}
```

And then add the library in your specific project build.gradle:

``` xml
    compile 'com.github.marcohc:android-clean-architecture:<release>'
```

Developed By
------------

* Marco Hernaiz Cao - <marco.hernaiz.cao@gmail.com>

Credits
-------

* [Fernando Rojas - Android Clean Architecture] [0]
* [Event bus] [1]
* [Mosby MVP] [2]
* [Butter knife] [3]
* [Toasteroid] [4]
* [Helperoid] [5]
* [Calligraphy] [6]
* [Custom crash] [7]
* [Timber] [8]
* [Retrofit] [9]

License
-------

    Copyright 2016 Marco Hernaiz Cao

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
[0]: https://github.com/android10/Android-CleanArchitecture
[1]: https://github.com/greenrobot/eventbus
[2]: https://github.com/sockeqwe/mosby
[3]: https://github.com/jakewharton/butterknife
[4]: https://github.com/marcohc/toasteroid
[5]: https://github.com/marcohc/com.marcohc.helperoid
[6]: https://github.com/chrisjenx/calligraphy
[7]: https://github.com/Ereza/CustomActivityOnCrash
[8]: https://github.com/JakeWharton/timber
[9]: https://github.com/square/retrofit
