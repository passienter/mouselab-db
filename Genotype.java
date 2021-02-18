import java.util.*;

/**
 * No equals method needed. Just use String.equals(String).
 */
public class Genotype {

    /**
     * Builds an easily storable version of a genotype given the necessary information
     * @param strain the name of the strain of animal
     * @param alleles a two-dimensional array of strings containing the names of the alleles and the states. Allele
     *                names should be stored in alleles[][0] and states in alleles[][1]. Any values intended to be
     *                blank should be made to be an empty string, NOT null.
     * @return a formatted genotype: StrainName:Allele1#State1;Allele2#State2;...
     */
    public static String buildGenotype(String strain, String[][] alleles){
        String toReturn = strain+":";
        for(int i = 0; i < alleles.length; i++)
            toReturn += alleles[i][0]+"#"+alleles[i][1]+";";
        return toReturn;
    }

    public static String buildGenotype(){
        Scanner read = new Scanner(System.in);
        String strain = "";
        System.out.print("Strain name: ");         //Get strain name
        strain = read.nextLine();
        System.out.print("Number of alleles: ");   //Get number of alleles (can be zero)
        int numAlleles =  read.nextInt();
        read.nextLine();
        if(numAlleles == 0)
            return buildGenotype(strain, new String[0][0]);
        String[][] alleles = new String[numAlleles][2];
        for(int i = 0; i < alleles.length; i++){
            System.out.print("Name of allele "+(i+1)+": ");
            alleles[i][0] = read.nextLine();
            System.out.print("State of allele "+(i+1)+": ");
            alleles[i][1] = read.nextLine();
        }
        return buildGenotype(strain, alleles);
    }

    public static String toString(String genotype){
        String[][] unpacked = unpack(genotype);
        String toReturn = unpacked[0][0];
        if(unpacked.length>1)
            toReturn += ":";
        for(int i = 1; i < unpacked.length; i++)
            toReturn += "  "+unpacked[i][0]+" "+unpacked[i][1];
        return toReturn;
    }

    public static String[][] unpack(String genotype){
        String[] strainSplit = genotype.split(":");
        String[] alleleSplit = new String[0];
        if(strainSplit.length>1)
            alleleSplit = strainSplit[1].split(";");
        String[][] toReturn = new String[alleleSplit.length+1][2];
        toReturn[0][0] = strainSplit[0];
        toReturn[0][1] = "";
        String[] temp;
        for(int i = 0; i < alleleSplit.length; i++){
            temp = alleleSplit[i].split("#");
            toReturn[i+1] = temp;
        }
        return toReturn;
    }

    public static void main(String[] args) {
        System.out.println("* Testing buildGenotype *");
        String genotype = buildGenotype(); //Test both buildGenotype
        System.out.println(genotype);
        System.out.println("* Testing unpack *");
        String[][] unpacked = unpack(genotype); //Test unpack
        for(int i = 0; i < unpacked.length; i++)
            System.out.print(unpacked[i][0]+"\t");
        System.out.println();
        for(int i = 0; i < unpacked.length; i++)
            System.out.print(unpacked[i][1]+"\t");
        System.out.println();
        System.out.println("* Testing toString *");
        System.out.println(toString(genotype)); //Test toString
    }
}
