#################################################################
# Glide
#################################################################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

##################################################################
# Retrofit 1.X
##################################################################
-keep class com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
# If in your rest service interface you use methods with Callback argument.
-keepattributes Exceptions
# If your rest service methods throw custom exceptions, because you've defined an ErrorHandler.
-keepattributes Signature

-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn retrofit.**
-dontwarn rx.**

#################################################################
# Jackson databind
#################################################################
# WARNING: This must be changed with the package name of each project!!!
-keep public class com.marcohc.architecture.app.domain.model.** {
  public void set*(***);
  public *** get*();
}
-keep public class com.marcohc.architecture.app.domain.entity.** {
  public void set*(***);
  public *** get*();
}
-keep class com.fasterxml.jackson.databind.ObjectMapper {
    public <methods>;
    protected <methods>;
}
-keep class com.fasterxml.jackson.databind.ObjectWriter {
    public ** writeValueAsString(**);
}
-dontwarn com.fasterxml.jackson.databind.**
-dontwarn javax.xml.**
-dontwarn javax.xml.stream.events.**
-keep class org.codehaus.** { *; }
-keepattributes *Annotation*,EnclosingMethod,Signature
-keepattributes Annotation,EnclosingMethod,Signature
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class org.codehaus.jackson.** { *; }
-keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility {
public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *; }

#################################################################
# Model mapper
#################################################################
-keep class java.beans.** { *; }
-keep class sun.misc.Unsafe { *; }
-dontwarn java.beans.**
-dontwarn org.modelmapper.**

#################################################################
# Timber
#################################################################
-keep class timber.log.** { *; }

-dontwarn

