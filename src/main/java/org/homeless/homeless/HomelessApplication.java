package com.dakar.dakar;

import com.coxautodev.graphql.tools.SchemaParser;
import com.dakar.dakar.resolvers.ShelterResolver;
import com.dakar.dakar.resolvers.MutationResolver;
import com.dakar.dakar.resolvers.QueryResolver;
import com.dakar.dakar.services.interfaces.IShelterService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomelessApplication {

    @Autowired
    private IShelterService shelterService;

    public static void main(String[] args) {
		SpringApplication.run(HomelessApplication.class, args);
	}

    @Bean
    public GraphQL buildGraphQL() {
        GraphQLSchema graphQLSchema = SchemaParser.newParser()
                .file("graphQLSchemas/shelter.graphqls")
                .resolvers(new QueryResolver(shelterService), new ShelterResolver(), new MutationResolver(shelterService))
                .build()
                .makeExecutableSchema();
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
