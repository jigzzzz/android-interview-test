package com.tokopedia.filter.view.di

import com.tokopedia.filter.view.data.repository.ProductRepository

object Injection {
    val provideProductRepository = ProductRepository()
}