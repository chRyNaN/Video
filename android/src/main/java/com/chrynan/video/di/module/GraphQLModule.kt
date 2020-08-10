package com.chrynan.video.di.module

import com.chrynan.video.common.graphql.ApolloGraphQLClientFactory
import com.chrynan.video.common.graphql.GraphQLClientFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class GraphQLModule {

    @Binds
    abstract fun bindGraphQLClientFactory(clientFactory: ApolloGraphQLClientFactory): GraphQLClientFactory
}