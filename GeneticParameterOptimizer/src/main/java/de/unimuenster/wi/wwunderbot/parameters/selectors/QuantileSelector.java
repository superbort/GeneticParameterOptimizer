package de.unimuenster.wi.wwunderbot.parameters.selectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Individual;
import de.unimuenster.wi.wwunderbot.parameters.genetics.Selector;

public class QuantileSelector<T extends Individual> implements Selector<T> {

  @Override
  public Collection<T> select(Collection<T> input) {
    List<T> list = new ArrayList<T>(input);
    Collections.sort(list);
    int end = list.size()-1;
    int middle = end - (list.size() / 2);
    return list.subList(middle, end);
  }

}
