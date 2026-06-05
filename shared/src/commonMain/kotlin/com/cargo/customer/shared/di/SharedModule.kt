package com.cargo.customer.shared.di

import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSource
import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSourceImp
import com.cargo.customer.shared.data.local.datasource.OnboardingDataSource
import com.cargo.customer.shared.data.local.datasource.OnboardingDataSourceImp
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSourceImpl
import com.cargo.customer.shared.data.repository.AuthRepositoryImp
import com.cargo.customer.shared.data.repository.OnboardingRepositoryImp
import com.cargo.customer.shared.domain.repository.AuthRepository
import com.cargo.customer.shared.domain.usecase.LogInUseCase
import com.cargo.customer.shared.domain.repository.OnboardingRepository
import com.cargo.customer.shared.domain.usecase.IsLoggedInUseCase
import com.cargo.customer.shared.domain.usecase.IsOnboardingFirstTimeUseCase
import com.cargo.customer.shared.domain.usecase.SetOnboardingFirstTimeUseCase
import com.cargo.customer.shared.domain.usecase.auth.RegisterUseCase
import org.koin.dsl.module


// Shared: repositories, use cases, shared ViewModels
val sharedModule = module {
    // provide shared ViewModels

    //datasource
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }

    single<AuthLocalDataSource> {
        AuthLocalDataSourceImp(get())
    }

    single <OnboardingDataSource>{ OnboardingDataSourceImp(get()) }

    //repo
    single<AuthRepository> {
        AuthRepositoryImp(
            remote = get(),
            local = get()
        )
    }
    //usecase
    factory { LogInUseCase(get()) }

    factory { IsLoggedInUseCase(get()) }

    single <OnboardingRepository>{ OnboardingRepositoryImp(get()) }

    factory { SetOnboardingFirstTimeUseCase(get()) }

    factory { IsOnboardingFirstTimeUseCase(get()) }

    factory { RegisterUseCase(get()) }
}