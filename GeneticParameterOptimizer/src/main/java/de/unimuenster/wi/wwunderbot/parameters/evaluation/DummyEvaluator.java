package de.unimuenster.wi.wwunderbot.parameters.evaluation;

import com.theaigames.blockbattle.ThreadedBlockbattle;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Population;
import de.unimuenster.wi.wwunderbot.parameters.individuals.ParametersIndividual;

public class DummyEvaluator {

  public void evaluate(Population<ParametersIndividual> population) {
    for (ParametersIndividual individual : population) {
      ThreadedBlockbattle.parameters = individual;
      String[] args = {"de.unimuenster.wi.wwunderbot.Main", "de.unimuenster.wi.wwunderbot.Main"};
        try {
          ThreadedBlockbattle.main(args);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
  }
}
