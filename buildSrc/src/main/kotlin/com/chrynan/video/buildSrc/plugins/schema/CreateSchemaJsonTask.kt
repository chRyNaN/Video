package com.chrynan.video.buildSrc.plugins.schema

import graphql.GraphQL
import graphql.introspection.IntrospectionQuery
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

open class CreateSchemaJsonTask @Inject constructor(private val extension: CreateSchemaJsonExtension) :
    DefaultTask() {

    @TaskAction
    fun create() {
        val graphQLDirectoryPath =
            extension.graphQLDirectory ?: throw InvalidExtensionException(
                extension
            )
        val schemaDirectoryPath =
            extension.schemaOutputDirectory ?: throw InvalidExtensionException(
                extension
            )

        val mockRuntimeWiring =
            RuntimeWiring.newRuntimeWiring().wiringFactory(MockedWiringFactory()).build()
        val parser = SchemaParser()
        val typeRegistry = TypeDefinitionRegistry()

        val graphQLDirectory = File(graphQLDirectoryPath)
        val schemaDirectory = File(schemaDirectoryPath)

        if (!graphQLDirectory.isDirectory) throw InvalidInputDirectoryException(
            graphQLDirectoryPath
        )
        if (!schemaDirectory.isDirectory) throw InvalidOutputDirectoryException(
            schemaDirectoryPath
        )

        val graphQLFiles = (graphQLDirectory.listFiles()?.toList() ?: emptyList())
            .filter {
                it.isFile && (it.path.endsWith(".graphql") ||
                        it.path.endsWith(".graphqls") ||
                        it.path.endsWith(".gql"))
            }

        graphQLFiles.forEach { typeRegistry.merge(parser.parse(it)) }

        val schema = SchemaGenerator().makeExecutableSchema(typeRegistry, mockRuntimeWiring)
        val graphQL = GraphQL.newGraphQL(schema).build()
        val executionResult = graphQL.execute(IntrospectionQuery.INTROSPECTION_QUERY)

        val output = JSONObject(executionResult.toSpecification())

        val outputFile = File(schemaDirectory, "schema.json")

        outputFile.writeText(output.toString())
    }

    private class MockedWiringFactory : EchoingWiringFactory() {

        override fun providesScalar(environment: ScalarWiringEnvironment): Boolean =
            !ScalarInfo.isGraphqlSpecifiedScalar(environment.scalarTypeDefinition.name)

        override fun getScalar(environment: ScalarWiringEnvironment): GraphQLScalarType =
            GraphQLScalarType.newScalar()
                .name(environment.scalarTypeDefinition.name)
                .coercing(object : Coercing<Any, Any> {

                    override fun parseValue(input: Any): Any =
                        throw UnsupportedOperationException("parseValue() function is not implemented.")

                    override fun parseLiteral(input: Any): Any =
                        throw UnsupportedOperationException("parseLiteral() function is not implemented.")

                    override fun serialize(dataFetcherResult: Any): Any =
                        throw UnsupportedOperationException("serialize() function is not implemented.")
                })
                .build()
    }
}