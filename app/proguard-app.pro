#################################################################
# Glide
#################################################################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

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

#################################################################
# Timber
#################################################################
-keep class timber.log.** { *; }

-dontwarn

