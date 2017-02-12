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
 * SatisfactionScore is a composite score of satisfaction from
 * wait time, price, travel time and the driver rating
 * @author Vasu Srinivasan
 */


public class SatisfactionScore {
	
	public static final double WW = 0.9;
	public static final double WP = 1.0;
	public static final double WT = 1.0;
	public static final double WR = 0.9;
	
	double avgWaitTime, currWaitTime, avgPrice, currPrice, avgTravelTime,
	currTravelTime, driverScore, waitScore, priceScore, ratingScore, 
	travelTimeScore, totalScore;
	
	public SatisfactionScore(double avgWaitTime, double currWaitTime,
				 double avgPrice, double currPrice, 
				 double avgTravelTime, double currTravelTime,
				 double currDriverScore) {
		this.avgWaitTime = avgWaitTime;
		this.currWaitTime = currWaitTime;
		this.avgPrice = avgPrice;
		this.avgTravelTime = avgTravelTime;
		this.driverScore = currDriverScore;
	}
	
	public void compute() {
		computeWaitingScore();
		computePriceScore();
		computeTravelTimeScore();
		totalScore = ((waitScore * WW) + (priceScore * WP) + 
			(travelTimeScore * WT)
			+ (ratingScore * WR))/4;
	}
	
	public void computeWaitingScore() {
		if(avgWaitTime >= currWaitTime) {
			waitScore = 100.0;
		} 
		else {
			waitScore = 100.0 - (currWaitTime - avgWaitTime);
		}
			
	}
	
	public void computePriceScore() {
		if(avgPrice >= currPrice) {
			priceScore = 100.0;
		} else {
			priceScore = 100.0 - (currPrice - avgPrice);
		}
			
	}
	
	public void computeTravelTimeScore() {
		if(avgTravelTime >= currTravelTime) {
			travelTimeScore = 100.0;
		} else {
			travelTimeScore = 100.0 - 
			(currTravelTime - avgTravelTime);
		}
			
	}
	
	public double getTotalScore( ) {

		return totalScore;
	}
	
	public static void main(String[] args) {
	
        }


	 
}
