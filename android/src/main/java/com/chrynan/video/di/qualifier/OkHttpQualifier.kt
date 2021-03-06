package com.chrynan.video.di.qualifier

import javax.inject.Qualifier

object OkHttpQualifier {

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    @Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    annotation class Coil

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    @Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    annotation class Web
}