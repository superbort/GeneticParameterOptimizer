package de.unimuenster.wi.wwunderbot.parameters.evaluation;

import java.util.Iterator;

import com.theaigames.blockbattle.ThreadedBlockbattle;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Population;
import de.unimuenster.wi.wwunderbot.parameters.individuals.ParametersIndividual;

public class EloEvaluator {
  
  private final double k = 20.0;

  public void evaluate(Population<ParametersIndividual> population) {
    Iterator<ParametersIndividual> iter = population.iterator();
    while (iter.hasNext()) {
      ParametersIndividual par1 = iter.next();
      if (!iter.hasNext()) break;
      ParametersIndividual par2 = iter.next();
      String[] args = {"de.unimuenster.wi.wwunderbot.Main", "de.unimuenster.wi.wwunderbot.Main"};
        try {
          int result = ThreadedBlockbattle.runWithParameters(par1, par2);
          int eloDif = par2.getFitness()- par1.getFitness();
          double expected = 1.0 / (1.0 + (Math.pow(10, eloDif/400.0)));
          if (result == 1){
            int value1 = par1.getFitness() + (int) (k*(1.0-expected));
            int value2 = par2.getFitness() - (int) (k*(1.0-expected));
            par1.setFitness(value1);
            par2.setFitness(value2);
          } else if (result == 2){
            int value1 = par1.getFitness() - (int) (k*expected);
            int value2 = par2.getFitness() + (int) (k*expected);
            par1.setFitness(value1);
            par2.setFitness(value2);
          } else {
            int value1 = par1.getFitness() + (int) (k*(0.5-expected));
            int value2 = par2.getFitness() - (int) (k*(0.5-expected));
            par1.setFitness(value1);
            par2.setFitness(value2);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
  }
}
