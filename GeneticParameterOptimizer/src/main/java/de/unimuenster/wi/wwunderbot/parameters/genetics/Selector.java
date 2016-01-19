package de.unimuenster.wi.wwunderbot.parameters.genetics;

import java.util.Collection;

public interface Selector<T extends Individual> {

  /**
   * Selects Individuals from a given Collection. The Collection will not be altered.
   * Individuals will NOT be cloned.
   * @param The input Collection
   * @return A new Collection containing the selected Individuals
   */
  public Collection<T> select(Collection<T> input);
  
}
