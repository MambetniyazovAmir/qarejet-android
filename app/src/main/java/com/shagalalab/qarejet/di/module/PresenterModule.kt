package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.di.AppSchedulers
import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.*
import com.shagalalab.qarejet.ui.category.CategoryPresenter
import com.shagalalab.qarejet.ui.chart.ChartsPresenter
import com.shagalalab.qarejet.ui.dashboard.DashboardPresenter
import com.shagalalab.qarejet.ui.main.MainPresenter
import com.shagalalab.qarejet.ui.record.RecordsPresenter
import com.shagalalab.qarejet.ui.splash.SplashPresenter
import com.shagalalab.qarejet.ui.transaction.AddTransactionPresenter
import com.shagalalab.qarejet.util.SchedulersProvider
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun providesSchedulers(): SchedulersProvider = AppSchedulers()

    @Provides
    @Singleton
    fun providesNewTransactionPresenter(
        addTransactionUseCase: AddTransactionUseCase,
        getAllAccountsUseCase: GetAllAccountsUseCase,
        getAllCategoriesUseCase: GetAllCategoriesUseCase,
        schedulersProvider: SchedulersProvider) =
        AddTransactionPresenter(addTransactionUseCase, getAllAccountsUseCase, getAllCategoriesUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesSplashPresenter(
        initialDataCase: InitialDataUseCase,
        addAccountsUseCase: AddAccountsUseCase,
        addCategoriesUseCase: AddCategoriesUseCase,
        schedulersProvider: SchedulersProvider) =
        SplashPresenter(initialDataCase, addAccountsUseCase, addCategoriesUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesMainPresenter(router: Router) = MainPresenter(router)

    @Provides
    @Singleton
    fun providesTransactionListPresenter(
        getTransactionsByDateUseCase: GetTransactionsByDateUseCase,
        schedulersProvider: SchedulersProvider) =
        RecordsPresenter(getTransactionsByDateUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesChartsPresenter(
        router: Router,
        getGetCategoriesWithAmountUseCase: GetCategoriesWithAmountUseCase,
        schedulersProvider: SchedulersProvider) =
        ChartsPresenter(router, getGetCategoriesWithAmountUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesCategoryPresenter(
        getTransactionsByCategoryUseCase: GetTransactionsByCategoryUseCase,
        schedulersProvider: SchedulersProvider
    ) = CategoryPresenter(getTransactionsByCategoryUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesDashboardPresenter(
        getTotalCashUseCase: GetTotalCashUseCase,
        schedulersProvider: SchedulersProvider
    ) = DashboardPresenter(getTotalCashUseCase, schedulersProvider)
}