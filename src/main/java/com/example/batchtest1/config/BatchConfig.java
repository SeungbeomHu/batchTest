package com.example.batchtest1.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private Integer testICN = 0;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemReader<String> helloReader() {

        return new ItemReader<>() {
            private boolean hasRead = false;

            @Override
            public String read() {


                testICN ++;

                return testICN % 5 != 0 ? testICN.toString() : null;


            }
        };

    }

    @Bean
    public ItemWriter<String> helloWriter() {
        System.out.println("data");
        return (items) -> {

            System.out.println(items);


        };
    }

    @Bean
    public Step printHelloStep() {
        return stepBuilderFactory.get("printHelloStep")
                .<String, String>chunk(5) // 한 번에 1개씩 처리
                .reader(helloReader())
                .writer(helloWriter())
                .build();
    }

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                .flow(printHelloStep())
                .end()
                .build();
    }
}