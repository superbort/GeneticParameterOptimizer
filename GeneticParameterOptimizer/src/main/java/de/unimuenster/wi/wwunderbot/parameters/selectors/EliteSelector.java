package de.unimuenster.wi.wwunderbot.parameters.selectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Individual;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Selector;

/**
 * Selects a constant number of individuals based on their fitness.
 *
 * @param <T> The type of individual
 */
public class EliteSelector<T extends Individual> implements Selector<T> {
  
  private final int size;
  
  /**
   * Creates a new EliteSelector with the given size.
   * @param eliteSize Size of the elite to select. Cannot be larger than the population.
   */
  public EliteSelector(int eliteSize) {
    this.size = eliteSize;
  }

  @Override
  public Collection<T> select(Collection<T> input) {
    List<T> list = new ArrayList<T>(input);
    Collections.sort(list);
    int end = list.size()-1;
    int middle = end - this.size;
    return list.subList(middle, end);
  }

}
