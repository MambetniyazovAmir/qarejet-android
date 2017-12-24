package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddNewTransactionUseCase
import com.shagalalab.qarejet.ui.splash.SplashPresenter
import com.shagalalab.qarejet.ui.transaction.NewTransactionPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/16/2017.
 */

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun providesNewTransactionPresenter(
            addNewTransactionUseCase: AddNewTransactionUseCase,
            getAllAccountsUseCase: GetAllAccountsUseCase,
            getAllCategoriesUseCase: GetAllCategoriesUseCase
    ) = NewTransactionPresenter(addNewTransactionUseCase, getAllAccountsUseCase, getAllCategoriesUseCase)

    @Provides
    @Singleton
    fun providesSplashPresenter(initialDataCase: InitialDataUseCase,
                                addAccountsUseCase: AddAccountsUseCase,
                                addCategoriesUseCase: AddCategoriesUseCase) =
            SplashPresenter(initialDataCase, addAccountsUseCase, addCategoriesUseCase)

}