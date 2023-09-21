/*************************************************************************
 *  Compilation:  javac PolygonTransform.java
 *  Execution:    java PolygonTransform
 *
 *  @author: Aidan Malixi
 *
 *************************************************************************/

public class PolygonTransform {



        // Returns a new array that is an exact copy of the given array. 
        // The given array is not mutated. 
        public static double[] copy(double[] array) {
    
            double copyarray[] = new double[array.length];
            for (int i = 0; i < array.length; i++) {
                copyarray[i] = array[i];
            }
            return copyarray;
        }
    
    
        // Scales the given polygon by the factor alpha. 
        public static void scale(double[] x, double[] y, double alpha) {
    
            for (int i = 0; i < x.length; i++) {
                x[i] *= alpha;
                y[i] *= alpha;
            }
    
        }
    
        // Translates the given polygon by (dx, dy). 
        public static void translate(double[] x, double[] y, double dx, double dy) {
    
            for (int i = 0; i < x.length; i++) {
                x[i] += dx;
                y[i] += dy;
            }
        }
    
        // Rotates the given polygon theta degrees counterclockwise, about the origin. 
        public static void rotate(double[] x, double[] y, double theta) {
    
            double radian = Math.toRadians(theta);
    
            for (int i = 0; i < x.length; i++) {
                double var1 = x[i];
                double var2 = y[i];
                x[i] = (var1 * Math.cos(radian)) - (var2 * Math.sin(radian));
                y[i] = ((var2) * Math.cos(radian)) + (var1 * Math.sin(radian));
            }
        }
    
        // Tests each of the API methods by directly calling them. 
        public static void main(String[] args) {
    
            StdDraw.setScale(-5.0, +5.0);
            
            double[] x = {0,1,1,0};
            double[] y = {0,0,2,1};
            double theta = 45.0;
    
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.polygon(x, y);
            rotate(x, y, theta);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.polygon(x, y);
    
        }
    }