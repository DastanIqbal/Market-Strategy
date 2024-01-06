// Generated by Dagger (https://dagger.dev).
package com.dastanapps.marketstrategy.di;

import com.dastanapps.marketstrategy.di.models.AppDispatchers;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_DispatcherFactory implements Factory<AppDispatchers> {
  @Override
  public AppDispatchers get() {
    return dispatcher();
  }

  public static AppModule_DispatcherFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppDispatchers dispatcher() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.dispatcher());
  }

  private static final class InstanceHolder {
    private static final AppModule_DispatcherFactory INSTANCE = new AppModule_DispatcherFactory();
  }
}