# Marvel Muzei

This repository contains the source code for the Marvel Muzei app.

It is available from [Google Play][].

MarvelMuzei is an extension for the [Muzei][] live-wallpaper app.

## Features

* Marvel characters or comics wallpapers every day
* WiFi-only download mode
* Customizable update interval
* Switch to next picture manually
* Save picture on your devices
* Share picture with your friends


## Building the app

The Android SDK for API level 21 is required to build the app. Then launch:

    ./gradlew clean build


## Acknowledgements

This project uses many great open-source libraries, such as:

* [Butterknife][]
* [Dagger][]
* [Retrofit][]

## License

<pre>
Copyright 2015 Arnaud Piroelle

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>

[Muzei]: https://play.google.com/store/apps/details?id=net.nurik.roman.muzei
[Google Play]: https://play.google.com/store/apps/details?id=com.arnaudpiroelle.marvelmuzei
[Butterknife]: http://jakewharton.github.io/butterknife/
[Dagger]: http://square.github.io/dagger/
[Retrofit]: http://square.github.io/retrofit/