Android Clean Architecture
==============

Following Uncle Bob's clean architecture approach over a communication bus and using a bunch of useful libraries:

- Mosby (MVP)
- ButterKnife
- EventBus
- Retrofit
- (...)

Usage
-----

Take a look on the sample app or check the diagram:

![Diagram] (https://github.com/marcohc/android-clean-architecture/blob/master/structure.png)

Compatibility
-------------

This library is compatible from API 15.

Gradle
------

This library uses the awesome tool [Jitpack] (https://jitpack.io/#marcohc/android-clean-architecture)

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

 * [Fernando Rojas - Android Clean Architecture] (https://github.com/android10/Android-CleanArchitecture)
 * [Mosby MVP] (http://hannesdorfmann.com/android/mosby)

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
