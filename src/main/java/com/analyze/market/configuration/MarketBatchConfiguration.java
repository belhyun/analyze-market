package com.analyze.market.configuration;

import com.analyze.market.batch.processor.analyze.MarketAnalyzeItemProcessor;
import com.analyze.market.batch.processor.analyze.MarketAnalyzeItemReader;
import com.analyze.market.batch.processor.analyze.MarketAnalyzeItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableBatchProcessing
public class MarketBatchConfiguration {

    /*@Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }

    @Bean
    public Job job(Step step1) throws Exception {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public MarketAnalyzeItemProcessor analyzeProcessor() {
        return null;
    }

    @Bean
    public MarketAnalyzeItemReader analyzeReader() {
        return null;
    }

    @Bean
    public MarketAnalyzeItemWriter analyzeWriter() {
        return null;
    }*/
}
