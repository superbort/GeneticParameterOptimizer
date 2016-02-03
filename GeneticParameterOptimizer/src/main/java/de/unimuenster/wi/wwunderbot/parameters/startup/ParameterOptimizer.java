package de.unimuenster.wi.wwunderbot.parameters.startup;

import java.util.List;

import de.unimuenster.wi.wwunderbot.parameters.evaluation.DummyEvaluator;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Population;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Selector;
import de.unimuenster.wi.wwunderbot.parameters.individuals.ParametersIndividual;
import de.unimuenster.wi.wwunderbot.parameters.selectors.DummySelector;
import de.unimuenster.wi.wwunderbot.parameters.selectors.QuantileSelector;

public class ParameterOptimizer {
  
  private Population<ParametersIndividual> population;
  private Selector<ParametersIndividual> dummySelector; //TODO replace dummySelector with real ones
  private Selector<ParametersIndividual> quantileSelector;
  private DummyEvaluator evaluator;

  public static void main(String[] args) {
    new ParameterOptimizer();
  }
  
  public ParameterOptimizer() {
    setUp();
    run();
  }
  
  private void setUp(){
    evaluator = new DummyEvaluator();
    population = Population.generateRandom(ParametersIndividual.GENERATOR, 100);
    dummySelector = new DummySelector<>();
    quantileSelector = new QuantileSelector<>();
  }
  
  private void run(){
    for (int i = 0; i < 50; i++){
      System.out.println("Generation "+ i);
      //population.applyCrossover(quantileSelector);
      population.applyMutation(quantileSelector);
      population.nextGen();
      evaluator.evaluate(population);
      population.applySelector(quantileSelector);
    }
    
    List<ParametersIndividual> fittest = population.getFittest(10);
    for (ParametersIndividual p: fittest){
      System.out.println(p);
    }
    
  }

}
