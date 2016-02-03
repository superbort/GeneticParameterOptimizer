package de.unimuenster.wi.wwunderbot.parameters.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Individual.Generator;

public class Population<T extends Individual> implements Iterable<T>{
  
  private Set<T> currentGeneration;
  private Set<T> nextGeneration;
  
  
  public static <T extends Individual> Population<T> generateRandom(Generator<T> generator, int initialSize){
    
    HashSet<T> firstGeneration = new HashSet<>();
    
    for (int i = 0; i < initialSize; ++i){
      firstGeneration.add(generator.generateRandom());
    }
    
    return new Population<>(firstGeneration);
  }
  
  private Population(Set<T> firstGeneration) {
    currentGeneration = firstGeneration;
    nextGeneration = new HashSet<>();
  }
  
  @SuppressWarnings("unchecked") //Type erasure prevents checking
  public void applyMutation(Selector<T> mutationSelector){
    Collection<T> toMutate = mutationSelector.select(currentGeneration);
    for(T indvidual: toMutate){
      T mutated = (T) indvidual.mutate();
      nextGeneration.add(mutated);
    }
  }
  
  @SuppressWarnings("unchecked")
  public void applyCrossover(Selector<T> parentSelector){
    Collection<T> parents = parentSelector.select(currentGeneration);
    Iterator<T> parentIterator = parents.iterator();
    while (parentIterator.hasNext()){
      T parent1 = parentIterator.next();
      if (!parentIterator.hasNext()) break;
      T parent2 = parentIterator.next();
      T child = (T) parent1.crossover(parent2);
      nextGeneration.add(child);
    }
  }
  
  public void nextGen(){
    currentGeneration = nextGeneration;
    nextGeneration = new HashSet<>();
  }
  
  public void applySelector(Selector<T> selector){
    Collection<T> selected = selector.select(currentGeneration);
    nextGeneration.addAll(selected);
  }

  @Override
  public Iterator<T> iterator() {
    return currentGeneration.iterator();
  }
  
  public List<T> getFittest(int i){
    ArrayList<T> result = new ArrayList<>(currentGeneration);
    Collections.sort(result);
    Collections.reverse(result);
    return result.subList(0, i);
  }

}
