package de.unimuenster.wi.wwunderbot.parameters.individuals;

import java.util.Random;

import com.theaigames.blockbattle.Parameters;

import de.unimuenster.wi.wwunderbot.parameters.genetics.Individual;

public class ParametersIndividual implements Individual, Parameters {

  public static final Individual.Generator<ParametersIndividual> GENERATOR = new Generator();

  private static final short HEIGHT_WEIGHT = -5000;

  private int fitness;

  private final short bumpinessWeight;
  private final short completenessWeight;
  private final short holesWeight;

  private Random random;

  private ParametersIndividual(short bumpinessWeight, short completenessWeight, short holesWeight) {
    this.fitness = 1000;
    this.bumpinessWeight = bumpinessWeight;
    this.completenessWeight = completenessWeight;
    this.holesWeight = holesWeight;
    this.random = new Random(); // TODO Seed for Random in Parameters Indivdual
  }

  @Override
  public ParametersIndividual mutate() {
    short newBumpinessWeight = bumpinessWeight;
    short newCompletenessWeight = completenessWeight;
    short newHolesWeight = holesWeight;
    int mutator = 1 << random.nextInt(16);
    switch (random.nextInt(3)) {
      case 0:
        newBumpinessWeight ^= mutator;
        break;
      case 1:
        newCompletenessWeight ^= mutator;
        break;
      default:
        newHolesWeight ^= mutator;
    }
    return new ParametersIndividual(newBumpinessWeight, newCompletenessWeight, newHolesWeight);
  }

  @Override
  public ParametersIndividual crossover(Individual other) {
    if (!(other instanceof ParametersIndividual)) return null;
    ParametersIndividual otherParameters = (ParametersIndividual) other;
    // choose parents for each property by a random bitstring (one bit for each property)
    int randombits = random.nextInt(0b111); // thats 8
    short childBumpiness = (0b001 & randombits) == 0 ? this.bumpinessWeight : otherParameters.bumpinessWeight;
    short childCompleteness = (0b010 & randombits) == 0 ? this.completenessWeight : otherParameters.completenessWeight;
    short childHoles = (0b100 & randombits) == 0 ? this.holesWeight : otherParameters.holesWeight;
    return new ParametersIndividual(childBumpiness, childCompleteness, childHoles);
  }

  @Override
  public int getFitness() {
    return fitness;
  }

  @Override
  public void setFitness(int fitness) {
    this.fitness = fitness;
  }

  public short getHeightWeight() {
    return HEIGHT_WEIGHT;
  }

  public short getBumpinessWeight() {
    return bumpinessWeight;
  }

  public short getCompletenessWeight() {
    return completenessWeight;
  }

  public short getHolesWeight() {
    return holesWeight;
  }

  private static class Generator implements Individual.Generator<ParametersIndividual>{

    @Override
    public ParametersIndividual generateRandom() {
      Random random = new Random();
      short bumpinessWeight = (short) (random.nextInt((int) Short.MAX_VALUE * 2) - Short.MAX_VALUE);
      short completenessWeight = (short) (random.nextInt((int) Short.MAX_VALUE * 2) - Short.MAX_VALUE);
      short holesWeight = (short) (random.nextInt((int) Short.MAX_VALUE * 2) - Short.MAX_VALUE);
      return new ParametersIndividual(bumpinessWeight, completenessWeight, holesWeight);
    }

  }

  @Override
  public int compareTo(Individual o) {
    if (o instanceof ParametersIndividual){
      ParametersIndividual other = (ParametersIndividual) o;
      return this.fitness-other.fitness;
    }
    return 1;
  }

  @Override
  public String toString() {
    return "ParametersIndividual [fitness=" + fitness + ", bumpinessWeight=" + bumpinessWeight
        + ", completenessWeight=" + completenessWeight + ", holesWeight=" + holesWeight + "]";
  }
  


}
