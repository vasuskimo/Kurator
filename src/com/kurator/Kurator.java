/* This program is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kurator;

/**
 * Kurator - Kurator is a mathematical model that aims to accurately predict the internal 
 * satisfaction of a prospective car ride and enables ride sharing companies to manage the 
 * overall satisfaction of the rider by providing alternatives before vehicle allocation.
 * @author Vasu Srinivasan
 */

public class Kurator {

    // These PID gain parameters
    // =========================    
    public static final double KP = 1.0;
    public static final double KI = 2.0;
    public static final double KD = 0.4;

    public static final double TARGET = 100.0;

    private double currentVal;
    private double cummulativeError;
    private double lastError;
    
    public double getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(double currentVal) {
        this.currentVal = currentVal;
    }

    public double getCummulativeError() {
        return cummulativeError;
    }

    public void setCummulativeError(double cumError) {
        this.cummulativeError = cumError;
    }

    public double getLastError() {
        return lastError;
    }

    public void setLastError(double lastError) {
        this.lastError = lastError;
    }

    public Kurator(double currentVal) {
        this.currentVal = currentVal;
    }
    
    public double getSatisfactionMetric( ) {
       double error, pCorrection, iCorrection, dCorrection, slope, corr; 
       error = TARGET - currentVal;
       pCorrection = KP * error;
       cummulativeError += error;
       iCorrection = KI * cummulativeError;
       slope = error - lastError;
       dCorrection = slope * KD;
       lastError = error;
       corr = pCorrection + iCorrection + dCorrection;
       return corr; 
    }
    
     public static void main(String[] args) {
         double cummulativeError = 0.1;
         double lastError = 0.1;
         
         SatisfactionScore score = 
		new RiderSatisfactionScore(
                6.5, 4.5, 7.7,11.5,32.3, 54.5, 95.0);
         score.compute();
         System.out.println("Total Score is " + score.getTotalScore());
         Kurator kurator = new  Kurator( score.getTotalScore());
         kurator.setCummulativeError(cummulativeError);
         kurator.setLastError(lastError);
         double satisfaction = kurator.getSatisfactionMetric();
         
         System.out.println("Satisfaction is " + satisfaction);
     }
    
}
