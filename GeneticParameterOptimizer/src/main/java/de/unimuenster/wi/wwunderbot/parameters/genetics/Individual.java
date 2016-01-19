package de.unimuenster.wi.wwunderbot.parameters.genetics;

public interface Individual extends Comparable<Individual>{
  
  /**
   * Generator used to create new Individuals
   */
  public interface Generator<T extends Individual> {
      public T generateRandom();
  }

  /**
   * Returns a mutated version of the Indivdual. Does not change the original Indivdual.
   * Mutate may involve randomness and calling it twice does not necessarily lead to equal results.
   */
  public Individual mutate();
  
  /**
   * Crosses over this Individual with the other Individual. Neither this nor the other other Individual are changed.
   * Crossover may involve randomness and calling it twice does not necessarily lead to equal results.
   * @param other The other parent Individual
   * @return The child Individual
   */
  public Individual crossover(Individual other);
  
  /**
   * Returns the fitness of this Indivdual.
   */
  public int getFitness();
  
  /**
   * Sets the fitness of this Individual.
   */
  public void setFitness(int fitness);
  
  

}
