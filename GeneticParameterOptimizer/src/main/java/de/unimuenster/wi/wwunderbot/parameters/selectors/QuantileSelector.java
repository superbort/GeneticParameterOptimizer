package de.unimuenster.wi.wwunderbot.parameters.selectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Individual;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Selector;

public class QuantileSelector<T extends Individual> implements Selector<T> {
  
  
  private final double p;
  
  /**
   * Creates a p-Quantile selector.
   * @param p Value between 0 and 1 to specify size of the quantile.
   */
  public QuantileSelector(double p) {
    this.p = p;
  } 
  
  public QuantileSelector() {
    this(0.5);
  }

  @Override
  public Collection<T> select(Collection<T> input) {
    List<T> list = new ArrayList<T>(input);
    Collections.sort(list);
    int end = list.size()-1;
    int middle = end - (int) (list.size() * p);
    return list.subList(middle, end);
  }

}
