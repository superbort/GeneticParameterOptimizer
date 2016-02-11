package de.unimuenster.wi.wwunderbot.parameters.startup;

import java.util.List;

import de.unimuenster.wi.wwunderbot.parameters.evaluation.EloEvaluator;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Population;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Selector;
import de.unimuenster.wi.wwunderbot.parameters.individuals.ParametersIndividual;
import de.unimuenster.wi.wwunderbot.parameters.selectors.DummySelector;
import de.unimuenster.wi.wwunderbot.parameters.selectors.EliteSelector;
import de.unimuenster.wi.wwunderbot.parameters.selectors.QuantileSelector;

public class ParameterOptimizer {
  
  private Population<ParametersIndividual> population;
  private Selector<ParametersIndividual> quantileSelector;
  private EloEvaluator evaluator;
  private Selector<ParametersIndividual> eliteSelector;

  public static void main(String[] args) {
    new ParameterOptimizer();
  }
  
  public ParameterOptimizer() {
    setUp();
    run();
  }
  
  private void setUp(){
    evaluator = new EloEvaluator();
    population = Population.generateRandom(ParametersIndividual.GENERATOR, 100);
    quantileSelector = new QuantileSelector<>(0.2); //mutation and crossover rate at 20%
    eliteSelector = new EliteSelector<>(70); 
  }
  
  private void run(){
    for (int i = 0; i < 50; i++){
      System.out.println("Generation "+ i);
      evaluator.evaluate(population);
      population.applySelector(eliteSelector);
      population.applyCrossover(quantileSelector);
      population.applyMutation(quantileSelector);
      population.nextGen();
    }
    
    List<ParametersIndividual> fittest = population.getFittest(10);
    for (ParametersIndividual p: fittest){
      System.out.println(p);
    }
    
  }

}
