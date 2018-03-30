package com.recommendation.jobs;

import com.recommendation.LatentFactor.Stochastic;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableScheduling
@Configurable
@Component
public class scheduledTask {
    
    @Autowired
    private Stochastic stochastic;

    @Value("${path.trainFile}")
    private String trainFilePath;

    @Value("${path.testFile}")
    private String testFilePath;
    
    @Scheduled(cron = "0/5 * * * * ?")
    private void runStochastic(){
        stochastic.setAlpha(0.003);
        stochastic.setLambda(0.01);
        stochastic.setIteration(30);
        stochastic.setFactor(220);
        stochastic.setRangeMin(1);
        stochastic.setRangeMax(5);
        stochastic.setTrainFilePath(trainFilePath);
        stochastic.setTestFilePath(testFilePath);
        stochastic.init();
        stochastic.doGradientDescent();
    }
}
