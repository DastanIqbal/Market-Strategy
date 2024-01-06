// Generated by Dagger (https://dagger.dev).
package com.dastanapps.marketstrategy.viewmodels;

import com.dastanapps.marketstrategy.domain.GetDaysAverageUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<GetDaysAverageUseCase> getDaysAverageUseCaseProvider;

  public MainViewModel_Factory(Provider<GetDaysAverageUseCase> getDaysAverageUseCaseProvider) {
    this.getDaysAverageUseCaseProvider = getDaysAverageUseCaseProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(getDaysAverageUseCaseProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<GetDaysAverageUseCase> getDaysAverageUseCaseProvider) {
    return new MainViewModel_Factory(getDaysAverageUseCaseProvider);
  }

  public static MainViewModel newInstance(GetDaysAverageUseCase getDaysAverageUseCase) {
    return new MainViewModel(getDaysAverageUseCase);
  }
}
