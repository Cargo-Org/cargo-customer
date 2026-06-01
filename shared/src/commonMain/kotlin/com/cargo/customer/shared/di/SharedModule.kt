package com.cargo.customer.shared.di

import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSource
import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSourceImp
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSourceImpl
import com.cargo.customer.shared.data.repository.AuthRepositoryImp
import com.cargo.customer.shared.domain.repository.AuthRepository
import com.cargo.customer.shared.domain.usecase.LoginUseCase
import org.koin.dsl.module


// Shared: repositories, use cases, shared ViewModels
val sharedModule = module {
    // provide repositories
    // provide use cases
    // provide shared ViewModels


    //datasource
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }

    single<AuthLocalDataSource> {
        AuthLocalDataSourceImp(get())
    }

    //repo
    single<AuthRepository> {
        AuthRepositoryImp(
            remote = get(),
            local = get()
        )
    }
    //usecase
    factory { LoginUseCase(get()) }
}