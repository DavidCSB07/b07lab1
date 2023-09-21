
public class Polynomial {
    double[] coefficients;
    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0;
    }
    public Polynomial(double[] poly){
        int length = poly.length;
        coefficients = new double[length];
        for(int i=0; i<length; i++){
            coefficients[i] = poly[i];
        }
    }
    public Polynomial add(Polynomial poly){
        int length1 = poly.coefficients.length;
        int length2 = coefficients.length;
        if(length1 == length2){
            for(int i = 0; i < length1; i++){
                coefficients[i] = poly.coefficients[i] + coefficients[i];
            }
            Polynomial result = new Polynomial(coefficients);
            return result;
        }
        else if(length1 < length2){
            for(int i = 0; i < length1; i++){
                coefficients[i] = poly.coefficients[i] + coefficients[i];
            }
            Polynomial result = new Polynomial(coefficients);
            return result;
        }
        else{
            for(int i = 0; i < length2; i++){
                poly.coefficients[i] = poly.coefficients[i] + coefficients[i];
            }
            Polynomial result = new Polynomial(poly.coefficients);
            return result;
        }
    }
    public double evaluate(double value){
        int length = coefficients.length;
        double result = 0;
        for(int i = 0; i < length; i++){
            result = result + coefficients[i]*(Math.pow(value, i));
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
