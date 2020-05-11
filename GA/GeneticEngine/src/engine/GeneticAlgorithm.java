package engine;

import java.io.*;

import org.jgap.*;
import org.jgap.audit.*;
import org.jgap.data.*;
import org.jgap.impl.*;
import org.jgap.xml.*;
import org.w3c.dom.*;

public class GeneticAlgorithm {
//La funcion fitness escribe los parametros que hay que tunear en el disk para que lo lea el roboto.
//Llama a launcher para empezar la batalla con ciertos parametros

    private static final int MAX_ALLOWED_EVOLUTIONS = 50;

    public static void createGA(int coso, boolean a_doMonitor) throws Exception {

        // Empieza con los default
        Configuration conf = new DefaultConfiguration();

        // pones la fitness function que hemos creado
        FitnessFunction myFunc = new FitnessFunct();    //PARAMETRO QUE NOS DA EL USER

        conf.setFitnessFunction(myFunc);

        // Cromosoma con 4 genes: 

        Gene[] sampleGenes = new Gene[4];

        sampleGenes[0] = new IntegerGene(conf, 0, 3);  // distanceLimit
        sampleGenes[1] = new IntegerGene(conf, 0, 2);  // probability
        sampleGenes[2] = new IntegerGene(conf, 0, 1);  // range
        sampleGenes[3] = new IntegerGene(conf, 0, 4);  // minimumSpeed

        IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);

        //Configurar la poblacion
        conf.setPopulationSize(20);


    }

}