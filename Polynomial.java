import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Polynomial {
    double[] coefficients;
    int[] exponent;

    public Polynomial(){
        coefficients = null; 
        exponent = null;
    }
    public Polynomial(double[] poly, int[] poly_2){
        int length = poly.length;
        coefficients = new double[length];
        for(int i=0; i<length; i++){
            coefficients[i] = poly[i];
        }
        length = poly_2.length; 
        exponent = new int[length];
        for(int i=0; i<length; i++){
            exponent[i] = poly_2[i];
        }
    }

    public Polynomial(int size){
        coefficients = new double[size];
        exponent = new int[size];
    }
    
    public Polynomial (File file) throws Exception{
        BufferedReader b = new BufferedReader(new FileReader(file));
		String line = b.readLine();
        b.close();
        String[] split1 = line.split("(?=[+-])");
        coefficients = new double[split1.length];
        exponent = new int[split1.length];
        for(int i = 0; i < split1.length; i++){
            String p = split1[i];
            if (Character.isDigit(p.charAt(0))){
                exponent[i] = 0;
                coefficients[i] = Double.parseDouble(p);
            }
            else{
                String[] split2 = p.split("x");
                coefficients[i] = Double.parseDouble(split2[0]);
                exponent[i] = Integer.parseInt(split2[1]);
            }
        }
    }
    
    public void saveToFile(String name) throws Exception{
        String result = "";
        for(int i = 0; i < exponent.length; i++){
            result += Double.toString(coefficients[i]) + 'x' + Integer.toString(exponent[i]);
        }
        File file = new File(name);
        file.createNewFile();
        FileWriter filewriter = new FileWriter(name);
        BufferedWriter writer = new BufferedWriter(filewriter);
        writer.write(result);
        writer.close();
    }
     
    public Polynomial multiply(Polynomial poly){
        Polynomial result = new Polynomial(1);
        for(int i =0; i < exponent.length; i++){
            for(int j =0; j < poly.exponent.length; j++){
                Polynomial sum = new Polynomial(1);
                sum.exponent[0] = poly.exponent[j] + exponent[i];
                sum.coefficients[0] = poly.coefficients[j] * coefficients[i];
                result = add(result, sum);
            }
        }
        return result;
    }
    
    public Polynomial add(Polynomial poly1, Polynomial poly2){
        //note poly2 is always 1 1 size (coef, expo)
        //two condition, one update (poly2 expo exist in poly1) poly1
        //               two rewrite (poly2 expo does not exist in poly1) poly1, +1 size
        for(int i=0; i<poly1.exponent.length; i++){ //check condition one update
            if(poly1.exponent[i] == poly2.exponent[0]){ //poly2 expo in poly1
                poly1.coefficients[i] += poly2.coefficients[0];
                return poly1;
            }
        }
        //check condition two rewrite
        //organize exponent
        Polynomial result = new Polynomial(poly1.exponent.length+1);
        int position = 0;
        for(int i = 0; i < result.exponent.length; i++){
            if(position != poly1.exponent.length && poly1.exponent[position] < poly2.exponent[0]){
                result.exponent[i] = poly1.exponent[position]; 
                result.coefficients[i] = poly1.coefficients[position];
                position++;
            }
            else{
                result.exponent[i] = poly2.exponent[0]; 
                result.coefficients[i] = poly2.coefficients[0];
            }
        }
        return result;
    }
     public Polynomial add(Polynomial poly){
        for(int i = 0; i < exponent.length; i++){
            double[] p1 = {coefficients[i]};
            int[] p2 = {exponent[i]};
            Polynomial poly2 = new Polynomial(p1, p2);
            poly = add(poly, poly2);
        }
        return poly;
    }
    
    public double evaluate(double value){
        int length = coefficients.length;
        double result = 0;
        for(int i = 0; i < length; i++){
            result = result + coefficients[i]*(Math.pow(value, exponent[i])); //math,pow(base, power)
        }
        return result;
    }
    public Boolean hasRoot(double value){
        if (evaluate(value) == 0){
            return true;
        }
        return false;
    }
}

