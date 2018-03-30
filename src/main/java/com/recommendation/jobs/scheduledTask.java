package com.recommendation.jobs;

import com.recommendation.LatentFactor.Stochastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Configurable
@Component
public class scheduledTask {
    
    @Autowired
    private Stochastic stochastic;
    
    @Scheduled(cron = "0/5 * * * * ?")
    private void runStochastic(){
        stochastic.setAlpha(0.003);
        stochastic.setLambda(0.01);
        stochastic.setIteration(30);
        stochastic.setFactor(220);
        stochastic.setTrainFilePath("/Users/victor/Desktop/ml-latest-small/train.txt");
        stochastic.setTestFilePath("/Users/victor/Desktop/ml-latest-small/test.txt");
        stochastic.init();
        stochastic.doGradientDescent();
    }
}
