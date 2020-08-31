package com.chrynan.video.presentation.viewmodel.builder

import com.chrynan.video.presentation.core.AdapterItem
import com.chrynan.video.presentation.viewmodel.SectionHeaderViewModel
import com.chrynan.video.presentation.viewmodel.SettingsItemViewModel

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class SettingsBuilderDslMarker

@SettingsBuilderDslMarker
class SettingsViewModelDslBuilder internal constructor() {

    private val listItems = mutableListOf<AdapterItem>()

    fun group(headerText: String, builder: SettingsGroupDslBuilder.() -> Unit) {
        val groupBuilder =
            SettingsGroupDslBuilder(
                headerText = headerText
            )
        builder.invoke(groupBuilder)
        listItems.addAll(groupBuilder.build())
    }

    internal fun build(): List<AdapterItem> = listItems
}

@SettingsBuilderDslMarker
class SettingsGroupDslBuilder internal constructor(val headerText: String) {

    private val settingsCells = mutableListOf<SettingsItemViewModel>()

    operator fun SettingsItemViewModel.unaryPlus(): SettingsItemViewModel {
        settingsCells.add(this)
        return this
    }

    internal fun build(): List<AdapterItem> {
        val header =
            SectionHeaderViewModel(
                header = headerText
            )
        return listOf(header) + settingsCells
    }
}

fun settings(builder: SettingsViewModelDslBuilder.() -> Unit): List<AdapterItem> {
    val settingsBuilder =
        SettingsViewModelDslBuilder()
    builder.invoke(settingsBuilder)
    return settingsBuilder.build()
}