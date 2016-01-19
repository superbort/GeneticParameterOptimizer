package de.unimuenster.wi.wwunderbot.parameters.selectors;

import java.util.Collection;
import java.util.HashSet;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Individual;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Selector;

/**
 * Dummy Selector for development and testing.
 * 
 * @author Alexander Brömmer
 *
 * @param <T> Type of Individuals to be selected
 */
public class DummySelector<T extends Individual> implements Selector<T> {

  /**
   * Returns a TreeSet containing all the entries from the input
   */
  @Override
  public HashSet<T> select(Collection<T> input) {
    return new HashSet<>(input);
  }

}
