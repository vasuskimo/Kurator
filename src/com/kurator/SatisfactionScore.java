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
	
	double avg_wait_time, curr_wait_time, avg_price, curr_price, avg_travel_time,
	curr_travel_time, driverScore, waitScore, priceScore, ratingScore, 
	travelTimeScore, totalScore;
	
	public SatisfactionScore(double avg_wait_time, double curr_wait_time,
				 double avg_price, double curr_price, 
				 double avg_travel_time, double curr_travel_time,
				 double curr_driver_score) {
		this.avg_wait_time = avg_wait_time;
		this.curr_wait_time = curr_wait_time;
		this.avg_price = avg_price;
		this.avg_travel_time = avg_travel_time;
		this.driverScore = curr_driver_score;
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
		if(avg_wait_time >= curr_wait_time) {
			waitScore = 100.0;
		} 
		else {
			waitScore = 100.0 - (curr_wait_time - avg_wait_time);
		}
			
	}
	
	public void computePriceScore() {
		if(avg_price >= curr_price) {
			priceScore = 100.0;
		} else {
			priceScore = 100.0 - (curr_price - avg_price);
		}
			
	}
	
	public void computeTravelTimeScore() {
		if(avg_travel_time >= curr_travel_time) {
			travelTimeScore = 100.0;
		} else {
			travelTimeScore = 100.0 - 
			(curr_travel_time - avg_travel_time);
		}
			
	}
	
	public double getTotalScore( ) {

		return totalScore;
	}
	
	public static void main(String[] args) {
	
        }


	 
}
