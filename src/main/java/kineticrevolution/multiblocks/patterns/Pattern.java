package kineticrevolution.multiblocks.patterns;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class Pattern {
    char[][][] pattern;

    public Pattern(char[][][] pattern) {
        this.pattern = pattern;
    }

    //Method signatures to be changed ofcourse
    public void rotatePattern(){
        //TODO: Add rotation for a pattern
        //Eg: Beam from Z-Aligned to X-Aligned etc
    }

    public void modifyPattern(){
        // TODO: Modify a pattern
    }

    public char[][][] getPattern() {
        return pattern;
    }


    /*
    * Make a cubic array viewable when printed out.
     */
    @Override
    public String toString() {
        String test = "";
        for (char[][] x : pattern){
            for(char[] y : x){
                test += "[";
                for (char z:y){
                    test += z;
                }
                test += "] ";
            }
            test += "\n";
        }
        return test;
    }

    public char getCharAt(int x,int y, int z){
        return pattern[x][y][z];
    }


    /*
    * Could possibly need a rewrite.
     */
    public Pattern intersect(Pattern other){
        char[][][] patternOther = other.getPattern();
        int x = Math.max(patternOther.length, pattern.length);
        int y = Math.max(patternOther[0].length,pattern[0].length);
        int z = Math.max(patternOther[0][0].length,pattern[0][0].length);

        char[][][] newPattern = new char[x][y][z];
        char[][][] comparison = new char[x][y][z];

        //Copy all chars to the new maximized cubic array: comparison
        for (int i = 0; i < patternOther.length; i++) {
            for (int j = 0; j < patternOther[i].length ; j++) {
                for (int k = 0; k < patternOther[i][j].length ; k++) {
                    comparison[i][j][k] = patternOther[i][j][k];
                }
            }
        }

        // Compare the current pattern with what is in the comparison. Equal elements get transferred.
        // Unequal elements get left out.
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length ; j++) {
                for (int k = 0; k < pattern[i][j].length ; k++) {
                    if (comparison[i][j][k] == pattern[i][j][k]){
                        newPattern[i][j][k] = pattern[i][j][k];
                    }
                }
            }
        }
        return new Pattern(newPattern);
    }
}
