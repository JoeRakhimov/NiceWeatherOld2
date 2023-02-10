package com.joerakhimov.niceweather.solid

import android.provider.ContactsContract.Data

interface DataFetcher{
    fun fetch()
}

class ApiDataFetcher: DataFetcher {
    override fun fetch() {}
}