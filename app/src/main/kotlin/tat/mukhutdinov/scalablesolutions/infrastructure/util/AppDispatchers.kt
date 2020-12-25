package tat.mukhutdinov.scalablesolutions.infrastructure.util

import tat.mukhutdinov.android.structure.Dispatchers


class AppDispatchers : Dispatchers {

    override val Main = kotlinx.coroutines.Dispatchers.Main

    override val IO = kotlinx.coroutines.Dispatchers.IO

    override val Default = kotlinx.coroutines.Dispatchers.Default
}