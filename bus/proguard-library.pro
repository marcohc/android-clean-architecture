#################################################################
# Butter Knife
#################################################################
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#################################################################
# Google appcompat
#################################################################
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

#################################################################
# Google support
#################################################################
-dontwarn android.support.**

#################################################################
# EventBus
#################################################################
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

#################################################################
# COPY THIS IF YOU WANT TO USE Jackson databind
#################################################################
# WARNING: This must be changed with the package name of each project!!!
#-keep public class your.class.package.** {
#  public void set*(***);
#  public *** get*();
#}
#-keep class com.fasterxml.jackson.databind.ObjectMapper {
#    public <methods>;
#    protected <methods>;
#}
#-keep class com.fasterxml.jackson.databind.ObjectWriter {
#    public ** writeValueAsString(**);
#}
#-dontwarn com.fasterxml.jackson.databind.**
#-dontwarn javax.xml.**
#-dontwarn javax.xml.stream.events.**
#-keep class org.codehaus.** { *; }
#-keepattributes *Annotation*,EnclosingMethod,Signature
#-keepattributes Annotation,EnclosingMethod,Signature
#-keepnames class com.fasterxml.jackson.** { *; }
#-keepnames class org.codehaus.jackson.** { *; }
#
#-keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility {
#public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *; }

#################################################################
# COPY THIS IF YOU WANT TO USE Model mapper
#################################################################
#-keep class java.beans.** { *; }
#-keep class sun.misc.Unsafe { *; }
#-dontwarn java.beans.**
#-dontwarn org.modelmapper.**

#################################################################
# Remove logs
#################################################################
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
    public static int wtf(...);
}