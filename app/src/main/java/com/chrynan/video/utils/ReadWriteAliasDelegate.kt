package com.chrynan.video.utils

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

class ReadWriteAliasDelegate<T>(private val delegate: KMutableProperty0<T>) : ReadWriteProperty<Any?, T> {

    override operator fun getValue(thisRef: Any?, property: KProperty<*>) = delegate.get()

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = delegate.set(value)
}

class ReadOnlyAliasDelegate<T>(private val delegate: KProperty0<T>) : ReadOnlyProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>) = delegate.get()
}

fun <T> alias(delegate: KMutableProperty0<T>) = ReadWriteAliasDelegate(delegate = delegate)

fun <T> alias(delegate: KProperty0<T>) = ReadOnlyAliasDelegate(delegate = delegate)