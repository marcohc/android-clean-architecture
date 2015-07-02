# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Desarrollo\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface2
# class:
#-keepclassmembers class fqcn.of.javascript.interface2.for.webview {
#   public *;
#}

# Jars
-injars ../libs

# Picasso
-dontwarn com.squareup.okhttp.**

# Nineoldandroids
-keep class com.nineoldandroids.animation.** { *; }

# Butter Knife
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

# Otto
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-dontwarn