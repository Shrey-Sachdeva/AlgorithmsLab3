
//Name: Shrey Sachdeva
//EID: ss77335


public class Program3 {

    EconomyCalculator calculator;
    VibraniumOreScenario vibraniumScenario;    

    public Program3() {
        this.calculator = null;
        this.vibraniumScenario = null;
    }

    /*
     * This method is used in lieu of a required constructor signature to initialize
     * your Program3. After calling a default (no-parameter) constructor, we
     * will use this method to initialize your Program3 for Part 1.
     */
    public void initialize(EconomyCalculator ec) {
        this.calculator = ec;
    }

    /*
     * This method is used in lieu of a required constructor signature to initialize
     * your Program3. After calling a default (no-parameter) constructor, we
     * will use this method to initialize your Program3 for Part 2.
     */
    public void initialize(VibraniumOreScenario vs) {
        this.vibraniumScenario = vs;
    }

    /*
     * This method returns an integer that is maximum possible gain in the Wakandan economy
     * given a certain amount of Vibranium
     */
    //TODO: Complete this function
    public int computeGain() {
        int[][] gain = new int[calculator.getNumProjects() + 1][calculator.getNumVibranium() + 1];
        // gain[0].length gives the amount of vibranium
        for(int i = 0; i < gain[0].length; i++) {
            gain[0][i] = 0;
        }
        // gain.length gives the number of projects
        for(int i = 0; i < gain.length; i++) {
            gain[i][0] = 0;
        }
        for(int i = 1; i < gain.length; i++) {
            for(int j = 1; j < gain[0].length; j++) {
                gain[i][j] = findMaxGain(gain, i, j);
            }
        }
        return gain[calculator.getNumProjects()][calculator.getNumVibranium()];
    }

    private int findMaxGain(int[][] gain, int project, int numVibranium) {
        int maxGain = 0;
        // i is the amount of vibranium allocated to the current project
        for(int i = 0; i <= numVibranium; i++) {
            int currentGain = calculator.calculateGain(project - 1, i) + gain[project - 1][numVibranium - i];
            if(currentGain > maxGain) {
                maxGain = currentGain;
            }
        }
        return maxGain;
    }

    /*
     * This method returns an integer that is the maximum possible dollar value that a thief 
     * could steal given the weight and volume capacity of his/her bag by using the 
     * VibraniumOreScenario instance.
     */
     //TODO: Complete this method
     public int computeLoss() {
        int[][][] value = new int[vibraniumScenario.getNumOres() + 1][vibraniumScenario.getWeightCapacity() + 1][vibraniumScenario.getVolumeCapacity() + 1];
        for(int i = 0; i < value[0].length; i++) {
            for(int j = 0; j < value[0][0].length; j++) {
                value[0][i][j] = 0;
            }
        }
        for(int o = 1; o < value.length; o++) {
            VibraniumOre currentOre = vibraniumScenario.getVibraniumOre(o - 1);
            for(int w = 1; w < value[0].length; w++) {
                for(int v = 1; v < value[0][0].length; v++) {
                    int reject = value[o - 1][w][v];
                    if(currentOre.getWeight() > w || currentOre.getVolume() > v) {
                        value[o][w][v] = reject;
                    }
                    else {
                        int accept = currentOre.getPrice() + value[o - 1][w - currentOre.getWeight()][v - currentOre.getVolume()];
                        if(accept > reject) {
                            value[o][w][v] = accept;
                        }
                        else {
                            value[o][w][v] = reject;
                        }
                    }
                }
            }
        }
        return value[vibraniumScenario.getNumOres()][vibraniumScenario.getWeightCapacity()][vibraniumScenario.getVolumeCapacity()];
     }
}


