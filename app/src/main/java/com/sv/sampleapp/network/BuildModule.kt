package com.sv.sampleapp.network

import dagger.Module
import dagger.Provides

@Module
class BuildModule {
    private var isDebug: Boolean

    constructor(isDebug: Boolean) {
        this.isDebug = isDebug
    }

    @Provides
    fun isDebugBuild(): Boolean {
        return isDebug
    }
}