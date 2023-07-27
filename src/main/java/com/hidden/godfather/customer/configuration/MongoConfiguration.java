package com.hidden.godfather.customer.configuration;


import com.hidden.godfather.customer.repository.CustomerRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = {CustomerRepository.class})
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {



    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }


    @Override
    protected String getDatabaseName() {
        return "db_customer";
    }
}
